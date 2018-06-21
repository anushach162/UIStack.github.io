
package cargo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentsRootObject {

    @SerializedName("data")
    @Expose
    private List<CommentsDatum> data = null;
    @SerializedName("paging")
    @Expose
    private CommentsPaging_ paging;

    public List<CommentsDatum> getData() {
        return data;
    }

    public void setData(List<CommentsDatum> data) {
        this.data = data;
    }

    public CommentsPaging_ getPaging() {
        return paging;
    }

    public void setPaging(CommentsPaging_ paging) {
        this.paging = paging;
    }

}
