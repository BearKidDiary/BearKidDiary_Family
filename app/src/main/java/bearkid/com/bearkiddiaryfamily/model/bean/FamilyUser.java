package bearkid.com.bearkiddiaryfamily.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * 家庭版用户
 */
public class FamilyUser extends BmobObject {
    public static final String NAME = "FUname";
    public static final String EMAIL = "FUemail";
    public static final String PHONE = "FUphone";
    public static final String AREA = "FUarea";
    public static final String PSW = "FUpsw";

    private String FUname;
    private String FUphone;
    private String FUarea;
    private String FUemail;
    private String FUpsw;

    public String getFUname() {
        return FUname;
    }

    public void setFUname(String FUname) {
        this.FUname = FUname;
    }

    public String getFUphone() {
        return FUphone;
    }

    public void setFUphone(String FUphone) {
        this.FUphone = FUphone;
    }

    public String getFUarea() {
        return FUarea;
    }

    public void setFUarea(String FUarea) {
        this.FUarea = FUarea;
    }

    public String getFUemail() {
        return FUemail;
    }

    public void setFUemail(String FUemail) {
        this.FUemail = FUemail;
    }

    public String getFUpsw() {
        return FUpsw;
    }

    public void setFUpsw(String FUpsw) {
        this.FUpsw = FUpsw;
    }
}
