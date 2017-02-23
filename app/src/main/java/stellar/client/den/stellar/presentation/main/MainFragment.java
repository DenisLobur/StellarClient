package stellar.client.den.stellar.presentation.main;


import android.support.annotation.NonNull;

import stellar.client.den.stellar.R;
import stellar.client.den.stellar.common.BaseFragment;
import stellar.client.den.stellar.common.BasePresenter;
import stellar.client.den.stellar.common.Layout;

@Layout(id = R.layout.fragment_main)
public class MainFragment extends BaseFragment implements MainView{

    @NonNull
    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void inject() {

    }
}
