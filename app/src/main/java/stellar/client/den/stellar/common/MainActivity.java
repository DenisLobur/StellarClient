package stellar.client.den.stellar.common;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

import stellar.client.den.stellar.R;
import stellar.client.den.stellar.injection.components.DaggerMainActivityComponent;
import stellar.client.den.stellar.injection.components.MainActivityComponent;
import stellar.client.den.stellar.injection.modules.ContextModule;
import stellar.client.den.stellar.injection.modules.NetworkModule;
import stellar.client.den.stellar.injection.modules.RestApiModule;
import stellar.client.den.stellar.model.Item;
import stellar.client.den.stellar.presentation.detail.DetailFragment;
import stellar.client.den.stellar.presentation.main.MainFragment;

@Layout(id = R.layout.activity_main)
public class MainActivity extends BaseActivity implements Router {

    private MainActivityComponent mainActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityComponent =
                DaggerMainActivityComponent
                        .builder()
                        .networkModule(new NetworkModule())
                        .restApiModule(new RestApiModule())
                        .contextModule(new ContextModule(this))
                        .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            addBackStack(new MainFragment());
        }
        return super.onCreateOptionsMenu(menu);
    }

    private void addBackStack(BaseFragment fragment) {
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.content, fragment);
        tx.addToBackStack(fragment.getFragmentName());
        tx.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            finish();
        }
    }

    @Override
    public void showDetails(Item item) {
        addBackStack(DetailFragment.newInstance(item));
    }

    public MainActivityComponent getMainActivityComponent() {
        return mainActivityComponent;
    }
}
