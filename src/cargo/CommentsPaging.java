
package cargo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentsPaging {

    @SerializedName("cursors")
    @Expose
    private CommentsCursors cursors;
    @SerializedName("next")
    @Expose
    private String next;

    public CommentsCursors getCursors() {
        return cursors;
    }

    public void setCursors(CommentsCursors cursors) {
        this.cursors = cursors;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

}
