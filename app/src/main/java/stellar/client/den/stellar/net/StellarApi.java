package stellar.client.den.stellar.net;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import stellar.client.den.stellar.model.Page;

public interface StellarApi {

    @GET("page/")
    Observable<Page> getPhotos(@Query("page") Integer pageNumber);
}
