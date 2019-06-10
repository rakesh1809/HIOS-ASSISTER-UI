package org.cms.scis.app.service;

import org.cms.scis.exchange.common.vo.PayloadVO;
import org.cms.scis.service.SCISService;
import org.springframework.beans.factory.annotation.Autowired;

public class SCISServiceUtil
{
  @Autowired
  private SCISService scisService;
  
  public PayloadVO validateSCISID(PayloadVO request)
    throws Exception
  {
    return this.scisService.validateSCISID(request);
  }
  
  public PayloadVO generateSCISID(PayloadVO request)
    throws Exception
  {
    return this.scisService.generateSCISID(request);
  }
  
  public PayloadVO retrieveSCISID(PayloadVO request)
    throws Exception
  {
    return this.scisService.retrieveSCISID(request);
  }
}
