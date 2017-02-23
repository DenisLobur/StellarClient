package stellar.client.den.stellar.injection.modules;

import android.util.Log;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import stellar.client.den.stellar.injection.scopes.ApplicationScope;

@Module
public class NetworkModule {

    @Provides
    @ApplicationScope
    public HttpLoggingInterceptor loggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> Log.i("Network: ", message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return loggingInterceptor;
    }

    @Provides
    @ApplicationScope
    public OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }
}
