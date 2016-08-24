package bearkid.com.bearkiddiaryfamily.model.bean;

import com.google.gson.annotations.Expose;

/**
 * Created by admin on 2016/8/23.
 */
public class Weight {
    @Expose
    private Long Hid;

    @Expose
    private Long Htime;

    @Expose
    private Float Hheight;

    private Kid kid;

    public Long getHid() {
        return Hid;
    }

    public void setHid(Long hid) {
        Hid = hid;
    }

    public Long getHtime() {
        return Htime;
    }

    public void setHtime(Long htime) {
        Htime = htime;
    }

    public Float getHheight() {
        return Hheight;
    }

    public void setHheight(Float hheight) {
        Hheight = hheight;
    }

    public Kid getKid() {
        return kid;
    }

    public void setKid(Kid kid) {
        this.kid = kid;
    }
}
