package co.com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.com.example.utils.ResourceLink;

public class Author {

	private String email;
	private String fName;
	private String lName;
	
	ResourceLink links;

	public String getEmail() {
		return email;
	}

	public Author(@JsonProperty("email") String email, @JsonProperty("fName") String fName,
			@JsonProperty("lName") String lName) {
		super();
		this.email = email;
		this.fName = fName;
		this.lName = lName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public ResourceLink getSelfLink() {
		return links;
	}

	public void setSelfLink(ResourceLink selfLink) {
		this.links = selfLink;
	}

}
