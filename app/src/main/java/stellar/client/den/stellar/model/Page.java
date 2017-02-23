package stellar.client.den.stellar.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable {
    @SerializedName("page")
    @Expose
    private Integer currentPage;
    @SerializedName("items")
    @Expose
    private List<Item> items;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "currentPage=" + currentPage +
                ", itemsCount=" + items.size() +
                '}';
    }
}
