package api.yourmart.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDateTime;


@Entity
@Table
public class Seller {
	@Id
	@GenericGenerator(name = "sequence_dep_id", strategy = "api.yourmart.servicies.SellerIdGenerator")
	@GeneratedValue(generator = "sequence_dep_id")  
	@Column(name="sellerId", updatable = false, nullable = false)
	private String sellerId;
	private String companyName;
	private String ownerName;
	private String address;
	private String email;
	private long telephone;
	private String gstNumber;
	private String password;
	//@Column( nullable = false,columnDefinition = " default testing ")
	private String status="NEED_APPROVAL";
	private LocalDateTime registrationTime;
	
	
	
	public LocalDateTime getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(LocalDateTime registrationTime) {
		this.registrationTime = registrationTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSellerId() {
		return sellerId;
	}
	
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getTelephone() {
		return telephone;
	}
	public void setTelephone(long telephone) {
		this.telephone = telephone;
	}
	
	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Seller [sellerId=" + sellerId + ", companyName=" + companyName + ", ownerName=" + ownerName
				+ ", address=" + address + ", email=" + email + ", telephone=" + telephone + ", GSTNumber=" + gstNumber
				+ ", password=" + password + ", verificatioStatus=" + status + "]";
	}
	
}