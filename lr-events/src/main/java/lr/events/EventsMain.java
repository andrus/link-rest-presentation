package lr.events;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.bootique.Bootique;
import io.bootique.jersey.JerseyModule;
import lr.events.dao.EventDAO;
import lr.events.dao.RandomEventDAO;

public class EventsMain implements Module {

    public static void main(String[] args) {
        Bootique.app("--config=classpath:events.yml", "--server")
                .autoLoadModules()
                .module(EventsMain.class)
                .run();
    }

    @Override
    public void configure(Binder binder) {
        JerseyModule.contributeResources(binder)
                .addBinding().to(EventsApi.class);
    }

    @Provides
    @Singleton
    private EventDAO provideEventDAO() {
        return new RandomEventDAO();
    }
}
