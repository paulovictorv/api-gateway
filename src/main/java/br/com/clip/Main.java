package br.com.clip;

import br.com.clip.repository.API;
import br.com.clip.repository.APIRepository;
import org.jdeferred.Promise;
import org.jooby.Deferred;
import org.jooby.Jooby;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by paulo on 24/11/15.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Jooby jooby = new Jooby();
        Executor executor = new ThreadPoolExecutor(10, 100, 30000l, TimeUnit.MINUTES, new ArrayBlockingQueue<>(100));
        jooby.use("*", (request, response) -> {
            Deferred deferred = new Deferred();

            APIRepository repository = jooby.require(APIRepository.class);
            API apiForPath = repository.getApiForPath(request.path());

            executor.execute(() -> {
                Promise call = apiForPath.call("/api/lol");
                call.done((obj) -> {
                    deferred.resolve(() -> {
                        System.out.println("hello");

                        response.end();
                        return null;
                    });
                });
            });

        });

        jooby.start();
    }
}
