package br.com.goclip.model;

import br.com.clip.model.API;
import br.com.clip.model.Route;
import org.junit.Test;

import java.util.HashSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by paulo on 24/11/15.
 */
public class APITest {

    @Test
    public void shouldFindCorrectRoute() throws InterruptedException {
        HashSet<Route> routes = new HashSet<>();
        routes.add(new Route("GET", "/test"));

        API api = new API("/api", routes);

        Route route = api.route("GET", "/test");

        assertThat(route, equalTo(new Route("GET", "/test")));
    }

}
