package bearkid.com.bearkiddiaryfamily.model.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

import bearkid.com.bearkiddiaryfamily.R;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;

/**
 * 时间轴事件
 *
 * @author zy
 */
public class TimeLine implements Serializable {
    public static final String KID = "kid";
    public static final String RELEASETIME = "releasetime";
    public static final String RELEASECONTENT = "releasecontent";
    public static final String IMAGE1 = "image1";
    public static final String IMAGE2 = "image2";
    public static final String IMAGE3 = "image3";
    public static final String TYPE = "type";
    public static final String TYPELOGO = "typelogo";
    public static final String AUTHOR = "author";

    @Expose
    private Long Tid;

    @Expose
    private Long Treleasetime;

    @Expose
    private String Treleasecontent;

    @Expose
    private String Timage1, Timage2, Timage3;

    @Expose
    private String Ttype;

    @Expose
    private Integer Ttypelogo;

    @Expose
    private User author;

    private Kid kid;

    public Long getTid() {
        return Tid;
    }

    public void setTid(Long tid) {
        Tid = tid;
    }

    public Long getTreleasetime() {
        return Treleasetime;
    }

    public void setTreleasetime(Long treleasetime) {
        Treleasetime = treleasetime;
    }

    public String getTreleasecontent() {
        return Treleasecontent;
    }

    public void setTreleasecontent(String treleasecontent) {
        Treleasecontent = treleasecontent;
    }

    public String getTimage1() {
        return Timage1;
    }

    public void setTimage1(String timage1) {
        Timage1 = timage1;
    }

    public String getTimage2() {
        return Timage2;
    }

    public void setTimage2(String timage2) {
        Timage2 = timage2;
    }

    public String getTimage3() {
        return Timage3;
    }

    public void setTimage3(String timage3) {
        Timage3 = timage3;
    }

    public String getTtype() {
        return Ttype;
    }

    public void setTtype(String ttype) {
        Ttype = ttype;
    }

    public Integer getTtypelogo() {
        return Ttypelogo;
    }

    public void setTtypelogo(Integer ttypelogo) {
        Ttypelogo = ttypelogo;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Kid getKid() {
        return kid;
    }

    public void setKid(Kid kid) {
        this.kid = kid;
    }

    public static class Type {
        public static final int EAT = 1;
        public static final int FIRSTTIME = 2;
        public static final int BODY = 3;
        public static final int SPORT = 4;
        public static final int STUDY = 5;
        public static final int CAMERA = 6;
        public static final int BATH = 7;
        public static final int TREE = 8;
        public static final int ALCOHOL = 9;
        public static final int FISH = 10;
        public static final int FOOTPRINT = 11;

        public static int getLogoResource(int type) {
            switch (type) {
                case EAT:
                    return R.drawable.timeline_type_eat;
                case FIRSTTIME:
                    return R.drawable.timeline_type_first_time;
                case BODY:
                    return R.drawable.timeline_type_body;
                case SPORT:
                    return R.drawable.timeline_type_sport;
                case STUDY:
                    return R.drawable.timeline_type_study;
                case CAMERA:
                    return R.drawable.timeline_type_camera;
                case BATH:
                    return R.drawable.timeline_type_bath;
                case TREE:
                    return R.drawable.timeline_type_tree;
                case ALCOHOL:
                    return R.drawable.timeline_type_alcohol;
                case FISH:
                    return R.drawable.timeline_type_fish;
                case FOOTPRINT:
                    return R.drawable.timeline_type_footprint;
                default:
                    return R.drawable.timeline_type_camera;
            }
        }
    }
}
