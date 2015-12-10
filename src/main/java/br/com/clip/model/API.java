package br.com.clip.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by paulo on 24/11/15.
 */
public class API {

    public final String rootUrl;
    private Set<Route> routes;

    public API(String rootUrl) {
        this.rootUrl = rootUrl;
        routes = new HashSet<>();
    }

    public void addRoute(String method, String path) {
        routes.add(new Route(rootUrl, method, path));
    }

    public Route route(String method, String path) {
        return routes.stream()
                .filter(rt -> rt.equals(new Route(rootUrl, method, path)))
                .findFirst()
                .get();
    }
}
