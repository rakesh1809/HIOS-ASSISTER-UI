package org.cms.scis.app.action;

import org.cms.scis.app.service.SCISServiceUtil;
import org.cms.scis.app.util.Util;
import org.cms.scis.exception.ActionProcessingBusinessException;
import org.cms.scis.exchange.common.vo.Errors;
import org.cms.scis.exchange.common.vo.PayloadVO;
import org.cms.scis.retrieve.exchange.PayloadType;
import org.cms.scis.util.RetrieveObjectUtil;
import org.switchyard.component.bean.Service;
import org.xml.sax.SAXException;

@Service(RetrieveSCISWebService.class)
public class RetrieveSCISIDAction
  extends SCISBaseAction
{

  public String process(String message)  throws ActionProcessingBusinessException
  {
    this.logger.info("Enter RetrieveSCIDAction.executeAction");

    PayloadType request = null;
    PayloadType respPayloadType = null;
    PayloadVO resVO = null;
    try
    {
      request = validateAndUnmarshallRequest(message);
    }
    catch (SAXException e)
    {
      this.logger.error("Error: ", e);
      respPayloadType = RetrieveObjectUtil.createPayloadForException(Errors.REQ_XML_NOT_VALID, request);
      throw new ActionProcessingBusinessException(message, Errors.REQ_XML_NOT_VALID.getDesc(), respPayloadType);
    }
    catch (Throwable e)
    {
      this.logger.error("Error: ", e);
      respPayloadType = RetrieveObjectUtil.createPayloadForException(Errors.ANY_RT_ERROR, request);
      throw new ActionProcessingBusinessException(message, Errors.ANY_RT_ERROR.getDesc(), respPayloadType);
    }
    try
    {
      SCISServiceUtil scisSvcUtil = getSCISServiceUtil();

      this.logger.debug("Constructing PayloadVO Request.....");
      PayloadVO reqVO = RetrieveObjectUtil.constructPayloadVO(request);

      this.logger.debug("Calling Data Layer....");
      resVO = scisSvcUtil.retrieveSCISID(reqVO);

      this.logger.debug("Constructing PayloadType Response....");
      respPayloadType = RetrieveObjectUtil.constructPayloadType(resVO, request);

      this.logger.debug("marshall the response");
      String responseXML = Util.marshall(respPayloadType);
      this.logger.debug("Response XML:" + responseXML);
      validateXML(responseXML);

      this.logger.info("Exit RetrieveSCIDAction.executeAction Succesfully...");
      return responseXML;
    }
    catch (SAXException e)
    {
      this.logger.error("Error: ", e);
      respPayloadType = RetrieveObjectUtil.createPayloadForException(Errors.RES_XML_NOT_VALID, request);
      throw new ActionProcessingBusinessException(message, Errors.RES_XML_NOT_VALID.getDesc(), respPayloadType);
    }
    catch (Throwable e)
    {
      this.logger.error("Error: ", e);
      respPayloadType = RetrieveObjectUtil.createPayloadForException(Errors.ANY_RT_ERROR, request);
      throw new ActionProcessingBusinessException(message, Errors.ANY_RT_ERROR.getDesc(), respPayloadType);
    }
  }

  private PayloadType validateAndUnmarshallRequest(String message)
    throws Exception
  {
    long startTime = System.currentTimeMillis();

    String requestXML = (String)message;

    validateXML(requestXML);

    this.logger.info("RetrieveSCISIDAction : validateAndUnmarshallRequest processed in " + (System.currentTimeMillis() - startTime) + " ms.");
    return (PayloadType)Util.unmarshall(requestXML, PayloadType.class.getName());
  }

  private void validateXML(String xml)
    throws Exception
  {
    Util.isValidXml(xml, "META-INF/schemas/Retrieve/XMLschemas/constraint/XMLschemas/exchange/ExchangeModel.xsd");
  }
}
