package lr.schedule;

import com.google.inject.Binder;
import com.google.inject.Module;
import io.bootique.Bootique;
import io.bootique.jersey.JerseyModule;

public class ScheduleMain implements Module {

    public static void main(String[] args) {
        Bootique.app("--config=classpath:schedule.yml", "--server")
                .autoLoadModules()
                .module(ScheduleMain.class)
                .run();
    }

    @Override
    public void configure(Binder binder) {
        JerseyModule
                .contributeResources(binder)
                .addBinding()
                .to(ScheduleApi.class);
    }
}
