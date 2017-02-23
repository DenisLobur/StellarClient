package stellar.client.den.stellar.presentation.main;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import stellar.client.den.stellar.R;
import stellar.client.den.stellar.model.Item;
import stellar.client.den.stellar.model.Page;

public class StellarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Item> itemList = new ArrayList<>();
    private List<Page> pageList = new ArrayList<>();
    private OnLoadMoreListener loadMoreListener;
    private OnItemClickListener itemClickListener;
    private boolean isLoading;

    public StellarAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new ViewHolder(inflater.inflate(R.layout.layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position >= getItemCount() - 1 && !isLoading && loadMoreListener != null) {
            isLoading = true;
            loadMoreListener.onLoadMore();
        }
        ((ViewHolder) holder).bindData(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList != null ? itemList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private static final int MAX_WIDTH = 100;
        private static final int MAX_HEIGHT = 100;

        @BindView(R.id.img)
        ImageView image;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.description)
        TextView description;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(v -> {
                itemClickListener.onItemClick(itemList.get(getAdapterPosition()));
            });
        }

        void bindData(Item item) {
            Picasso.with(context)
                    .load(item.getStellarPicture())
                    .transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                    .resize(50, 50)
                    .centerInside()
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(image);
            name.setText(checkEmptyName(item.getStellarName()));
            description.setText(checkEmptyDescription(item.getStellarDescription()));
        }
    }

    public void notifyDataChanged() {
        notifyDataSetChanged();
        isLoading = false;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public interface OnItemClickListener {
        void onItemClick(Item item);
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void swapData(Page page) {
        pageList.add(page);
        itemList.addAll(pageList.get(pageList.size() - 1).getItems());
        notifyDataSetChanged();
        isLoading = false;
    }

    private String checkEmptyName(String name) {
        return TextUtils.isEmpty(name) ? context.getString(R.string.no_name) : name;
    }

    private String checkEmptyDescription(String description) {
        return TextUtils.isEmpty(description) ? context.getString(R.string.no_description) : description;
    }
}
