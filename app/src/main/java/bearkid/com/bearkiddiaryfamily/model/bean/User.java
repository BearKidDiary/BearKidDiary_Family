package bearkid.com.bearkiddiaryfamily.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * 家庭版用户
 */

public class User {
    public static final String NAME = "Uname";
    public static final String SEX = "Usex";
    public static final String PHONE = "Uphone";
    public static final String AREA = "Uarea";
    public static final String PSW = "Upsw";
    public static final String AVATAR = "Uavatar";
    public static final String WORKEXPERIENCE = "Uworkexperience";
    public static final String SPECIALTY = "Uspecialty";
    public static final String EDUEXPERIENCE = "Ueduexperience";
    public static final String EMAIL = "Uemail";


    private String Uname = null;
    private String Usex = null;
    private String Uphone;
    private String Uarea = null;
    private String Upsw;
    //private String Uavatar;
    private String Uemail = null;

    public String getUname() {
        return Uname;
    }

    public void setUname(String Uname) {
        this.Uname = Uname;
    }

    public String getUsex() {
        return Usex;
    }

    public void setUsex(String Usex) {
        this.Usex = Usex;
    }

    public String getUphone() {
        return Uphone;
    }

    public void setUphone(String Uphone) {
        this.Uphone = Uphone;
    }

    public String getUarea() {
        return Uarea;
    }

    public void setUarea(String Uarea) {
        this.Uarea = Uarea;
    }

    public String getUemail() {
        return Uemail;
    }

    public void setUemail(String Uemail) {
        this.Uemail = Uemail;
    }

    public String getUpsw() {
        return Upsw;
    }

    public void setUpsw(String Upsw) {
        this.Upsw = Upsw;
    }

    @Override
    public String toString() {
        return "Uphone = " + Uphone
                + "Uname" + Uname;
    }
}
