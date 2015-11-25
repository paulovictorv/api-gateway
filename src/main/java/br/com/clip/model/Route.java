package br.com.clip.model;

import com.google.common.base.Objects;
import org.jdeferred.Promise;

/**
 * Created by paulo on 24/11/15.
 */
public class Route {

    public final String method;
    public final String route;

    public Route(String method, String route) {
        this.method = method;
        this.route = route;
    }

    public Promise<APIResponse, APIResponse, APIResponse> call(String rootUrl) {

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;
        Route route1 = (Route) o;
        return Objects.equal(method, route1.method) &&
                Objects.equal(route, route1.route);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(method, route);
    }

}
