/**
 * @author Jacob Hong 
 */
package org.jreddit.webservice.jredditwebservice.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * @author JacobHong
 *	Description: A singleton Properties object with access to key value pairs from a .properties file
 */
@Startup
@Singleton
public class PropertiesHandler {
	
	private Properties prop = new Properties();	

	  @PostConstruct
	  private void init()
	  {
		  InputStream is = PropertiesHandler.class.getClassLoader().getResourceAsStream("config.properties");
		  
		  if (is != null)
		  {
			  try 
			  {
				prop.load(is);
			  } 
			  catch (IOException e)
			  {
				throw new RuntimeException("Could not load properties file.");
			  }		
			  finally 
			  {
				  if (is != null)
				  {
					 try 
					 {
						 is.close();
					 }
					 catch(IOException ee)
					 {
						 throw new RuntimeException("Failed to close input stream with properties file");
					 }
				  }
			  }
		  }
	  }
	
	  @Lock(LockType.READ)
	  public Properties getProperties()
	  {
		  return prop;
	  }


}
