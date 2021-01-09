package ie.gmit.sw;

public class Users {
private String businessName;
private String businessID;
private String email;

public Users(String businessName, String businessID, String email) {
	super();
	this.businessName = businessName;
	this.businessID = businessID;
	this.email = email;
}
public String getBusinessName() {
	return businessName;
}
public void setBusinessName(String businessName) {
	this.businessName = businessName;
}
public String getBusinessID() {
	return businessID;
}
public void setBusinessID(String businessID) {
	this.businessID = businessID;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

}

