package br.com.goclip.model;

import br.com.clip.model.APIResponse;
import br.com.clip.model.Route;
import org.jdeferred.Promise;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by paulo on 24/11/15.
 */
public class RouteTest {

    @Test
    public void shouldCallRoute() {
        Route get = new Route("GET", "/route");

        Promise<APIResponse, APIResponse, APIResponse> call = get.call("http://localhost:8080/");
        call.done(apiResponse -> {
            assertThat(apiResponse.code, equalTo(200));
        });


    }
}
