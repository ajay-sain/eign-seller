package api.yourmart.controllers.seller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import api.yourmart.models.Product;

@Path("status")
public class ProductController {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response changeProductStatus(Product product) {
		System.out.println(product.getProductId());
		return Response.status(Status.OK)
				.entity("status is updated")
				.build();
	}
}
