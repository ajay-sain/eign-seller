package api.yourmart.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class Product {

	@Id
	@GenericGenerator(name = "sequence_dep_id", strategy = "api.yourmart.servicies.ProductIdGenerator")
	@GeneratedValue(generator = "sequence_dep_id")  
	@Column(name="productId", updatable = false, nullable = false)
	private String productId;
	private String sellerId;
	private String sellerProductId;
	private String name;
	private String shortDescription;
	private String longDescription;
	private String catagories;
	private long mrp;
	private long ssp;
	private long ymp;
	private String primaryUrl;
	private String status;
	private long height;
	private long width;
	private long length; 
	@ElementCollection(targetClass=String.class)
	private List<String> galeryUrl;
	private String usageInstructions;
	private LocalDateTime registrationTime;
	private String comment;
	

	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public LocalDateTime getRegistrationTime() {
		return registrationTime;
	}
	public void setRegistrationTime(LocalDateTime registrationTime) {
		this.registrationTime = registrationTime;
	}
	public String getUsageInstructions() {
		return usageInstructions;
	}
	public void setUsageInstructions(String usageInstructions) {
		this.usageInstructions = usageInstructions;
	}
	public List<String> getGaleryUrl() {
		return galeryUrl;
	}
	public void setGaleryUrl(List<String> galeryUrl) {
		this.galeryUrl = galeryUrl;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	
	public long getHeight() {
		return height;
	}
	public void setHeight(long height) {
		this.height = height;
	}
	public long getWidth() {
		return width;
	}
	public void setWidth(long width) {
		this.width = width;
	}
	public long getLength() {
		return length;
	}
	public void setLength(long length) {
		this.length = length;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getSellerProductId() {
		return sellerProductId;
	}
	public void setSellerProductId(String sellerProductId) {
		this.sellerProductId = sellerProductId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getLongDescription() {
		return longDescription;
	}
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	public String getCatagories() {
		return catagories;
	}
	public void setCatagories(String catagories) {
		this.catagories = catagories;
	}
	public float getMrp() {
		return mrp;
	}
	
	public long getSsp() {
		return ssp;
	}
	public void setSsp(long ssp) {
		this.ssp = ssp;
	}
	public long getYmp() {
		return ymp;
	}
	public void setYmp(long ymp) {
		this.ymp = ymp;
	}
	public void setMrp(long mrp) {
		this.mrp = mrp;
	}
	public String getPrimaryUrl() {
		return primaryUrl;
	}
	public void setPrimaryUrl(String primaryUrl) {
		this.primaryUrl = primaryUrl;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}

