package be.swsb.fiazard.ordering.orderplacement;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import be.swsb.fiazard.common.Identifiable;
import be.swsb.fiazard.common.error.ErrorR;
import be.swsb.fiazard.common.eventsourcing.EventStore;

import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Api(value = OrderingResourceV1.ORDERING_BASE_URI, description = "Operations about orders")
@Path(OrderingResourceV1.ORDERING_BASE_URI)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderingResourceV1 {

    public static final String ORDERING_BASE_URI = "/v1/ordering";
    private EventStore eventStore;

    public OrderingResourceV1(EventStore eventStore){
    	this.eventStore = eventStore;
    }

    // TODO status 202 teruggeven + id
    @POST
    @Timed
    @ApiResponses(value = {
            @ApiResponse(code = 202, response = Identifiable.class, message = "Order placed"),
            @ApiResponse(code = 403, response = ErrorR.class, message="Error while placing order")
    })
    @Path("/placeorder")
    public Response placeOrder(PlaceOrder placeOrder) {
    	Identifiable identifiable = Identifiable.randomId();
    	eventStore.store(new OrderPlaced(identifiable.getId(), placeOrder.getSandwiches()));
        return Response.ok(identifiable).build();
    }

}
