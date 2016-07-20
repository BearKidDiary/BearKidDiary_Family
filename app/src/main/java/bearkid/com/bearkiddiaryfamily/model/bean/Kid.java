package bearkid.com.bearkiddiaryfamily.model.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by admin on 2016/7/11.
 */
public class Kid extends BmobObject {
    public static final String NAME = "Kname";
    public static final String SEX = "Ksex";
    public static final String BIRTHDAY = "Kbirthday";
    public static final String ASK = "Kask";
    public static final String FAMILY = "family";
    public static final String AVATAR = "Kavatar";

    private String Kname;
    private String Ksex;
    private BmobDate Kbirthday;
    private String Kask;
    private Family family;
    private BmobFile Kavatar;
    private BmobRelation Ktimeline;

    public BmobFile getKavatar() {
        return Kavatar;
    }

    public void setKavatar(BmobFile kavatar) {
        Kavatar = kavatar;
    }

    public BmobRelation getKtimeline() {
        return Ktimeline;
    }

    public void setKtimeline(BmobRelation ktimeline) {
        Ktimeline = ktimeline;
    }

    public String getKname() {
        return Kname;
    }

    public void setKname(String kname) {
        Kname = kname;
    }

    public String getKsex() {
        return Ksex;
    }

    public void setKsex(String ksex) {
        Ksex = ksex;
    }

    public BmobDate getKbirthday() {
        return Kbirthday;
    }

    public void setKbirthday(BmobDate kbirthday) {
        Kbirthday = kbirthday;
    }

    public String getKask() {
        return Kask;
    }

    public void setKask(String kask) {
        Kask = kask;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }
}
