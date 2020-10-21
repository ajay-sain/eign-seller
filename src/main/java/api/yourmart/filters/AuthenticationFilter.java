package api.yourmart.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import api.yourmart.constants.CommonConstants;
import api.yourmart.servicies.AuthenticationService;

@PreMatching
@Provider
public class AuthenticationFilter implements ContainerRequestFilter{
	
	
	
	
	
	@Override
	public void filter(ContainerRequestContext containerRequest) throws IOException {
		// TODO Auto-generated method stub
		
		UriInfo url = containerRequest.getUriInfo();
		System.out.println(url);
		String authCredentials = containerRequest
				.getHeaderString(CommonConstants.AUTHENTICATION_HEADER);
		
		if(url.getPath().contains("authentication")) {
			return;
		}
		
		
		
		System.out.println(authCredentials + "     &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&" + CommonConstants.AUTHENTICATION_HEADER);
		AuthenticationService authenticationService = new AuthenticationService();
		if((authCredentials!=null && authenticationService.authenticate(authCredentials))) {
			return;
		}
		
		
		containerRequest.abortWith(Response.status(Status.UNAUTHORIZED)
											.entity("Unauthorized please try after login")
											.build());
	}

	

}
