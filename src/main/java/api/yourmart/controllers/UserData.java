package api.yourmart.controllers;

import javax.persistence.EntityManager;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Session;

import api.yourmart.models.Seller;
import api.yourmart.servicies.HibernetConnector;
import javax.persistence.Query;
@Path("/")
public class UserData {
	
	@Path("users/" + "{sellerId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserData(@PathParam("sellerId") String sellerId) {
		try {
			Session session = HibernetConnector.getSessionFactory().openSession();
			String queryString = "SELECT p FROM Seller p WHERE p.sellerId =:id";
			Query query = session.createQuery(queryString);
			query.setParameter("id", sellerId);
			Seller seller =  (Seller) query.getResultList().get(0);
			session.close();
			return Response.status(Status.OK)
				      .entity(seller)
				      .build();
			}
			catch(Exception e) {
				return Response.status(Status.INTERNAL_SERVER_ERROR)
					      .entity("INTERNAL SERVER ERROR")
					      .build();
			}
	}

}
