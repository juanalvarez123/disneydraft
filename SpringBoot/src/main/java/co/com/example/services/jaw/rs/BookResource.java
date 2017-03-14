package co.com.example.services.jaw.rs;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.example.dao.CategoryDAO;
import co.com.example.model.Book;
import co.com.example.utils.ResourceLink;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Path("/books")
@Api(value = "/books")
@Component
public class BookResource {

	@Context
	UriInfo uriInfo;

	@Autowired
	public CategoryDAO categoryDAO;

	@GET
	@Path("/book/{id}")
	@ApiOperation(value = "Get a Book by ID", notes = "Query a book")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "Book's ID", required = true, dataType = "string", paramType = "/{id}", defaultValue="001")
      })
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = Book.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")}) 
	@Produces({ MediaType.APPLICATION_JSON })
	public Book getBook(@PathParam("id") String id) {
		Book book = categoryDAO.getBook(id);
		javax.ws.rs.core.Link links = javax.ws.rs.core.Link.fromUri(uriInfo.getBaseUriBuilder().path(BookResource.class)
				.path(BookResource.class, "getBook").build(book.getBookId())).rel("book").type("GET").build();
		book.setSelfLink(new ResourceLink(links));
		return book;
	}

	@GET
	@Path("/book")
	@Produces({ MediaType.APPLICATION_JSON })
	public Book getBook2(@QueryParam("id") String id) {
		Book book = categoryDAO.getBook(id);
		javax.ws.rs.core.Link links = javax.ws.rs.core.Link.fromUri(uriInfo.getBaseUriBuilder().path(BookResource.class)
				.path(BookResource.class, "getBook").build(book.getBookId())).rel("book").type("GET").build();
		book.setSelfLink(new ResourceLink(links));
		return book;
	}
	
	@PUT
	@Path("/book/batch")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response executeBatch() {

		return Response.status(Status.ACCEPTED).entity("The task has ben launched.").build(); 
	}
}
