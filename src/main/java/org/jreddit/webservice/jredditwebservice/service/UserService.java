package org.jreddit.webservice.jredditwebservice.service;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.inject.Inject;

import org.jreddit.webservice.jredditwebservice.model.Multi;
import org.jreddit.webservice.jredditwebservice.model.Multireddits;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.jreddit.oauth.RedditToken;
import com.github.jreddit.oauth.exception.RedditOAuthException;
import com.github.jreddit.oauth.param.RedditDuration;
import com.github.jreddit.oauth.param.RedditScope;
import com.github.jreddit.oauth.param.RedditScopeBuilder;
import com.github.jreddit.request.action.multi.CopyMultisRequest;
import com.github.jreddit.request.action.multi.MakeMultisPublicRequest;
import com.github.jreddit.request.retrieval.multireddits.MultiredditsRequest;
import com.github.jreddit.request.retrieval.username.UsernameRequest;



/**
 * 
 * @author Jacob Hong
 *
 */
 


@Stateful
public class UserService {
	
	    @Inject
	    private Logger log;
	    
	    @Inject
	    private RedditWebappService rwp;
        
	    private RedditToken token;
	    
	    public String getUserToken()
	    {                    
	    	RedditScopeBuilder scopeBuilder = new RedditScopeBuilder();
	    	scopeBuilder.addScope(RedditScope.IDENTITY);
	    	scopeBuilder.addScope(RedditScope.READ);
	    	scopeBuilder.addScope(RedditScope.EDIT);
	    	scopeBuilder.addScope(RedditScope.MYSUBREDDITS);
	    	scopeBuilder.addScope(RedditScope.SUBSCRIBE);
	    	String userTokenURI = rwp.getAgent().generateCodeFlowURI(scopeBuilder, RedditDuration.PERMANENT);
	    	return userTokenURI;
	    }
	    
	    /**
	     * Description: Retrieve token from user and create a Reddit Token
	     * @param code
	     * @return
	     * @throws Exception
	     */
	    public void createAccessToken (String code) throws RedditOAuthException 
	    {	        
	        token = rwp.getAgent().token(code);
	        log.log(Level.SEVERE, "TOKEN CREATED : " + token.getAccessToken());
	    }
	    
	    /**
	     * Description: Get a users multireddits and makes them public so we
	     * can copy them
	     * @return
	     * @throws Exception
	     */
	    public void makeMultiredditsPublic () throws Exception 
	    {	        
	        String username = getUsername();
	        String multis = getMultis(username);	      
	        Multireddits[] s = parseJson(multis);	
	        makePublic(s);	        
	    }
	    
	    public String copyUserMultis() throws RedditOAuthException 
	    {
	        log.log(Level.SEVERE, "COPYUSERMULTI TOKEN : " + token.getAccessToken());

	    	CopyMultisRequest request = new CopyMultisRequest();
	    	String multis = rwp.getClient().post(token, request);
	    	return multis;
	    }
	    
	    private String getUsername() throws ParseException, org.json.simple.parser.ParseException
		{
			UsernameRequest usernameRequest = new UsernameRequest();
			String usernameJSON = rwp.getClient().get(token, usernameRequest);
			
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = ((JSONObject)parser.parse(usernameJSON));
			
			String username = (String) jsonObject.get("name");
			return username;
		}

		private String getMultis(String username) {
			MultiredditsRequest request = new MultiredditsRequest(username);
		    String multis = rwp.getClient().get(token, request);
			return multis;
		}

		private void makePublic(Multireddits[] m) throws JsonProcessingException
		{
		
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
			for(Multireddits multis : m)
			{
				Multi multi = multis.getData();
				multi.setVisibility("public");
				
				String path = multi.getPath();
				String json = ow.writeValueAsString(multi);
				
		        MakeMultisPublicRequest request = new MakeMultisPublicRequest(path, json);	
		        rwp.getClient().put(token, request);
			}	    	    	
		}
		
		private void makePrivate(Multireddits[] m, RedditToken token) throws JsonProcessingException
		{
		
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
			for(Multireddits multis : m)
			{
				Multi multi = multis.getData();
				multi.setVisibility("private");
				
				String path = multi.getPath();
				String json = ow.writeValueAsString(multi);
				
		        MakeMultisPublicRequest request = new MakeMultisPublicRequest(path, json);	
		        rwp.getClient().put(token, request);
			}	    	    	
		}

		private Multireddits[] parseJson(String json) throws Exception
	    {	    				
	    	Multireddits[] m = null;
	    	ObjectMapper mapper = new ObjectMapper();
	    		
	    	try 
	    	{	    		
	    		m = mapper.readValue(json, Multireddits[].class);    			
	    			 
	    	} 
	    	catch (Exception e)
	    	{
	    		throw e;
	    	}
	    		
			return m;	    	
	    }
	    
}
