package stellar.client.den.stellar.presentation.detail;


import javax.inject.Inject;

import stellar.client.den.stellar.common.BasePresenter;
import stellar.client.den.stellar.common.Router;
import stellar.client.den.stellar.model.Item;

public class DetailPresenter extends BasePresenter<DetailView, Router> {

    @Inject
    public DetailPresenter() {

    }

    public void setDetails(Item item) {
        getView().showDetails(item);
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {

    }
}
