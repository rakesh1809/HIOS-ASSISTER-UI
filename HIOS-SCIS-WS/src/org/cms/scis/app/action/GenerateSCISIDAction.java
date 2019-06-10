package org.cms.scis.app.action;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.cms.scis.app.service.HIOSSCISService;
import org.cms.scis.app.util.Util;
import org.cms.scis.exception.ActionProcessingBusinessException;
import org.cms.scis.exception.SCISSystemUnavailableException;
import org.cms.scis.exchange.common.vo.Errors;
import org.cms.scis.exchange.common.vo.PayloadVO;
import org.cms.scis.generate.exchange.IssuerType;
import org.cms.scis.generate.exchange.PayloadType;
import org.cms.scis.generate.exchange.ResponseCodeSimpleType;
import org.cms.scis.generate.exchange.SCIDInsuranceProductType;
import org.cms.scis.util.AttributeType;
import org.cms.scis.util.Constants;
import org.cms.scis.util.GenerateObjectUtil;
import org.switchyard.component.bean.Service;
import org.xml.sax.SAXException;

import com.cgifederal.www.IssuerProductRequest;
import com.cgifederal.www.IssuerProductResponse;

@Service(GenerateSCISWebService.class)
public class GenerateSCISIDAction extends SCISBaseAction {

	final protected Logger logger = Logger.getLogger(this.getClass().getName());

	public String process(String message) throws ActionProcessingBusinessException {
		this.logger.info("Enter GenerateSCIDAction.executeAction");

		PayloadType request = null;
		PayloadType respPayloadType = null;
		try {
			request = validateAndUnmarshallRequest(message);
		} catch (SAXException e) {
			this.logger.error("Error: ", e);
			respPayloadType = GenerateObjectUtil.createPayloadForException(Errors.REQ_XML_NOT_VALID, request);
			throw new ActionProcessingBusinessException(message, Errors.REQ_XML_NOT_VALID.getDesc(), respPayloadType);
		} catch (Throwable e) {
			this.logger.error("Error: ", e);
			respPayloadType = GenerateObjectUtil.createPayloadForException(Errors.ANY_RT_ERROR, request);
			throw new ActionProcessingBusinessException(message, Errors.ANY_RT_ERROR.getDesc(), respPayloadType);
		}
		try {
			respPayloadType = GenerateObjectUtil.shallowCopy(request);

			respPayloadType = validateRequest(respPayloadType);
			if (respPayloadType.getErrorMetadata().isEmpty()) {
				this.logger.info("calling the data layer...");

				PayloadVO reqVO = GenerateObjectUtil.constructPayloadVO(respPayloadType);

				this.userId = request.getRequestUserId();
				if ((this.userId == null) || (this.userId.trim().isEmpty())) {
					this.userId = "SYSTEM";
				}
				reqVO.setUserId(this.userId);

				PayloadVO resVO = getSCISServiceUtil().generateSCISID(reqVO);
				respPayloadType = GenerateObjectUtil.constructPayloadType(resVO, request);
			}
			this.logger.info("marshall the response");
			String responseXML = Util.marshall(respPayloadType);
			this.logger.debug("Response XML:" + responseXML);
			validateXML(responseXML);

			this.logger.info("add it to the message body");
			return responseXML;
		} catch (SAXException e) {
			this.logger.error("Error: ", e);
			respPayloadType = GenerateObjectUtil.createPayloadForException(Errors.RES_XML_NOT_VALID, request);
			throw new ActionProcessingBusinessException(message, Errors.RES_XML_NOT_VALID.getDesc(), respPayloadType);
		} catch (Throwable e) {
			this.logger.error("Error: ", e);
			respPayloadType = GenerateObjectUtil.createPayloadForException(Errors.ANY_RT_ERROR, request);
			throw new ActionProcessingBusinessException(message, Errors.ANY_RT_ERROR.getDesc(), respPayloadType);
		}
	}

	private PayloadType validateAndUnmarshallRequest(String message) throws Exception {
		long startTime = System.currentTimeMillis();
		String requestXML = (String) message;

		validateXML(requestXML);

		this.logger.info("GenerateSCISIDAction : validateAndUnmarshallRequest processed in "
				+ (System.currentTimeMillis() - startTime) + " ms.");

		return (PayloadType) Util.unmarshall(requestXML, PayloadType.class.getName());
	}

	private void validateXML(String xml) throws Exception {
		Util.isValidXml(xml, "META-INF/schemas/Generate/XMLschemas/constraint/XMLschemas/exchange/ExchangeModel.xsd");
	}

	private PayloadType validateRequest(PayloadType payload) throws SCISSystemUnavailableException {
		HIOSSCISService hiosSvc = getHiosSCISService();
		List<IssuerProductRequest> hiosReqObj = validateAndGetIssurPrdList(payload);
		if ((hiosReqObj != null) && (!hiosReqObj.isEmpty())) {
			List<IssuerProductResponse> hiosResponseList = hiosSvc.validIssuerProducts(hiosReqObj);

			return createErrorPayloadType(payload, hiosResponseList);
		}
		return payload;
	}

