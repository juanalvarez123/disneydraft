package co.com.example.services.jaw.rs;

import java.util.Collection;
import java.util.Collections;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import co.com.example.model.Author;

@Path("/authors")
@Component
public class AuthorResource {
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<Author> getAuthor() {
        return Collections.singletonList(new Author("a@b.com", "John", "Smith"));
    }
}