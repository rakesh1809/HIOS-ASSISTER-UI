package org.cms.scis.app.action;

import org.cms.scis.exception.ActionProcessingBusinessException;
import org.switchyard.annotations.OperationTypes;

public abstract interface GenerateSCISWebService
{
  @OperationTypes(in="{http://example.ffe.cms.gov/exchange/1.0}Payload", out="{http://example.ffe.cms.gov/exchange/1.0}Payload")
  public abstract String process(String paramString)
    throws ActionProcessingBusinessException;
}
