package stellar.client.den.stellar.injection.modules;

import dagger.Module;
import dagger.Provides;
import stellar.client.den.stellar.injection.scopes.ApplicationScope;
import stellar.client.den.stellar.net.StellarApi;
import stellar.client.den.stellar.presentation.main.MainPresenter;

@Module
public class PresenterModule {

    @Provides
    @ApplicationScope
    public MainPresenter provideMainPresenter(StellarApi stellarApi) {
        return new MainPresenter(stellarApi);
    }
}
