package stellar.client.den.stellar.injection.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import stellar.client.den.stellar.injection.scopes.ApplicationScope;
import stellar.client.den.stellar.net.StellarApi;

@Module(includes = NetworkModule.class)
public class RestApiModule {

    private static final String BASE_URL = "http://54.202.245.89:3000/page/";

    @Provides
    @ApplicationScope
    public StellarApi getStellarApi(Retrofit retrofit) {
        return retrofit.create(StellarApi.class);
    }

    @Provides
    @ApplicationScope
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @ApplicationScope
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }
}
