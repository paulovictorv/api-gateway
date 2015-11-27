package br.com.clip.model;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * Created by paulo on 25/11/15.
 */
public class Deferred<T> {

    CompletableFuture<T> completableFuture;

    Deferred(CompletableFuture<T> completableFuture) {
        this.completableFuture = completableFuture;
    }

    public Deferred(Supplier<T> supplier) {
        completableFuture = CompletableFuture.supplyAsync(supplier);
    }

    public Deferred() {
        completableFuture = new CompletableFuture<>();
    }

    public void resolve(T resolveValue) {
        completableFuture.complete(resolveValue);
    }

    public void reject(Throwable exception) {
        completableFuture.completeExceptionally(exception);
    }

    public Promise<T> promise() {
        return new Promise<>(this);
    }


}
