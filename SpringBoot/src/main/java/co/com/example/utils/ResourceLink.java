package co.com.example.utils;

public class ResourceLink {

	private String rel;
	private String href;
	private String type;

	public ResourceLink(){};
	
	public ResourceLink(javax.ws.rs.core.Link links){
		this.rel = links.getRel();
		this.href = links.getUri().toString();
		this.type = links.getType();
	};
	
	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
