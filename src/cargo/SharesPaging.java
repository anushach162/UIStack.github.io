
package cargo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SharesPaging {

    @SerializedName("cursors")
    @Expose
    private SharesCursors cursors;

    public SharesCursors getCursors() {
        return cursors;
    }

    public void setCursors(SharesCursors cursors) {
        this.cursors = cursors;
    }

}
