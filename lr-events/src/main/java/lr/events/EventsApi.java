package lr.events;

import com.google.inject.Inject;
import com.nhl.link.rest.DataResponse;
import com.nhl.link.rest.LinkRest;
import lr.events.dao.EventDAO;
import lr.events.model.Event;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class EventsApi {

    @Context
    private Configuration configuration;

    @Context
    private UriInfo uri;

    @Inject
    private EventDAO eventDAO;

    @GET
    public DataResponse<Event> getEvents() {
        return LinkRest.select(Event.class, configuration)
                .uri(uri)
                .listener(new EventsBackend(eventDAO))
                .select();
    }
}