	private List<IssuerProductRequest> validateAndGetIssurPrdList(PayloadType payload) {
		List<IssuerType> issuerTypeList = payload.getIssuer();
		List<IssuerProductRequest> issrPrdList = new ArrayList<IssuerProductRequest>();
		boolean invalidQty = false;
		Iterator<IssuerType> issuerItr = issuerTypeList.iterator();
		while (issuerItr.hasNext()) {
			IssuerType issuerType = (IssuerType) issuerItr.next();
			List<SCIDInsuranceProductType> prdList = issuerType.getInsuranceProduct();

			Iterator<SCIDInsuranceProductType> prdItr = prdList.iterator();
			while (prdItr.hasNext()) {
				SCIDInsuranceProductType scidInsrncPrdType = (SCIDInsuranceProductType) prdItr.next();
				int qty = GenerateObjectUtil.getRequestQuantity(scidInsrncPrdType);

				IssuerProductRequest issrPrdReq = new IssuerProductRequest();
				if ((Constants.getQuantityMinRangeValue() > qty) || (qty > Constants.getQuantityMaxRangeValue())) {
					invalidQty = true;

					GenerateObjectUtil.setAttribute(scidInsrncPrdType, AttributeType.LINK_METADATA,
							Errors.INVALID_QUANTITY.getCode());
				}
				issrPrdReq.setIssuer((String) issuerType.getIssuerIdentification().getIdentificationID().getValue());
				issrPrdReq.setProduct((String) scidInsrncPrdType.getInsuranceProductIdentification()
						.getIdentificationID().getValue());

				issrPrdList.add(issrPrdReq);
			}
		}
		if (invalidQty == true) {
			GenerateObjectUtil.addErrorMetaData(payload, Errors.INVALID_QUANTITY.getCode(),
					Errors.INVALID_QUANTITY.getDesc());
		}
		return issrPrdList;
	}

	private PayloadType createErrorPayloadType(PayloadType reqPayloadType, List<IssuerProductResponse> hiosResList) {
		SCIDInsuranceProductType prdType = null;
		IssuerType issrType = null;

		Iterator<IssuerProductResponse> itr = hiosResList.iterator();
		while (itr.hasNext()) {
			IssuerProductResponse issrPrdRes = (IssuerProductResponse) itr.next();
			if ((!issrPrdRes.isIssuerValid()) || (!issrPrdRes.isProductValid())
					|| (!issrPrdRes.isIssuerProductValid())) {
				List<IssuerType> issrTypeList = reqPayloadType.getIssuer();

				Iterator<IssuerType> issrTypeItr = issrTypeList.iterator();
				while (issrTypeItr.hasNext()) {
					issrType = (IssuerType) issrTypeItr.next();
					String issuerId = issrPrdRes.getIssuer();
					String reqIssrId = GenerateObjectUtil.getIssuerId(issrType);
					if (reqIssrId.equalsIgnoreCase(issuerId)) {
						if ((!issrPrdRes.isIssuerValid()) && (issuerId != null)) {
							GenerateObjectUtil.addErrorMetaData(reqPayloadType, Errors.INVALID_ISSUER_ID.getCode(),
									Errors.INVALID_ISSUER_ID.getDesc());
							GenerateObjectUtil.setAttribute(issrType, AttributeType.LINK_METADATA,
									Errors.INVALID_ISSUER_ID.getCode());
						}
						List<SCIDInsuranceProductType> scidInsPrdTypeList = issrType.getInsuranceProduct();
						Iterator<SCIDInsuranceProductType> insPrdTypeItr = scidInsPrdTypeList.iterator();
						while (insPrdTypeItr.hasNext()) {
							prdType = (SCIDInsuranceProductType) insPrdTypeItr.next();

							String productId = issrPrdRes.getProduct();
							String reqProdId = GenerateObjectUtil.getProductId(prdType);
							if ((!issrPrdRes.isProductValid()) && (productId != null)
									&& (reqProdId.equalsIgnoreCase(productId))) {
								GenerateObjectUtil.addErrorMetaData(reqPayloadType, Errors.INVALID_PROD_ID.getCode(),
										Errors.INVALID_PROD_ID.getDesc());
								GenerateObjectUtil.setAttribute(prdType, AttributeType.LINK_METADATA,
										Errors.INVALID_PROD_ID.getCode());
							}
							if ((!issrPrdRes.isIssuerProductValid()) && (reqProdId.equalsIgnoreCase(productId))
									&& (reqIssrId.equalsIgnoreCase(issuerId))) {
								GenerateObjectUtil.addErrorMetaData(reqPayloadType,
										Errors.INVALID_ISSR_PROD_ID.getCode(), Errors.INVALID_ISSR_PROD_ID.getDesc());
								GenerateObjectUtil.setAttribute(prdType, AttributeType.LINK_METADATA,
										Errors.INVALID_ISSR_PROD_ID.getCode());
							}
						}
					}
				}
			}
		}
		GenerateObjectUtil.setResponseMetaData(reqPayloadType, ResponseCodeSimpleType.ERROR);
		return reqPayloadType;
	}
}
