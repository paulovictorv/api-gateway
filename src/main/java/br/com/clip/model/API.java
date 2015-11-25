package br.com.clip.model;

import java.util.Set;

/**
 * Created by paulo on 24/11/15.
 */
public class API {

    private String rootUrl;
    private Set<Route> routes;

    public API(String rootUrl, Set<Route> routes) {
        this.rootUrl = rootUrl;
        this.routes = routes;
    }

    public Route route(String method, String path) {
        return routes.stream()
                .filter(rt -> rt.equals(new Route(method, path)))
                .findFirst()
                .get();
    }
}
