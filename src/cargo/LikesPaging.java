
package cargo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LikesPaging {

    @SerializedName("cursors")
    @Expose
    private LikesCursors cursors;

    public LikesCursors getCursors() {
        return cursors;
    }

    public void setCursors(LikesCursors cursors) {
        this.cursors = cursors;
    }

}
