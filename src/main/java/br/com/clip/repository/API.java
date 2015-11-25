package br.com.clip.repository;

import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;

import java.util.Set;

/**
 * Created by paulo on 24/11/15.
 */
public class API {

    private String rootUrl;
    private Set<Routes> routes;

    public API(String rootUrl, Set<Routes> routes) {
        this.rootUrl = rootUrl;
        this.routes = routes;
    }

    public Promise call(String path) {
        DeferredObject<Object, Object, Object> deferred = new DeferredObject<>();
        Promise<Object, Object, Object> promise = deferred.promise();

        deferred.resolve("lol");

        return promise;
    }
}
