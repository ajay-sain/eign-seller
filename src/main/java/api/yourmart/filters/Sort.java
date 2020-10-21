package api.yourmart.filters;

import java.util.Comparator;

import api.yourmart.models.Product;

public class Sort implements Comparator<Product> {

	private String orderBy;
	private boolean desc = false;

	public Sort(String orderBy, boolean desc) {
		// TODO Auto-generated constructor stub
		this.orderBy = orderBy;
		this.desc = desc;
	}

	@Override
	public int compare(Product o1, Product o2) {
		// TODO Auto-generated method stub
		int response = 0;
		if(this.orderBy.equals("mrp")) {
			response = o1.getMrp()<o2.getMrp()?-1:1;
		}
		else if(this.orderBy.equals("ymp")) {
			response = o1.getYmp()<o2.getYmp()?-1:1;
		}
		else if(this.orderBy.equals("ssp")) {
			response = o1.getSsp()<o2.getSsp()?-1:1;
		}else {
			
		}
		
		
		if(this.desc) {
			response *=-1;
		}
		return response;
	}

}
