package be.craftsmen.fiazardtje.resource;

import be.craftsmen.fiazardtje.common.Id;
import be.craftsmen.fiazardtje.representation.category.CategoryR;
import be.craftsmen.fiazardtje.representation.error.ErrorR;
import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/v1/category", description = "Operations about categories")
@Path("/v1/category")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResourceV1 {

    private static final List<CategoryR> categories;

    static {
        CategoryR ham = new CategoryR(Id.random(), "Ham");
        CategoryR cheese = new CategoryR(Id.random(), "Cheese");
        categories = Lists.newArrayList(ham, cheese);
    }

    @GET
    @Timed
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = CategoryR[].class, message = ""),
            @ApiResponse(code = 403, response = ErrorR.class, message="Unauthorized")
    })
    public Response getAll(){
        return Response.ok(categories, MediaType.APPLICATION_JSON_TYPE).build();
    }
}