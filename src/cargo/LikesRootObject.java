
package cargo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LikesRootObject {

    @SerializedName("data")
    @Expose
    private List<LikesDatum> data = null;
    @SerializedName("paging")
    @Expose
    private LikesPaging paging;

    public List<LikesDatum> getData() {
        return data;
    }

    public void setData(List<LikesDatum> data) {
        this.data = data;
    }

    public LikesPaging getPaging() {
        return paging;
    }

    public void setPaging(LikesPaging paging) {
        this.paging = paging;
    }

}
