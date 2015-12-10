package br.com.clip.service;

import br.com.clip.model.APIResponse;
import br.com.clip.model.Deferred;
import br.com.clip.model.Promise;
import br.com.clip.model.Route;
import org.apache.http.client.fluent.Async;
import org.apache.http.client.fluent.Request;
import org.apache.http.concurrent.FutureCallback;

/**
 * Created by paulo on 09/12/15.
 */

public class HTTPService {

    private Async async = Async.newInstance();

    public HTTPService() {
    }

    public Promise<APIResponse> call(Route route) {
        Request req = forMethod(route);
        APIResponse response = new APIResponse();

        Deferred<APIResponse> deferred = new Deferred<>();

        async.execute(req, httpResponse -> response
                        .withCode(httpResponse.getStatusLine().getStatusCode())
                        .withMessage(httpResponse.getStatusLine().getReasonPhrase())
                        .withHeaders(httpResponse.getAllHeaders()),
                new FutureCallback<APIResponse>() {
                    @Override
                    public void completed(APIResponse apiResponse) {
                        deferred.resolve(apiResponse);
                    }

                    @Override
                    public void failed(Exception e) {
                        deferred.reject(e);
                    }

                    @Override
                    public void cancelled() {
                    }
                });

        return deferred.promise();
    }

    private Request forMethod(Route route) {
        switch (route.method) {
            case "GET":
                return Request.Get(route.rootUrl + route.path);
            default:
                throw new UnsupportedOperationException();
        }
    }

}
