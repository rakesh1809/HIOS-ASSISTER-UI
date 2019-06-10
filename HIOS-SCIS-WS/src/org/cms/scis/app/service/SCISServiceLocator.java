package org.cms.scis.app.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SCISServiceLocator implements ApplicationContextAware {

    private static ApplicationContext ctx = null;
    private static SCISServiceLocator instance = null;
    
    public SCISServiceLocator() {
    }

    private SCISServiceLocator(ApplicationContext ctx){
	this.ctx =ctx;
    }


    @Override
    public void setApplicationContext(ApplicationContext ctx)
	    throws BeansException {
	this.ctx = ctx;

    }

    public synchronized static SCISServiceLocator getInstance(){
	if (instance != null) {
		return instance;
	}
	instance = new SCISServiceLocator(ctx);
	return instance;
    }

	public SCISServiceUtil getSCISServiceUtil(){
		return (SCISServiceUtil)ctx.getBean("scisServiceUtil");
	}
	
	public HIOSSCISService getHiosSCISService(){
		return (HIOSSCISService)ctx.getBean("hiosScisService");
	}
	
}

