package stellar.client.den.stellar.injection.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import stellar.client.den.stellar.injection.scopes.ApplicationScope;

@Module
public class ContextModule {

    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    public Context context() {
        return context;
    }
}
