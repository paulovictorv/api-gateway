package br.com.clip.repository;

import br.com.clip.model.API;

import java.util.HashSet;

/**
 * Created by paulo on 24/11/15.
 */
public class APIRepository {
    public API getApiForPath(String path) {
        return new API("http://localhost:8080/", new HashSet<>());
    }
}
