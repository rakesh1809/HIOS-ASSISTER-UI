package org.cms.scis.app.action;

import org.apache.log4j.Logger;
import org.cms.scis.app.service.HIOSSCISService;
import org.cms.scis.app.service.SCISServiceLocator;
import org.cms.scis.app.service.SCISServiceUtil;
import org.jboss.soa.esb.actions.AbstractSpringAction;
import org.jboss.soa.esb.actions.ActionLifecycleException;
import org.jboss.soa.esb.actions.annotation.Process;
import org.jboss.soa.esb.message.Message;
import org.springframework.beans.factory.BeanFactory;

public abstract class SCISBaseAction
implements ValidateSCISWebService, RetrieveSCISWebService, GenerateSCISWebService
{
  protected final Logger logger = Logger.getLogger(getClass().getName());
  protected String userId = null;
  protected static final String DEFAULT_USERID = "SYSTEM";
  protected static final String TOKEN = "SAML_TOKEN";

  protected SCISServiceUtil getSCISServiceUtil()
  {
    return (SCISServiceUtil)SCISServiceLocator.getInstance().getSCISServiceUtil();
  }

  protected HIOSSCISService getHiosSCISService()
  {
    return (HIOSSCISService)SCISServiceLocator.getInstance().getHiosSCISService();
  }
}
