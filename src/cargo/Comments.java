
package cargo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comments {

    @SerializedName("data")
    @Expose
    private List<CommentsDatum_> data = null;
    @SerializedName("paging")
    @Expose
    private CommentsPaging paging;

    public List<CommentsDatum_> getData() {
        return data;
    }

    public void setData(List<CommentsDatum_> data) {
        this.data = data;
    }

    public CommentsPaging getPaging() {
        return paging;
    }

    public void setPaging(CommentsPaging paging) {
        this.paging = paging;
    }

}
