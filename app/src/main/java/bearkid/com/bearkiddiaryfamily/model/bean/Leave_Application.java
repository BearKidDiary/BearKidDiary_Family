package bearkid.com.bearkiddiaryfamily.model.bean;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class Leave_Application implements Serializable{

	public static final Integer APPROVE_YES = 1;//同意
	public static final Integer APPROVE_NO = 0;//不同意
	
	public static final Integer APPROVED = 1;//已审批
	public static final Integer UNAPPROVED = 0;//未审批
	
	public static final Integer SICK_LEAVE = 1;//病假
	public static final Integer THING_LEAVE = 2;//事假
	
	public static final String LAID = "LAid"; 
	public static final String LAISAPPROVED = "LAisapproved";
	public static final String LASTATUS = "LAstatus";
	public static final String LASTARTTIME = "LAstarttime";
	public static final String LAENDTIME = "LAendtime";
	public static final String LATYPE = "LAtype";
	public static final String LAREASON = "LAreason";
	public static final String LACOMMENT = "LAcomment";
	
	@Expose
	private Long LAid;
	/**
	 * 是否同意
	 */
	@Expose
	private Integer LAisapproved;
	/**
	 * 申请状态
	 */
	@Expose
	private Integer LAstatus;
	
	/**
	 * 请假开始时间
	 */
	@Expose
	private Long LAstarttime;
	
	/**
	 * 请假结束时间
	 */
	@Expose
	private Long LAendtime;
	
	/**
	 * 请假类型
	 */
	@Expose
	private Integer LAtype;
	
	/**
	 * 请假原因
	 */
	@Expose
	private String LAreason;
	
	/**
	 * 审批人评语
	 */
	@Expose
	private String LAcomment;
	
	/**
	 * 审批人
	 */
	@Expose
	private User LArover;
	
	/**
	 * 申请人
	 */
	@Expose
	private User LAapplicant;
	
	/**
	 * 对应的机构
	 */
	@Expose
	private Organization LAorg;

	public Long getLAid() {
		return LAid;
	}

	public void setLAid(Long lAid) {
		LAid = lAid;
	}

	public Integer getLAisapproved() {
		return LAisapproved;
	}

	public void setLAisapproved(Integer lAisapproved) {
		LAisapproved = lAisapproved;
	}

	public Integer getLAstatus() {
		return LAstatus;
	}

	public void setLAstatus(Integer lAstatus) {
		LAstatus = lAstatus;
	}

	public Long getLAstarttime() {
		return LAstarttime;
	}

	public void setLAstarttime(Long lAstarttime) {
		LAstarttime = lAstarttime;
	}

	public Long getLAendtime() {
		return LAendtime;
	}

	public void setLAendtime(Long lAendtime) {
		LAendtime = lAendtime;
	}

	public Integer getLAtype() {
		return LAtype;
	}

	public void setLAtype(Integer lAtype) {
		LAtype = lAtype;
	}

	public String getLAreason() {
		return LAreason;
	}

	public void setLAreason(String lAreason) {
		LAreason = lAreason;
	}

	public String getLAcomment() {
		return LAcomment;
	}

	public void setLAcomment(String lAcomment) {
		LAcomment = lAcomment;
	}

	public User getLArover() {
		return LArover;
	}

	public void setLArover(User lArover) {
		LArover = lArover;
	}

	public User getLAapplicant() {
		return LAapplicant;
	}

	public void setLAapplicant(User lAapplicant) {
		LAapplicant = lAapplicant;
	}

	public Organization getLAorg() {
		return LAorg;
	}

	public void setLAorg(Organization lAorg) {
		LAorg = lAorg;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{" + "LAid:" + LAid + "," + "LAstatus:" + LAstatus + "," + "LAstarttime:" + LAstarttime + "," + "LAendtime:" + LAendtime + ","
				+ "LAtype:" + LAtype + "," + "LAreason:" + LAreason + "," + "LAcomment:" + LAcomment + "}";
	}
}