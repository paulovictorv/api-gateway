package br.com.goclip.model;

import br.com.clip.model.Deferred;
import br.com.clip.model.Promise;
import org.junit.Test;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.concurrent.CountDownLatch;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by paulo on 25/11/15.
 */
public class DeferredTest {

    @Test
    public void shouldChainTogetherSimpleValues() throws InterruptedException {
        Deferred<String> deferred = new Deferred<>(() -> "convert");

        CountDownLatch countDownLatch = new CountDownLatch(1);
        ArrayList<String> stringArrayList = new ArrayList<>();
        deferred.promise()
                .then((val) -> {
                    stringArrayList.add(val);
                    return "converted";
                })
                .then((conv) -> {
                    stringArrayList.add(conv);
                    return "step3";
                })
                .then((fina) -> {
                    stringArrayList.add(fina);
                    countDownLatch.countDown();
                    return null;
                });

        deferred.resolve("completed");
        countDownLatch.await();

        assertThat(stringArrayList, hasItems("convert", "converted", "step3"));
    }

    @Test
    public void shouldChainPromises() throws InterruptedException {
        Deferred<String> deferred = new Deferred<>(() -> "convert");

        CountDownLatch countDownLatch = new CountDownLatch(1);
        ArrayList<String> stringArrayList = new ArrayList<>();
        deferred.promise()
                .chain(this::producer)
                .chain(this::producer)
                .then((conv) -> {
                    stringArrayList.add(conv);
                    return "step3";
                })
                .then((fina) -> {
                    stringArrayList.add(fina);
                    countDownLatch.countDown();
                    return null;
                });

        deferred.resolve("completed");
        countDownLatch.await();

        assertThat(stringArrayList, hasItems("done!", "done!", "step3"));
    }

    @Test
    public void shouldCatchBlock() throws InterruptedException {
        Deferred<String> deferred = new Deferred<>();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        ArrayList<String> stringArrayList = new ArrayList<>();
        deferred.promise()
                .then((val) -> {
                    stringArrayList.add(val);
                    return "converted";
                })
                .then((conv) -> {
                    stringArrayList.add(conv);
                    return "step3";
                })
                .error((cause) -> {
                    assertThat(cause, instanceOf(Throwable.class));
                    countDownLatch.countDown();
                    return "error";
                });

        deferred.reject(new IllegalFormatCodePointException(1));
        countDownLatch.await();
    }

    @Test
    public void shouldPropagateError() throws InterruptedException {
        Deferred<String> stringDeferred = new Deferred<>(() -> "lol");

        CountDownLatch countDownLatch = new CountDownLatch(1);
        stringDeferred.promise()
                .chain(this::producer)
                .chain(this::producerError)
                .chain(this::producer)
                .error((cause) -> {
                    assertThat(cause, instanceOf(Throwable.class));
                    countDownLatch.countDown();
                    return null;
                });

        countDownLatch.await();
    }

    @Test
    public void shouldHandleErrorInsidePromise() throws InterruptedException {
        Deferred<String> stringDeferred = new Deferred<>(() -> "lol");

        CountDownLatch countDownLatch = new CountDownLatch(1);
        stringDeferred.promise()
                .chain(this::producer)
                .chain(this::producerErrorNotExpected)
                .chain(this::producer)
                .error((cause) -> {
                    assertThat(cause, instanceOf(Throwable.class));
                    countDownLatch.countDown();
                    return null;
                });

        countDownLatch.await();
    }

    private Promise<String> producerError(String val) {
        Deferred<String> stringDeferred = new Deferred<>();

        stringDeferred.reject(new NullPointerException());
        return stringDeferred.promise();
    }

    private Promise<String> producerErrorNotExpected(String val) {
        return new Deferred<>(() -> {
            //Forcing unexpected exception
            ArrayList<String> objects = new ArrayList<>();
            return objects.get(0);
        }).promise();
    }

    private Promise<String> producer(String val) {
        return new Deferred<>(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
            return "done!";
        }).promise();
    }

}
