
package cargo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentsDatum {

    @SerializedName("comments")
    @Expose
    private Comments comments;
    @SerializedName("id")
    @Expose
    private String id;

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
