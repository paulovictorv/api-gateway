package br.com.clip;

import org.jooby.Jooby;

/**
 * Created by paulo on 24/11/15.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Jooby jooby = new Jooby();
        jooby.use("*", (request, response) -> {
        });
        jooby.start();
    }
}
