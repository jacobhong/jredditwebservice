package org.jreddit.webservice.jredditwebservice.service;
/**
 * Author: Jacob Hong
 */
import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.jreddit.webservice.jredditwebservice.util.PropertiesHandler;

import com.github.jreddit.oauth.RedditOAuthAgent;
import com.github.jreddit.oauth.app.RedditWebApp;
import com.github.jreddit.oauth.client.RedditClient;

@Singleton
@Startup
@DependsOn("PropertiesHandler")
@Lock(LockType.READ)
public class RedditWebappService {
	
	@Inject 
	private PropertiesHandler propertyHandler;
	
	private RedditWebApp rwp;
	private String userAgent;
	
	@PostConstruct
	private void init()
	{
		String clientID = propertyHandler.getProperties().getProperty("clientID");
		String clientSecret = propertyHandler.getProperties().getProperty("clientSecret");
		String redirectURI = propertyHandler.getProperties().getProperty("redirectURI");
		userAgent = propertyHandler.getProperties().getProperty("userAgent");
		rwp = new RedditWebApp(clientID, clientSecret, redirectURI, userAgent);
	}
	
	public RedditClient getClient()
	{
		return rwp.getClient();
	}
	
	public String getClientID()
	{
		return rwp.getClientID();
	}
	
	public String getClientSecret()
	{
		return rwp.getClientSecret();
	}
	
	public String getRedirectURI()
	{
		return rwp.getRedirectURI();
	}
	
	public RedditOAuthAgent getAgent()
	{
		return rwp.getAgent();
	}	

	public String getUserAgent()
	{
		return userAgent;
	}

}
