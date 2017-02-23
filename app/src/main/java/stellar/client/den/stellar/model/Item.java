package stellar.client.den.stellar.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("name")
    @Expose
    private String stellaName;
    @SerializedName("image_url")
    @Expose
    private String stellaPicture;
    @SerializedName("description")
    @Expose
    private String stellaDescription;

    public String getStellaName() {
        return stellaName;
    }

    public String getStellaPicture() {
        return stellaPicture;
    }

    public String getStellaDescription() {
        return stellaDescription;
    }

    @Override
    public String toString() {
        return "Item{" +
                "stellaName='" + stellaName + '\'' +
                ", stellaPicture='" + stellaPicture + '\'' +
                ", stellaDescription='" + stellaDescription + '\'' +
                '}';
    }
}
