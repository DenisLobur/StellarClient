package stellar.client.den.stellar.presentation.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import stellar.client.den.stellar.R;
import stellar.client.den.stellar.common.BaseFragment;
import stellar.client.den.stellar.common.BasePresenter;
import stellar.client.den.stellar.common.Layout;
import stellar.client.den.stellar.model.Item;
import stellar.client.den.stellar.presentation.main.BitmapTransform;

import static stellar.client.den.stellar.common.Constants.ITEM;

@Layout(id = R.layout.fragment_details)
public class DetailFragment extends BaseFragment implements DetailView {

    @BindView(R.id.detail_img)
    ImageView fullImage;
    @BindView(R.id.detail_name)
    TextView name;
    @BindView(R.id.detail_description)
    TextView description;
    @Inject
    DetailPresenter presenter;

    private static final int MAX_WIDTH = 600;
    private static final int MAX_HEIGHT = 800;

    public static DetailFragment newInstance(Item item) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.setDetails((Item) getArguments().getSerializable(ITEM));
    }

    @Override
    public void showDetails(Item item) {
        if (item != null) {
            Picasso.with(getActivity())
                    .load(item.getStellarPicture())
                    .transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerInside()
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(fullImage);
            name.setText(checkEmptyName(item.getStellarName()));
            description.setText(checkEmptyDescription(item.getStellarDescription()));
        } else {
            Picasso.with(getActivity()).load(R.mipmap.ic_launcher).into(fullImage);
        }
    }

    @NonNull
    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void inject() {
        getMainActivityComponent().inject(this);
    }

    private String checkEmptyName(String name) {
        return TextUtils.isEmpty(name) ? getString(R.string.no_name) : name;
    }

    private String checkEmptyDescription(String description) {
        return TextUtils.isEmpty(description) ? getString(R.string.no_description) : description;
    }
}
