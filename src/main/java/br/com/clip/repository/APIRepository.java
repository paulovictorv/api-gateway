package br.com.clip.repository;

import java.util.HashSet;

/**
 * Created by paulo on 24/11/15.
 */
public class APIRepository {
    public API getApiForPath(String path) {
        return new API("", new HashSet<>());
    }
}
