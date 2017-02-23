package stellar.client.den.stellar.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Item implements Serializable {
    @SerializedName("name")
    @Expose
    private String stellarName;
    @SerializedName("image_url")
    @Expose
    private String stellarPicture;
    @SerializedName("description")
    @Expose
    private String stellarDescription;

    public String getStellarName() {
        return stellarName;
    }

    public String getStellarPicture() {
        return stellarPicture;
    }

    public String getStellarDescription() {
        return stellarDescription;
    }

    @Override
    public String toString() {
        return "Item{" +
                "stellaName='" + stellarName + '\'' +
                ", stellaPicture='" + stellarPicture + '\'' +
                ", stellaDescription='" + stellarDescription + '\'' +
                '}';
    }
}
