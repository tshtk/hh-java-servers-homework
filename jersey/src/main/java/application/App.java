package application;

import resource.CounterResource;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return new HashSet<>(List.of(CounterResource.class));
    }
}

