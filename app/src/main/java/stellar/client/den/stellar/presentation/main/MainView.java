package stellar.client.den.stellar.presentation.main;


import stellar.client.den.stellar.common.BaseView;
import stellar.client.den.stellar.model.Page;

public interface MainView extends BaseView {

    void fillStellarList(Page page);

    void updateStellarList();
}
