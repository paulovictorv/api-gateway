package br.com.clip.model;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * Created by paulo on 25/11/15.
 */
public class Promise<T> {

    private Deferred<T> deferred;

    public Promise(Deferred deferred) {
        this.deferred = deferred;
    }

    public <U> Promise<U> then(Function<T, U> func) {
        CompletableFuture<U> completableFuture = deferred.completableFuture
                .thenComposeAsync((T previousResult) -> {
                    return CompletableFuture.supplyAsync(() -> func.apply(previousResult));
                });

        return new Deferred<>(completableFuture).promise();
    }

    public <U> Promise<U> chain(Function<T, Promise<U>> func) {
        CompletableFuture<U> completableFuture = deferred.completableFuture
                .thenComposeAsync((T previousResult) -> {
                    return func.apply(previousResult).deferred.completableFuture;
                });

        return new Deferred<>(completableFuture).promise();
    }

    public <X> Promise<X> error(Function<Throwable, X> supplier) {
        Throwable[] t = new Throwable[1];
        CompletableFuture<T> exceptionally = deferred.completableFuture
                .exceptionally((e) -> {
                    t[0] = e;
                    return null;
                });

        CompletableFuture<X> xCompletableFuture = exceptionally
                .thenComposeAsync((T ignored) -> CompletableFuture.supplyAsync(() -> supplier.apply(t[0])));

        return new Deferred<X>(xCompletableFuture).promise();
    }
}
