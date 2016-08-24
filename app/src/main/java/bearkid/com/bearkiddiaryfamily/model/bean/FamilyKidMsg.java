package bearkid.com.bearkiddiaryfamily.model.bean;

import android.graphics.Bitmap;

import java.util.List;

import cn.bmob.v3.datatype.BmobFile;

/**
 * 孩子页面 我的孩子列表上的信息
 */
@Deprecated
public class FamilyKidMsg {
    private String name;
    private int age;
    private BmobFile avatar;
    private List<Course> courses;

    public BmobFile getAvatar() {
        return avatar;
    }

    public void setAvatar(BmobFile avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
