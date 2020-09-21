package de.bender;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.reactivestreams.Publisher;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

@ApplicationScoped
@Path("/notifications")
public class NotificationResource {

    @Inject
    @Channel("notifications")
    Publisher<String> prices;

    @Inject
    @Channel("notifications")
    @Broadcast
    Emitter<String> priceEmitter;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Publisher<String> stream() {
        return prices;
    }

    @PUT
    // @Produces(MediaType.SERVER_SENT_EVENTS)
    public Response test() {
        priceEmitter.send("test");
        return Response.ok().build();
    }
}
