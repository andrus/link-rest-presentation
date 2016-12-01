package lr.events;

import com.nhl.link.rest.annotation.listener.SelectServerParamsApplied;
import com.nhl.link.rest.processor.ProcessingStage;
import com.nhl.link.rest.runtime.processor.select.SelectContext;
import lr.events.dao.EventDAO;
import lr.events.model.Event;

import java.util.List;

public class EventsBackend {

    private EventDAO eventDAO;

    public EventsBackend(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @SelectServerParamsApplied
    public ProcessingStage<SelectContext<Event>, Event> afterServer(SelectContext<Event> context) {

        List<Event> events = eventDAO.getEvents();
        if (context.getEntity().getQualifier() != null) {
            events = context.getEntity().getQualifier().filterObjects(events);
        }
        context.setObjects(events);

        return null;
    }
}
