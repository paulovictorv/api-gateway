package br.com.clip.repository;

/**
 * Created by paulo on 24/11/15.
 */
public class APIResponse {

    public final int code;
    public final String statusMsg;
    public final String body;

    public APIResponse(int code, String statusMsg, String body) {
        this.code = code;
        this.statusMsg = statusMsg;
        this.body = body;
    }
}
