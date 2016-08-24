package bearkid.com.bearkiddiaryfamily.model.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

public class Kid implements Serializable{
    public static final String NAME = "Kname";
    public static final String SEX = "Ksex";
    public static final String BIRTHDAY = "Kbirthday";
    public static final String ASK = "Kask";
    public static final String FAMILY = "family";
    public static final String AVATAR = "Kavatar";

    @Expose
    private Long Kid;
    /**
     * 孩子名字
     */
    @Expose
    private String Kname;
    /**
     * 孩子性别
     */
    @Expose
    private String Ksex;
    /**
     * 孩子出生时间
     */
    @Expose
    private Long Kbirthday;
    /**
     * 家长叮嘱
     */
    @Expose
    private String Kask;
    /**
     * 孩子获得的小红花数目
     */
    @Expose
    private Integer Kflowers;
    /**
     * 所在的家庭
     */
    private Family family;
    /**
     * 头像URL
     */
    @Expose
    private String Kavatar;
    /**
     * 孩子的时间轴
     */
    private Set<TimeLine> Ktimeline = new HashSet<>();
    /**
     * 孩子的重量
     */
    private Set<Weight> weight = new HashSet<>();
    /**
     * 孩子的视力
     */
    private Set<Vision> vision = new HashSet<>();
    /**
     * 孩子的身高
     */
    private Set<Height> height = new HashSet<>();
    /**
     * 孩子参与的课程
     */
    private Set<Course> attendCourse = new HashSet<>();

    /**
     * 孩子在不同课程中获得的成绩
     */
    private Set<Score> score = new HashSet<>();

    public Long getKid() {
        return Kid;
    }

    public void setKid(Long kid) {
        Kid = kid;
    }

    public String getKname() {
        return Kname;
    }

    public void setKname(String kname) {
        Kname = kname;
    }

    public String getKsex() {
        return Ksex;
    }

    public void setKsex(String ksex) {
        Ksex = ksex;
    }

    public Long getKbirthday() {
        return Kbirthday;
    }

    public void setKbirthday(Long kbirthday) {
        Kbirthday = kbirthday;
    }

    public String getKask() {
        return Kask;
    }

    public void setKask(String kask) {
        Kask = kask;
    }

    public Integer getKflowers() {
        return Kflowers;
    }

    public void setKflowers(Integer kflowers) {
        Kflowers = kflowers;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public String getKavatar() {
        return Kavatar;
    }

    public void setKavatar(String kavatar) {
        Kavatar = kavatar;
    }

    public Set<TimeLine> getKtimeline() {
        return Ktimeline;
    }

    public void setKtimeline(Set<TimeLine> ktimeline) {
        Ktimeline = ktimeline;
    }

    public Set<Weight> getWeight() {
        return weight;
    }

    public void setWeight(Set<Weight> weight) {
        this.weight = weight;
    }

    public Set<Vision> getVision() {
        return vision;
    }

    public void setVision(Set<Vision> vision) {
        this.vision = vision;
    }

    public Set<Height> getHeight() {
        return height;
    }

    public void setHeight(Set<Height> height) {
        this.height = height;
    }

    public Set<Course> getAttendCourse() {
        return attendCourse;
    }

    public void setAttendCourse(Set<Course> attendCourse) {
        this.attendCourse = attendCourse;
    }

    public Set<Score> getScore() {
        return score;
    }

    public void setScore(Set<Score> score) {
        this.score = score;
    }
}