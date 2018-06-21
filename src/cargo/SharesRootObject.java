
package cargo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SharesRootObject {

    @SerializedName("data")
    @Expose
    private List<SharesDatum> data = null;
    @SerializedName("paging")
    @Expose
    private SharesPaging paging;

    public List<SharesDatum> getData() {
        return data;
    }

    public void setData(List<SharesDatum> data) {
        this.data = data;
    }

    public SharesPaging getPaging() {
        return paging;
    }

    public void setPaging(SharesPaging paging) {
        this.paging = paging;
    }

}
