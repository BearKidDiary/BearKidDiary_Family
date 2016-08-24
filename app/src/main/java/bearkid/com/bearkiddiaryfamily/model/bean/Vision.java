package bearkid.com.bearkiddiaryfamily.model.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Vision implements Serializable {
    @Expose
    private Long Vid;

    @Expose
    private Float Vleft;

    @Expose
    private Float Vright;

    @Expose
    private Long Vtime;

    private Kid kid;

    public Long getVid() {
        return Vid;
    }

    public void setVid(Long vid) {
        Vid = vid;
    }

    public Float getVleft() {
        return Vleft;
    }

    public void setVleft(Float vleft) {
        Vleft = vleft;
    }

    public Float getVright() {
        return Vright;
    }

    public void setVright(Float vright) {
        Vright = vright;
    }

    public Long getVtime() {
        return Vtime;
    }

    public void setVtime(Long vtime) {
        Vtime = vtime;
    }

    public Kid getKid() {
        return kid;
    }

    public void setKid(Kid kid) {
        this.kid = kid;
    }
}
