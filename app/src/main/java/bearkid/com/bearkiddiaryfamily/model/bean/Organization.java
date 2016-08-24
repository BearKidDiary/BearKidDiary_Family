package bearkid.com.bearkiddiaryfamily.model.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by admin on 2016/7/4.
 */
public class Organization implements Serializable{
    /**
     * 机构的名称
     */
    private String Oname;
    /**
     * 机构的地址
     */
    private String Oaddress;
    /**
     * 机构的成立时间
     */
    private BmobDate Otime;
    /**
     * 机构的标志图
     */
    private BmobFile Oavatar;
    /**
     * 机构的公告
     */
    private String Oannounce;

    public String getOname() {
        return Oname;
    }

    public void setOname(String oname) {
        Oname = oname;
    }

    public String getOaddress() {
        return Oaddress;
    }

    public void setOaddress(String oaddress) {
        Oaddress = oaddress;
    }

    public BmobDate getOtime() {
        return Otime;
    }

    public void setOtime(BmobDate otime) {
        Otime = otime;
    }

    public BmobFile getOavatar() {
        return Oavatar;
    }

    public void setOavatar(BmobFile oavatar) {
        Oavatar = oavatar;
    }

    public String getOannounce() {
        return Oannounce;
    }

    public void setOannounce(String oannounce) {
        Oannounce = oannounce;
    }
}
