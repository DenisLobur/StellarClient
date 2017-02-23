package stellar.client.den.stellar.presentation.detail;


import stellar.client.den.stellar.common.BaseView;
import stellar.client.den.stellar.model.Item;

public interface DetailView extends BaseView {
    void showDetails(Item item);
}
