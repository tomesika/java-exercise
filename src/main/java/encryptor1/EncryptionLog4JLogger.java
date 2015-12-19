package encryptor1;

import org.apache.log4j.Logger;


public class EncryptionLog4JLogger 
{
	public static org.apache.log4j.Logger logger =Logger.getLogger(EncryptionLog4JLogger.class.getName());
	public void info(String msg)
	{
		logger.info(msg);
	}
	public void logError(String msg) 
	{
		logger.error(msg);
	}
	public void fatal(String msg)
	{	
		logger.fatal(msg);
	}
	public void debug(String msg)
	{
		logger.debug(msg);
	}
	
}


