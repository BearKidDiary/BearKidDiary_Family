package bearkid.com.bearkiddiaryfamily.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bearkid.com.bearkiddiaryfamily.model.bean.Course;
import bearkid.com.bearkiddiaryfamily.model.bean.FamilyKidMsg;
import cn.bmob.v3.datatype.BmobDate;

/**
 * 给孩子页面提供假数据
 */
public class FadeModel {
    public static FamilyKidMsg getFamilyKidMsg() {
        Course course = new Course();
        course.setCname("语文课");
        course.setCclasstime(new Date().getTime());
        course.setCendtime(new Date().getTime());
        List<Course> list = new ArrayList<>();
        list.add(course);
        FamilyKidMsg fkm = new FamilyKidMsg();
        fkm.setAge(12);
        fkm.setName("大宝");
        fkm.setCourses(list);
        return fkm;
    }
}
