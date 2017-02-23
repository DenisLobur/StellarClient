package stellar.client.den.stellar.presentation.main;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import stellar.client.den.stellar.R;
import stellar.client.den.stellar.common.BaseFragment;
import stellar.client.den.stellar.common.BasePresenter;
import stellar.client.den.stellar.common.Layout;
import stellar.client.den.stellar.model.Item;
import stellar.client.den.stellar.model.Page;

@Layout(id = R.layout.fragment_main)
public class MainFragment extends BaseFragment implements MainView {

    @BindView(R.id.stellar_list)
    RecyclerView stellarRecyclerView;

    @Inject
    MainPresenter presenter;

    private StellarAdapter adapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        stellarRecyclerView.setLayoutManager(layoutManager);
        adapter = new StellarAdapter(getActivity());
        adapter.setLoadMoreListener(() -> stellarRecyclerView.post(() -> presenter.fetchItems()));
        adapter.setOnItemClickListener(itemClickListener);
        stellarRecyclerView.setAdapter(adapter);
    }

    @Override
    public void fillStellarList(Page page) {
        adapter.swapData(page);
    }

    @Override
    public void updateStellarList() {
        adapter.notifyDataSetChanged();
    }

    StellarAdapter.OnItemClickListener itemClickListener = new StellarAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(Item item) {
            presenter.openDetails(item);
        }
    };

    @NonNull
    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void inject() {
        getMainActivityComponent().inject(this);
    }
}
