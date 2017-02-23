package stellar.client.den.stellar.injection.components;

import dagger.Component;
import stellar.client.den.stellar.injection.modules.ContextModule;
import stellar.client.den.stellar.injection.modules.RestApiModule;
import stellar.client.den.stellar.injection.scopes.ApplicationScope;
import stellar.client.den.stellar.presentation.detail.DetailFragment;
import stellar.client.den.stellar.presentation.main.MainFragment;

@ApplicationScope
@Component(modules = {ContextModule.class, RestApiModule.class})
public interface MainActivityComponent {

    void inject(MainFragment mainFragment);

    void inject(DetailFragment detailFragment);
}
