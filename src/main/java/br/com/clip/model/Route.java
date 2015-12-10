package br.com.clip.model;

import br.com.clip.service.HTTPService;
import com.google.common.base.Objects;

/**
 * Created by paulo on 24/11/15.
 */
public class Route {

    public final String rootUrl;
    public final String method;
    public final String path;
    private HTTPService httpService;

    public Route(String rootUrl, String method, String path) {
        this.rootUrl = rootUrl;
        this.method = method;
        this.path = path;
        httpService = new HTTPService();
    }

    public Promise<APIResponse> call() {
        return httpService.call(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;
        Route route1 = (Route) o;
        return Objects.equal(method, route1.method) &&
                Objects.equal(path, route1.path);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(method, path);
    }

}
