package bearkid.com.bearkiddiaryfamily.model.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by admin on 2016/7/4.
 */
public class Teacher extends BmobObject{
    /**
     * 教师名称
     */
    private String Tname;
    /**
     * 教师性别
     */
    private String Tsex;
    /**
     * 教师手机号码
     */
    private String Tphone;
    /**
     * 所在地区
     */
    private String Tarea;
    /**
     * 登陆密码
     */
    private String Tpsw;
    /**
     * 教师头像
     */
    private BmobFile Tavatar;
    /**
     * 工作经验
     */
    private String Tworkexperience;
    /**
     * 教师特长
     */
    private String Tspecialty;
    /**
     * 教师教育经验
     */
    private String Teduexperience;

    public String getTname() {
        return Tname;
    }

    public void setTname(String tname) {
        Tname = tname;
    }

    public String getTsex() {
        return Tsex;
    }

    public void setTsex(String tsex) {
        Tsex = tsex;
    }

    public String getTphone() {
        return Tphone;
    }

    public void setTphone(String tphone) {
        Tphone = tphone;
    }

    public String getTarea() {
        return Tarea;
    }

    public void setTarea(String tarea) {
        Tarea = tarea;
    }

    public String getTpsw() {
        return Tpsw;
    }

    public void setTpsw(String tpsw) {
        Tpsw = tpsw;
    }

    public BmobFile getTavatar() {
        return Tavatar;
    }

    public void setTavatar(BmobFile tavatar) {
        Tavatar = tavatar;
    }

    public String getTworkexperience() {
        return Tworkexperience;
    }

    public void setTworkexperience(String tworkexperience) {
        Tworkexperience = tworkexperience;
    }

    public String getTspecialty() {
        return Tspecialty;
    }

    public void setTspecialty(String tspecialty) {
        Tspecialty = tspecialty;
    }

    public String getTeduexperience() {
        return Teduexperience;
    }

    public void setTeduexperience(String teduexperience) {
        Teduexperience = teduexperience;
    }
}
