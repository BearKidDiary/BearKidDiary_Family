package bearkid.com.bearkiddiaryfamily.model.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;

/**
 * 课程信息
 */
public class Course implements Serializable {
    @Expose
    private Long Cid;
    /**
     * 上课时间
     */
    @Expose
    private Long Cclasstime;
    /**
     * 下课时间
     */
    @Expose
    private Long Cendtime;
    /**
     * 开学时间
     */
    @Expose
    private Long Ctime;
    /**
     * 毕业时间
     */
    @Expose
    private Long Cofftime;
    /**
     * 开课审批人
     */
    private User approver;
    /**
     * 课程的开课背景、原因
     */
    @Expose
    private String Cbackground;
    /**
     * 课程描述
     */
    @Expose
    private String Cdesc;
    /**
     * 课程名称
     */
    @Expose
    private String Cname;
    /**
     * 周一到周日是否需要上课
     */
    @Expose
    private Boolean Cmonday, Ctuesday, Cwednesday, Cthursday, Cfriday, Csaturday, Csunday;
    /**
     * 课程的简介图片URL
     */
    @Expose
    private String Cimage;
    /**
     * 课程所属的机构
     */
    private Organization organization;
    /**
     * 课程的任课老师
     */
    @Expose
    private User teacher;
    /**
     * 参与课程的孩子
     */
    private Set<Kid> students = new HashSet<>();
    /**
     * 课程中各个学生获得的成绩
     */
    private Set<Score> score = new HashSet<>();

    public Long getCid() {
        return Cid;
    }

    public void setCid(Long cid) {
        Cid = cid;
    }

    public Long getCclasstime() {
        return Cclasstime;
    }

    public void setCclasstime(Long cclasstime) {
        Cclasstime = cclasstime;
    }

    public Long getCendtime() {
        return Cendtime;
    }

    public void setCendtime(Long cendtime) {
        Cendtime = cendtime;
    }

    public Long getCtime() {
        return Ctime;
    }

    public void setCtime(Long ctime) {
        Ctime = ctime;
    }

    public Long getCofftime() {
        return Cofftime;
    }

    public void setCofftime(Long cofftime) {
        Cofftime = cofftime;
    }

    public User getApprover() {
        return approver;
    }

    public void setApprover(User approver) {
        this.approver = approver;
    }

    public String getCbackground() {
        return Cbackground;
    }

    public void setCbackground(String cbackground) {
        Cbackground = cbackground;
    }

    public String getCdesc() {
        return Cdesc;
    }

    public void setCdesc(String cdesc) {
        Cdesc = cdesc;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public Boolean getCmonday() {
        return Cmonday;
    }

    public void setCmonday(Boolean cmonday) {
        Cmonday = cmonday;
    }

    public Boolean getCtuesday() {
        return Ctuesday;
    }

    public void setCtuesday(Boolean ctuesday) {
        Ctuesday = ctuesday;
    }

    public Boolean getCwednesday() {
        return Cwednesday;
    }

    public void setCwednesday(Boolean cwednesday) {
        Cwednesday = cwednesday;
    }

    public Boolean getCthursday() {
        return Cthursday;
    }

    public void setCthursday(Boolean cthursday) {
        Cthursday = cthursday;
    }

    public Boolean getCfriday() {
        return Cfriday;
    }

    public void setCfriday(Boolean cfriday) {
        Cfriday = cfriday;
    }

    public Boolean getCsaturday() {
        return Csaturday;
    }

    public void setCsaturday(Boolean csaturday) {
        Csaturday = csaturday;
    }

    public Boolean getCsunday() {
        return Csunday;
    }

    public void setCsunday(Boolean csunday) {
        Csunday = csunday;
    }

    public String getCimage() {
        return Cimage;
    }

    public void setCimage(String cimage) {
        Cimage = cimage;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public Set<Kid> getStudents() {
        return students;
    }

    public void setStudents(Set<Kid> students) {
        this.students = students;
    }

    public Set<Score> getScore() {
        return score;
    }

    public void setScore(Set<Score> score) {
        this.score = score;
    }
}
