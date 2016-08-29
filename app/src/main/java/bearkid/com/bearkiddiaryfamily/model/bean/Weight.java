package bearkid.com.bearkiddiaryfamily.model.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by admin on 2016/8/23.
 */
public class Weight implements Serializable{
    @Expose
    private Long Wid;

    @Expose
    private Long Wtime;

    @Expose
    private float Wweight;

    private Kid kid;

    public Long getWid() {
        return Wid;
    }

    public void setWid(Long wid) {
        Wid = wid;
    }

    public Long getWtime() {
        return Wtime;
    }

    public void setWtime(Long wtime) {
        Wtime = wtime;
    }

    public float getWweight() {
        return Wweight;
    }

    public void setWweight(float wweight) {
        Wweight = wweight;
    }

    public Kid getKid() {
        return kid;
    }

    public void setKid(Kid kid) {
        this.kid = kid;
    }

}
