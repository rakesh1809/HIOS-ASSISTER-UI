package org.cms.scis.app.action;

import java.util.List;

import org.cms.scis.app.cache.ScisCacheManagerImpl;
import org.cms.scis.app.service.SCISServiceUtil;
import org.cms.scis.app.util.Util;
import org.cms.scis.exception.ActionProcessingBusinessException;
import org.cms.scis.exchange.common.vo.Errors;
import org.cms.scis.exchange.common.vo.InsurancePlanVO;
import org.cms.scis.exchange.common.vo.PayloadVO;
import org.cms.scis.exchange.common.vo.ResponseCode;
import org.cms.scis.util.ValidateObjectUtil;
import org.cms.scis.validate.exchange.PayloadType;
import org.switchyard.component.bean.Service;
import org.xml.sax.SAXException;

@Service(ValidateSCISWebService.class)
public class ValidateSCIDAction
  extends SCISBaseAction
{

  public String process(String message)
    throws ActionProcessingBusinessException
  {
    this.logger.info("Enter ValidateSCIDAction.executeAction");

    PayloadType request = null;
    try
    {
      request = validateAndUnmarshallRequest(message);
    }
    catch (SAXException e)
    {
      this.logger.error("Error: ", e);
      PayloadType respPayloadType = ValidateObjectUtil.createPayloadForException(Errors.REQ_XML_NOT_VALID, request);
      throw new ActionProcessingBusinessException(message, Errors.REQ_XML_NOT_VALID.getDesc(), respPayloadType);
    }
    catch (Throwable e)
    {
      this.logger.error("Error: ", e);
      PayloadType respPayloadType = ValidateObjectUtil.createPayloadForException(Errors.ANY_RT_ERROR, request);
      throw new ActionProcessingBusinessException(message, Errors.ANY_RT_ERROR.getDesc(), respPayloadType);
    }
    try
    {
      SCISServiceUtil scisSvcUtil = getSCISServiceUtil();
      PayloadVO reqVO = ValidateObjectUtil.payloadVORequest(request);
      PayloadVO resVO;

      if ("true".equals(System.getProperty("scis.cache.enabled"))) {
        resVO = cachedValidateScid(reqVO, scisSvcUtil);
      } else {
        resVO = scisSvcUtil.validateSCISID(reqVO);
      }
      PayloadType response = ValidateObjectUtil.payloadTypeResponse(resVO, request);

      this.logger.info("marshall the response");
      String responseXML = Util.marshall(response);
      this.logger.debug("Response XML:" + responseXML);
      validateXML(responseXML);

      this.logger.info("add it to the message body");

      return responseXML;
    }
    catch (Exception e)
    {
      this.logger.error("Error: ", e);
      PayloadType respPayloadType = ValidateObjectUtil.createPayloadForException(Errors.RES_XML_NOT_VALID, request);
      throw new ActionProcessingBusinessException(message, Errors.RES_XML_NOT_VALID.getDesc(), respPayloadType);
    }
  }

  private PayloadType validateAndUnmarshallRequest(String message)
    throws Exception
  {
    String requestXML = (String)message;
    validateXML(requestXML);
    return (PayloadType)Util.unmarshall(requestXML, PayloadType.class.getName());
  }

  private void validateXML(String xml)
    throws Exception
  {
    Util.isValidXml(xml, "META-INF/schemas/Validate/XMLschemas/constraint/XMLschemas/exchange/ExchangeModel.xsd");
  }

  private PayloadVO cachedValidateScid(PayloadVO reqVO, SCISServiceUtil scisSvcUtil)
    throws Exception
  {
    List<InsurancePlanVO> reqList = reqVO.getInsurancePlanTypeVO();
    boolean allInCache = true;
    for (InsurancePlanVO req : reqList) {
      if (ScisCacheManagerImpl.getInstance().get("/ValidateSCID", req.getInsurancePlanStandardComponentIdentification()) != null)
      {
        this.logger.debug("found SCID in cache");
        req.setStatus(true);
      }
      else
      {
        allInCache = false;
      }
    }
    PayloadVO resVO = reqVO;
    if (allInCache)
    {
      this.logger.info("All SCID in cache so no data call");
      resVO.setResponseCode(ResponseCode.SUCCESS);
    }
    else
    {
      this.logger.info("calling data layer");
      resVO = scisSvcUtil.validateSCISID(reqVO);

      List<InsurancePlanVO> dbReqList = resVO.getInsurancePlanTypeVO();
      for (InsurancePlanVO req : dbReqList) {
        if (req.isStatus()) {
          ScisCacheManagerImpl.getInstance().add("/ValidateSCID", req.getInsurancePlanStandardComponentIdentification(), req.getInsurancePlanStandardComponentIdentification());
        }
      }
    }
    return resVO;
  }
}
