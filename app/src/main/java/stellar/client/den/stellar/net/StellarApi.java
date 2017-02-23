package stellar.client.den.stellar.net;


import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import stellar.client.den.stellar.model.Page;

public interface StellarApi {

    @GET("page/{pageNumber}")
    Observable<Page> getItems(@Path("pageNumber") Integer pageNumber);
}
