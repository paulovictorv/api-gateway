package br.com.goclip.model;

import br.com.clip.model.APIResponse;
import br.com.clip.model.Promise;
import br.com.clip.model.Route;
import br.com.goclip.model.util.BaseTest;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by paulo on 24/11/15.
 */
public class RouteTest extends BaseTest {

    @Test
    public void shouldCallGetRoute() throws InterruptedException {
        Route get = new Route("http://localhost:8080/", "GET", "/route");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Promise<APIResponse> call = get.call();
        call.then(apiResponse -> {
            assertThat(apiResponse.code, equalTo(200));
            countDownLatch.countDown();
            return null;
        });
        countDownLatch.await();
    }
}
