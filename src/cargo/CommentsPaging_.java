
package cargo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentsPaging_ {

    @SerializedName("cursors")
    @Expose
    private CommentsCursors_ cursors;

    public CommentsCursors_ getCursors() {
        return cursors;
    }

    public void setCursors(CommentsCursors_ cursors) {
        this.cursors = cursors;
    }

}
