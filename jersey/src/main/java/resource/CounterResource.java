package resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import counter.Counter;
import dto.GetCounter;

import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/counter")
public class CounterResource {


    private static final ObjectMapper mapper = new ObjectMapper();
    private final String nameAuthCookie = "hh-auth";
    private final int lengthAuthCookie = 10;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GetCounter getCounterValue() {
        final long value = Counter.value.get();
        return new GetCounter(value);
    }

    @POST
    public Response increaseCounter() {
        final long value = Counter.value.incrementAndGet();
        final String textResponse = "Counter value increased by 1.\nCounter value: " + value;
        return Response.ok(textResponse).build();
    }

    @DELETE
    public Response decreaseCounter(@HeaderParam("Subtraction-Value") long delta) {
        final long value = Counter.value.addAndGet(-1 * delta);
        final String textResponse = "Counter value decreased by " + delta + ".\nCounter value: " + value;
        return Response.ok(textResponse).build();
    }

    @POST()
    @Path("/clear")
    public Response resetCounter(@CookieParam("hh-auth") Cookie authCookie) {
        if (authCookie.getValue().length() > lengthAuthCookie) {
            final long value = Counter.value.updateAndGet(o -> 0);
            final String textResponse = "Counter value reset.\nCounter value: " + value;
            return Response.ok(textResponse).build();
        }
        final long value = Counter.value.get();
        final String textResponse = "Unable to reset counter value.\nCounter value: " + value;
        return Response.status(401).entity(textResponse).build();
    }
}







