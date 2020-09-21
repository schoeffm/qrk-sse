package de.bender;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

@ApplicationScoped
@Path("/notifications")
public class NotificationResource {

    Sse sse;
    SseBroadcaster sseBroadcaster;

    @Context
    public void setSse(Sse sse) {
        this.sse = sse;
        this.sseBroadcaster = sse.newBroadcaster();
    }

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void register(@Context SseEventSink eventSink, @Context Sse sse){
        eventSink.send(sse.newEvent("Welcome!"));
        this.sseBroadcaster.register(eventSink);
    }

    @PUT
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void broadcast(){
        this.sseBroadcaster.broadcast(sse.newEvent("test"));
    }
}
