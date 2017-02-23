package stellar.client.den.stellar.presentation.detail;

import android.support.annotation.NonNull;

import stellar.client.den.stellar.R;
import stellar.client.den.stellar.common.BaseFragment;
import stellar.client.den.stellar.common.BasePresenter;
import stellar.client.den.stellar.common.Layout;

@Layout(id = R.layout.fragment_details)
public class DetailFragment extends BaseFragment implements DetailView {

    @NonNull
    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void inject() {

    }
}
