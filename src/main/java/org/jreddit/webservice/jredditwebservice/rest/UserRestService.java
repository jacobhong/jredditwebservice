package org.jreddit.webservice.jredditwebservice.rest;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
/**
 * 
 * @author Jacob Hong
 *
 */
import javax.ws.rs.core.UriInfo;

import org.jreddit.webservice.jredditwebservice.service.UserService;

import com.github.jreddit.oauth.exception.RedditOAuthException;

@Path("/userService")
public class UserRestService {

	    @Inject
	    private Logger log;	    
	    
	    @Inject
	    private UserService userService;

	    @GET
	    @Path("/token")
	    @Produces(MediaType.TEXT_PLAIN)
	    public String getUserToken()
	    {            
	    	String token = userService.getUserToken();
	        return token; 
	    }	    
	    
	    @GET
	    @Path("/accessToken")
	    @Produces({MediaType.TEXT_HTML})
	    public Response createAccessToken(@QueryParam("code") String code) throws Exception
	    {
        	  // Information about the app
            ResponseBuilder builder = null;
            try
            {
            	userService.createAccessToken(code);
            	builder = Response.ok();            	
            }
            catch(RedditOAuthException e) 
            {
            	Map<String, String> responseObj = new HashMap<>();
 	            responseObj.put("error", e.getMessage());
 	            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
            }
            return Response.seeOther(new URI("http://localhost:8080/jredditwebservice/accessToken.html")).build();
	    }	    
	 
	    @POST
	    @Path("/visibility")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response makeMultiredditsPublic() 
	    {
        	  // Information about the app
            ResponseBuilder builder = null;
	        try
	        {
	        	userService.makeMultiredditsPublic();
	        	builder = Response.ok(200);
	        } catch (RedditOAuthException e)
	        {
	            // Handle generic exceptions
	            Map<String, String> responseObj = new HashMap<>();
	            responseObj.put("error", e.getMessage());
	            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        return builder.build();
	    }	    
	    
	    @POST
	    @Path("/copy")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response copyMultis()
	    {
	   	  // Information about the app
            ResponseBuilder builder = null;
	        try
	        {
	        	userService.copyUserMultis();
	        	builder = Response.ok(200);
	        } catch (Exception e)
	        {
	            // Handle generic exceptions
	            Map<String, String> responseObj = new HashMap<>();
	            responseObj.put("error", e.getMessage());
	            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
	        }

	        return builder.build();
	    }
	
}

