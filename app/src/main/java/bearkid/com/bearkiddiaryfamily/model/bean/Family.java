package bearkid.com.bearkiddiaryfamily.model.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by admin on 2016/7/11.
 */
public class Family implements Serializable {
    @Expose
    private Long Fid;
    /**
     * 家庭的名字
     */
    @Expose
    private String Fname;
    /**
     * 家庭的创建者， 只有创建者可以新增孩子
     */
    private User creator;
    /**
     * 家庭的成员
     */
    private Set<User> members = new HashSet<>();
    /**
     * 家庭的孩子
     */
    private Set<Kid> kid = new HashSet<>();

    public Long getFid() {
        return Fid;
    }

    public void setFid(Long fid) {
        Fid = fid;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }

    public Set<Kid> getKid() {
        return kid;
    }

    public void setKid(Set<Kid> kid) {
        this.kid = kid;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Family) {
            Family other = (Family) obj;
            return other.Fid != null && other.Fid.equals(Fid);
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (Fid != null) {
            return Fid.hashCode();
        }
        return super.hashCode();
    }
}
