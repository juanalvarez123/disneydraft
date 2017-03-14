package co.com.example.services.jaw.rs;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.example.dao.CategoryDAO;
import co.com.example.model.Book;
import co.com.example.model.Category;
import co.com.example.utils.ResourceLink;
import io.swagger.annotations.Api;

@Path("/categories")
@Api(value = "/categories")
@Component
public class CategoryResource {

	@Context
	UriInfo uriInfo;

	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	public CategoryDAO categoryDAO;

	@GET
	@Path("/category/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Category getCategory(@PathParam("id") String id) {
		Category cat = categoryDAO.getCategory(id);
		return cat;
	}

	@GET
	@Path("/category2/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Category getCategory2(@PathParam("id") String id) {

		Category cat = categoryDAO.getCategory(id);
		for (Book book : cat.getBooks()) {

			// Generate a simple link
			// ControllerLinkBuilder.linkTo(BookService.class).slash(book.getBookId()).withSelfRel();
			// org.springframework.hateoas.Link selfLink =
			// JaxRsLinkBuilder.linkTo(BookResource.class).withRel("book");

			javax.ws.rs.core.Link links = javax.ws.rs.core.Link.fromUri(uriInfo.getBaseUriBuilder()
					.path(BookResource.class).path(BookResource.class, "getBook").build(book.getBookId())).rel("book")
					.type("GET").build();
			book.setSelfLink(new ResourceLink(links));
		}
		javax.ws.rs.core.Link links = javax.ws.rs.core.Link
				.fromUri(uriInfo.getBaseUriBuilder().path(CategoryResource.class)
						.path(CategoryResource.class, "getCategory2").build(cat.getCategoryId()))
				.rel("category").type("GET").build();
		cat.setLinks(new ResourceLink(links));
		return cat;
	}

	@POST
	@Path("/category/add")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void addCategory(Category category) {
		System.out.println("adding a new category");
	}

	@POST
	@Path("/category2/add")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response addCategory2(Category category) {
		String json = null;
		if (category == null) {

			//return Response.serverError().entity("category cannot be blank").build();

			// return Response.status(Status.BAD_REQUEST).build();
			
			return Response.status(Status.BAD_REQUEST).entity("category cannot be blank").build(); 
		}
		try {
			json = mapper.writeValueAsString(category);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return Response.serverError().entity("Error While parsing to json the response").build();
		}
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}

	@DELETE
	@Path("/category/{id}")
	public void deleteCategory(@PathParam("id") String id) {
	}

	@PUT
	@Path("/category")
	public void updateCategory(Category category) {
	}

	@POST
	@Path("/category/book")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void addBooks(Category category) {
	}

	@GET
	@Path("/category/{id}/books")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void getBooks(@PathParam("id") String id) {
	}

}
