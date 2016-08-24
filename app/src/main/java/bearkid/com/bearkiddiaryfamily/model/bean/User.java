package bearkid.com.bearkiddiaryfamily.model.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 家庭版用户
 */

public class User implements Serializable {
    public static final String ID = "Uid";
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
    @Expose
    private Long Uid;

    @Expose
    private String Uname = null;

    @Expose
    private String Usex = null;

    @Expose
    private String Uphone = null;

    @Expose
    private String Uarea = null;

    private String Upsw;

    @Expose
    private String Uavatar;

    @Expose
    private String Uemail = null;

    /**
     * 该用户天生所在的家庭
     */
    private Family ownFamily;

    /**
     * 该用户参与的家庭，即别人邀请自己加入的家庭
     */
    private Set<Family> accessFamily = new HashSet<>();

    /**
     * 如果该用户是管理员，批准过的课程列表
     */
    private Set<Course> approverCourse = new HashSet<>();

    /**
     * 如果该用户是教师，任教的课程
     */
    private Set<Course> teachCourse = new HashSet<>();

    /**
     * 如果是管理员，创建的机构
     */
    private Organization createOrganization;

    /**
     * 作为老师参与其中的机构
     */
    private Set<Organization> workOrganization = new HashSet<>();
    /**
     * 作为家长参与其中的机构
     */
    private Set<Organization> parentOrganization = new HashSet<>();

    /**
     * 请假申请
     */
    private Set<Leave_Application> application = new HashSet<>();

    /**
     * 请假审批
     */
    private Set<Leave_Application> approval = new HashSet<>();

    public Long getUid() {
        return Uid;
    }

    public void setUid(Long uid) {
        Uid = uid;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String uname) {
        Uname = uname;
    }

    public String getUsex() {
        return Usex;
    }

    public void setUsex(String usex) {
        Usex = usex;
    }

    public String getUphone() {
        return Uphone;
    }

    public void setUphone(String uphone) {
        Uphone = uphone;
    }

    public String getUarea() {
        return Uarea;
    }

    public void setUarea(String uarea) {
        Uarea = uarea;
    }

    public String getUpsw() {
        return Upsw;
    }

    public void setUpsw(String upsw) {
        Upsw = upsw;
    }

    public String getUavatar() {
        return Uavatar;
    }

    public void setUavatar(String uavatar) {
        Uavatar = uavatar;
    }

    public String getUemail() {
        return Uemail;
    }

    public void setUemail(String uemail) {
        Uemail = uemail;
    }

    public Family getOwnFamily() {
        return ownFamily;
    }

    public void setOwnFamily(Family ownFamily) {
        this.ownFamily = ownFamily;
    }

    public Set<Family> getAccessFamily() {
        return accessFamily;
    }

    public void setAccessFamily(Set<Family> accessFamily) {
        this.accessFamily = accessFamily;
    }

    public Set<Course> getApproverCourse() {
        return approverCourse;
    }

    public void setApproverCourse(Set<Course> approverCourse) {
        this.approverCourse = approverCourse;
    }

    public Set<Course> getTeachCourse() {
        return teachCourse;
    }

    public void setTeachCourse(Set<Course> teachCourse) {
        this.teachCourse = teachCourse;
    }

    public Organization getCreateOrganization() {
        return createOrganization;
    }

    public void setCreateOrganization(Organization createOrganization) {
        this.createOrganization = createOrganization;
    }

    public Set<Organization> getWorkOrganization() {
        return workOrganization;
    }

    public void setWorkOrganization(Set<Organization> workOrganization) {
        this.workOrganization = workOrganization;
    }

    public Set<Organization> getParentOrganization() {
        return parentOrganization;
    }

    public void setParentOrganization(Set<Organization> parentOrganization) {
        this.parentOrganization = parentOrganization;
    }

    public Set<Leave_Application> getApplication() {
        return application;
    }

    public void setApplication(Set<Leave_Application> application) {
        this.application = application;
    }

    public Set<Leave_Application> getApproval() {
        return approval;
    }

    public void setApproval(Set<Leave_Application> approval) {
        this.approval = approval;
    }

    @Override
    public String toString() {
        return "Uphone = " + Uphone
                + "Uname" + Uname;
    }
}
