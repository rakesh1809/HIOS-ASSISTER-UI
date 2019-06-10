package org.cms.scis.exception;

public class SCISSystemUnavailableException
  extends Exception
{
  public SCISSystemUnavailableException() {}
  
  public SCISSystemUnavailableException(String errorCode)
  {
    super(errorCode);
  }
  
  public SCISSystemUnavailableException(String errorCode, String errorMessage)
  {
    super(errorCode);
  }
  
  public SCISSystemUnavailableException(Throwable exception)
  {
    super(exception);
  }
  
  public SCISSystemUnavailableException(String errorCode, Throwable exception)
  {
    super(errorCode, exception);
  }
}
