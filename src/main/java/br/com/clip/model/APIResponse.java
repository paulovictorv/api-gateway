package br.com.clip.model;

import org.apache.http.Header;

/**
 * Created by paulo on 24/11/15.
 */
public class APIResponse {

    public final int code;
    public final String statusMsg;
    public final String body;

    public APIResponse() {
        this.code = 0;
        this.statusMsg = "";
        this.body = "";
    }

    public APIResponse(int code, String statusMsg, String body) {
        this.code = code;
        this.statusMsg = statusMsg;
        this.body = body;
    }

    public APIResponse withHeaders(Header[] allHeaders) {
        return new APIResponse(this.code, this.statusMsg, this.body);
    }

    public APIResponse withCode(int statusCode) {
        return new APIResponse(statusCode, this.statusMsg, this.body);
    }

    public APIResponse withMessage(String statusMsg) {
        return new APIResponse(this.code, statusMsg, this.body);
    }
}
