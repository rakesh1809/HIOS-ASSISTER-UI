package org.cms.scis.app.service;

import com.cgifederal.www.BasicHttpBinding_IHIOSSCISServiceStub;
import com.cgifederal.www.IssuerProductRequest;
import com.cgifederal.www.IssuerProductResponse;
import com.cgifederal.www.IssuerResponse;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.axis.client.Service;
import org.apache.log4j.Logger;
import org.cms.scis.exception.SCISSystemUnavailableException;

public class HIOSSCISService
{
  private static final Logger logger = Logger.getLogger(HIOSSCISService.class);
  private static String scisUrl = "scis.hiosurl";
  
  public List<IssuerResponse> validIssuers(List<String> issuerIds)
    throws SCISSystemUnavailableException
  {
    logger.info("Enter HiosSCISService.isValidIssuer");
    List<IssuerResponse> hiosResponseList = new ArrayList();
    try
    {
      logger.debug("Calling HIOS SCIS sevice - isValidIssuer()");
      IssuerResponse[] response = createHiosStub().validIssuers((String[])issuerIds.toArray(new String[issuerIds.size()]));
      if (response != null) {
        hiosResponseList = Arrays.asList(response);
      }
    }
    catch (Exception e)
    {
      logger.error("Error in calling HIOS SCIS Service - isValidIssuer:" + e.getMessage());
      throw new SCISSystemUnavailableException("System currently Unavailable,Please contact Help Desk", e);
    }
    logger.info("Exit HiosSCISService.isValidIssuer");
    return hiosResponseList;
  }
  
  public List<IssuerProductResponse> validIssuerProducts(List<IssuerProductRequest> issrPrdList)
    throws SCISSystemUnavailableException
  {
    logger.info("Enter HiosSCISService.isValidIssuerProduct");
    
    List<IssuerProductResponse> responseList = new ArrayList();
    try
    {
      logger.debug("Calling HIOS SCIS sevice - isValidIssuerProduct()");
      IssuerProductResponse[] response = createHiosStub().validIssuersProducts((IssuerProductRequest[])issrPrdList.toArray(new IssuerProductRequest[issrPrdList.size()]));
      if (response != null) {
        responseList = Arrays.asList(response);
      }
    }
    catch (Exception e)
    {
      logger.error("Error in calling HIOS SCIS Service - isValidIssuerProduct:" + e.getMessage());
      throw new SCISSystemUnavailableException("System currently Unavailable,Please contact Help Desk", e);
    }
    logger.info("Exit HiosSCISService.isValidIssuerProduct");
    return responseList;
  }
  
  private static BasicHttpBinding_IHIOSSCISServiceStub createHiosStub()
    throws Exception
  {
    String svcURL = System.getProperty(scisUrl);
    logger.info("HIOS SCIS Service url: " + svcURL);
    if (svcURL == null) {
      throw new SCISSystemUnavailableException("HIOS Service URL is not available");
    }
    BasicHttpBinding_IHIOSSCISServiceStub stub = new BasicHttpBinding_IHIOSSCISServiceStub(new URL(svcURL), new Service());
    
    return stub;
  }
}
