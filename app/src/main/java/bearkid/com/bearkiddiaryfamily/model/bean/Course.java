package bearkid.com.bearkiddiaryfamily.model.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;

/**
 * 课程信息
 */
public class Course extends BmobObject{
    /**
     * 开课时间
     */
    private BmobDate Cstarttime;
    /**
     * 结束时间
     */
    private BmobDate Cendtime;
    /**
     * 开课审批人
     */
    private Teacher Capprover;
    /**
     * 课程的开课背景、原因
     */
    private String Cbackground;
    /**
     * 课程描述
     */
    private String Cdesc;
    /**
     * 课程名称
     */
    private String Cname;
}
