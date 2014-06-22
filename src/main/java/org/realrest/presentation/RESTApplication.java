package org.realrest.presentation;

import org.realrest.presentation.resources.BookingsResource;
import org.realrest.presentation.resources.EntryPointResource;
import org.realrest.presentation.resources.HotelsResource;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * @author volodymyr.tsukur
 */
public class RESTApplication extends Application {

    private final Set<Class<?>> classes = new HashSet<>();

    public RESTApplication() {
        classes.add(EntryPointResource.class);
        classes.add(HotelsResource.class);
        classes.add(BookingsResource.class);
    }

    public Set<Class<?>> getClasses() {
        return classes;
    }

}