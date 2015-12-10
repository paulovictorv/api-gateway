package br.com.goclip.model;

import br.com.clip.model.API;
import br.com.clip.model.Route;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by paulo on 24/11/15.
 */
public class APITest {

    @Test
    public void shouldFindCorrectRoute() throws InterruptedException {
        API api = new API("/api");
        api.addRoute("GET", "/test");

        Route route = api.route("GET", "/test");

        assertThat(route, equalTo(new Route(api.rootUrl, "GET", "/test")));
    }

}
