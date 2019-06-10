package org.cms.scis.app.action;


import org.cms.scis.exception.ActionProcessingBusinessException;
import org.switchyard.annotations.OperationTypes;

public interface ValidateSCISWebService {
    
    @OperationTypes(in = "{http://example.ffe.cms.gov/exchange/1.0}Payload", out = "{http://example.ffe.cms.gov/exchange/1.0}Payload")
    String process(String message) throws ActionProcessingBusinessException;
    
}
