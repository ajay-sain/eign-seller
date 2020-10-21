	package api.yourmart.controllers.seller;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

//import org.hibernate.Query;
import org.hibernate.Session;
//import org.hibernate.query.Query;

import api.yourmart.filters.Sort;
import api.yourmart.models.Product;
import api.yourmart.servicies.HibernetConnector;

@Path("products")
public class ShopController {
	
	@DELETE
	public Response deleteProduct(Product product) {//
		try{
			Session session = HibernetConnector.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(product);
			session.getTransaction().commit();
			session.close();
			return Response.status(Status.OK)
				      .entity("product deleted successfully")
				      .build();
		}
		catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
				      .entity("INTERNAL SERVER ERROR")
				      .build();
		}
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/{start}/{size}")
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getProduct( @PathParam("id") String id,@PathParam("start") long start, @PathParam("size") long size,@QueryParam("string") String string,@QueryParam("orderBy") String orderBy,@QueryParam("desc") Boolean desc) {
		try {
			Session session = HibernetConnector.getSessionFactory().openSession();
//			session.beginTransaction();
			String queryString = "SELECT p FROM Product p WHERE p.sellerId =:id";
			if(string != null && !string.equals("")) {
				queryString +=  " and p.sellerProductId like :string or p.name like :string or p.status like :string or p.catagories like :string";
			}
			Query query = session.createQuery(queryString);
			if(string != null && !string.equals("")) {
				query.setParameter("string", "%"+string+"%");
			}
			query.setParameter("id", id);
			query.setMaxResults((int) size);
			query.setFirstResult((int) start);//("start", start);
			List<Product> products = query.getResultList();
			if(orderBy != null && !orderBy.equals("")) {
				products.sort(new Sort(orderBy,desc));
			}
			
			queryString = "SELECT count(*) as records FROM Product p WHERE p.sellerId =:id ";
			if(string != null && !string.equals("")) {
				queryString +=  " and p.sellerProductId like :string or p.name like :string or p.status like :string or p.catagories like :string";
			}
			query = session.createQuery(queryString);
			
			query.setParameter("id", id);
			Long count = (Long)query.getSingleResult();
			ProductResponse response = new ProductResponse();
			response.setProductList(products);
			response.setTotalRows(count);
//			session.getTransaction().commit();
//			session.close();
			return Response.status(Status.OK)
				      .entity(response)
				      .build();
		}
		catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
				      .entity("INTERNAL SERVER ERROR")
				      .build();
		}
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerProduct(Product product) {
		if(product == null)
			return Response.status(Status.BAD_REQUEST).entity("product details are not satisphied please connect to backend people").build();
		try {
			product.setRegistrationTime(LocalDateTime.now());
			Session session = HibernetConnector.getSessionFactory().openSession();
			product.setStatus("NEW");
			session.beginTransaction();
			session.saveOrUpdate(product);
			session.getTransaction().commit();
			session.close();
			return Response.status(Status.OK).entity("product saved successfully").build();
		}
		catch(Exception e) {
			System.out.println(e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Please connect to the backend people").build();
		}
	}
}
