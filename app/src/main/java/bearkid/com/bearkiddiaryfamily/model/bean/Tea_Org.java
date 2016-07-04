package bearkid.com.bearkiddiaryfamily.model.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobPointer;

/**
 * 教师和机构的雇佣关系
 */
public class Tea_Org extends BmobObject{
    /**
     * 老师在机构中的身份 （普通教师、管理员）
     */
    private Integer identity;
    /**
     * 教师
     */
    private BmobPointer teacher;
    /**
     * 机构
     */
    private BmobPointer organization;

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public BmobPointer getTeacher() {
        return teacher;
    }

    public void setTeacher(BmobPointer teacher) {
        this.teacher = teacher;
    }

    public BmobPointer getOrganization() {
        return organization;
    }

    public void setOrganization(BmobPointer organization) {
        this.organization = organization;
    }
}
