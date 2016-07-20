package bearkid.com.bearkiddiaryfamily.model.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;

/**
 * 时间轴事件
 *
 * @author 张宇
 */
public class TimeLine extends BmobObject {
    public static final String KID = "kid";
    public static final String RELEASETIME = "releasetime";
    public static final String RELEASECONTENT = "releasecontent";
    public static final String IMAGE1 = "image1";
    public static final String IMAGE2 = "image2";
    public static final String IMAGE3 = "image3";
    public static final String TYPE = "type";
    public static final String TYPELOGO = "typelogo";
    public static final String AUTHOR = "author";
    private BmobDate releasetime;
    private String releasecontent;
    private BmobFile image1, image2, image3;
    private String type;
    private Integer typelogo;
    private Kid kid;
    private User author;

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

    public BmobDate getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(BmobDate releasetime) {
        this.releasetime = releasetime;
    }

    public String getReleasecontent() {
        return releasecontent;
    }

    public void setReleasecontent(String releasecontent) {
        this.releasecontent = releasecontent;
    }

    public BmobFile getImage1() {
        return image1;
    }

    public void setImage1(BmobFile image1) {
        this.image1 = image1;
    }

    public BmobFile getImage2() {
        return image2;
    }

    public void setImage2(BmobFile image2) {
        this.image2 = image2;
    }

    public BmobFile getImage3() {
        return image3;
    }

    public void setImage3(BmobFile image3) {
        this.image3 = image3;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTypelogo() {
        return typelogo;
    }

    public void setTypelogo(Integer typelogo) {
        this.typelogo = typelogo;
    }
}
