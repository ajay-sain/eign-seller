package api.yourmart.controllers;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Session;

import api.yourmart.constants.CommonConstants;
import api.yourmart.models.JWTUserCredentials;
import api.yourmart.models.Seller;
import api.yourmart.models.UserLogin;
import api.yourmart.servicies.AuthenticationService;
import api.yourmart.servicies.HibernetConnector;

@Path("authentication")
public class Authentication {

	
	@GET
	@Path("login")
	public Response getLogin() {
		return Response
			      .status(Status.OK)
			      .entity("not a registerd user please generate a request")
			      .build();
	}
	
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postLogin(UserLogin user) {
		if(user==null) {
			return Response
				      .status(Response.Status.OK)
				      .entity("please provide user credentials")
				      .build();
		}
		try {
			Session session = HibernetConnector.getSessionFactory().openSession();
//			EntityManager entityManager = session.getEntityManagerFactory().createEntityManager();
//			Seller seller = entityManager.find(Seller.class,user.getSellerId());
			Seller seller = session.get(Seller.class,user.getSellerId());
			if(seller==null) {
				return Response
					      .status(Response.Status.OK)
					      .entity("not a registerd user please generate a request")
					      .build();
			}
			else {
				if(seller.getPassword().equals(user.getPassword())) {
					seller.setPassword(null);
					
					JWTUserCredentials credentials = new JWTUserCredentials();
					credentials.setId(seller.getSellerId());
					credentials.setRole("SELLER");
					
					if(seller.getStatus().equals("NEED_APPROVAL")) {
						return Response
							      .status(Response.Status.UNAUTHORIZED)
							      .entity("Your registration is not yet approved.")
							      .build();
					}
					if(seller.getStatus().equals("REJECTED")) {
						return Response
							      .status(Response.Status.UNAUTHORIZED)
							      .entity("Your registration has been cancelled, Please contact to the YourMart seller services team.")
							      .build();
					}
					
					AuthenticationService authService = new AuthenticationService();
					return Response.status(Status.OK).header(CommonConstants.AUTHENTICATION_HEADER, "Bearer "+authService.generateToken(credentials))
						     .cookie(new NewCookie(CommonConstants.AUTHENTICATION_HEADER, "Bearer "+authService.generateToken(credentials)))  
							.entity(seller)
						      .build();						      
				}
				else {
					return Response
						      .status(Response.Status.UNAUTHORIZED)
						      .entity("wrong password! please try again")
						      .build();
				}
			}
		}catch (Exception e) {
			System.out.println(e);
			return Response
				      .status(Response.Status.INTERNAL_SERVER_ERROR)
				      .entity("server error")
				      .build();
		}
	}
	
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerSeller(Seller seller) {
		seller.setStatus("NEED_APPROVAL");
		seller.setRegistrationTime(LocalDateTime.now());
		try {
			Session session = HibernetConnector.getSessionFactory().openSession();
			System.out.println(session);
			session.beginTransaction();
			session.saveOrUpdate(seller);
			session.getTransaction().commit();
			session.close();
				return Response.status(Status.OK).entity("request generated for new seller").build();
		}catch (Exception e) {
			System.out.println(e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("seller not saved please try again").build();
		}
	}
	
	
}
