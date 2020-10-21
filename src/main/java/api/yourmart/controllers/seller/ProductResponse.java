package api.yourmart.controllers.seller;

import java.util.List;

import api.yourmart.models.Product;

public class ProductResponse {
	private long totalRows;
	private List<Product> productList;
	public long getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	
	
}
