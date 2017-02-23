package stellar.client.den.stellar.presentation.main;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
            Picasso.with(context).load(item.getStellaPicture()).into(image);
            name.setText(item.getStellaName());
            description.setText(item.getStellaDescription());
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
}
