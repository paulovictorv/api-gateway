package br.com.goclip.model.util;

import org.jooby.Jooby;

/**
 * Created by paulo on 10/12/15.
 */
public class TestApp extends Jooby {

    {
        get("route", () -> "hey");
    }

}
