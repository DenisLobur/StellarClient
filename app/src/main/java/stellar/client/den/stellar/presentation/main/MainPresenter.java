package stellar.client.den.stellar.presentation.main;


import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import stellar.client.den.stellar.common.BasePresenter;
import stellar.client.den.stellar.common.Router;
import stellar.client.den.stellar.model.Item;
import stellar.client.den.stellar.net.StellarApi;

public class MainPresenter extends BasePresenter<MainView, Router> {


    private StellarApi stellarApi;
    private static Scheduler scheduler = Schedulers.from(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
    AtomicInteger increment = new AtomicInteger();

    @Inject
    public MainPresenter(StellarApi stellarApi) {
        this.stellarApi = stellarApi;
    }

    public void fetchItems() {
        int i = increment.incrementAndGet();
        stellarApi.getItems(0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(scheduler)
                //.map(it -> Log.d("result", it.toString()))
                .subscribe(pageResult -> {
                    Log.d("result", pageResult.toString());
                    getView().fillStellarList(pageResult);
                }, throwable -> {
                    Throwable th = throwable;
                });
    }

    @Override
    public void onStart() {
        fetchItems();
    }

    @Override
    public void onStop() {

    }

    public void openDetails(Item item) {
        getRouter().showDetails(item);
    }
}
