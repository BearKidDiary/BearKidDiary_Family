package bearkid.com.bearkiddiaryfamily.model.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

public class Org_Course_Kid extends BmobObject {
    private Organization organization;
    private Kid kid;
    private BmobRelation courses;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Kid getKid() {
        return kid;
    }

    public void setKid(Kid kid) {
        this.kid = kid;
    }

    public BmobRelation getCourses() {
        return courses;
    }

    public void setCourses(BmobRelation courses) {
        this.courses = courses;
    }
}
