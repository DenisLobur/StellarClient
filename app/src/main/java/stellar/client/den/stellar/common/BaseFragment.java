package stellar.client.den.stellar.common;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.lang.annotation.Annotation;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment implements BaseView {

    private static final AtomicInteger lastFragmentId = new AtomicInteger(0);
    private final int fragmentId;
    private Unbinder unbinder;

    public BaseFragment() {
        fragmentId = lastFragmentId.incrementAndGet();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Class cls = getClass();
        if (!cls.isAnnotationPresent(Layout.class)) return null;
        Annotation annotation = cls.getAnnotation(Layout.class);
        Layout layout = (Layout) annotation;
        View view = inflater.inflate(layout.id(), null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        inject();
        MainActivity mainActivity = (MainActivity) getActivity();
        //noinspection unchecked
        getPresenter().setRouter(mainActivity);
        //noinspection unchecked
        getPresenter().setView(this);
    }

    @Override
    public void showError(@StringRes int message) {
        Toast.makeText(getContext(), getString(message), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        getPresenter().onStop();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        //noinspection unchecked
        getPresenter().setRouter(null);

    }

    public String getFragmentName() {
        return Long.toString(fragmentId);
    }

    @NonNull
    protected abstract BasePresenter getPresenter();

    protected abstract void inject();
}
