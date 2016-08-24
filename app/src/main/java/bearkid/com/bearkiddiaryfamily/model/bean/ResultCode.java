
package bearkid.com.bearkiddiaryfamily.model.bean;

public class ResultCode {

	private ResultCode() {
	}

	public static final int SUCCESS = 0;

	/**
	 * 缺少请求参数
	 */
	public static final int ERROR_MISSING_PARAMETER = -1;
	/**
	 * 不存在该用户
	 */
	public static final int ERROR_NO_USER = -2;
	/**
	 * 已经有家庭，不能重复创建
	 */
	public static final int ERROR_EXIST_FAMILY = -3;
	/**
	 * 不存在该家庭
	 */
	public static final int ERROR_NO_FAMILY = -4;
	/**
	 * 不存在对应的关联关系
	 */
	public static final int ERROR_NO_RELATION = -5;
	/**
	 * 不存在该机构
	 */
	public static final int ERROR_NO_ORG = -8;
	/**
	 * 不存在该孩子
	 */
	public static final int ERROR_NO_KID = -6;
	/**
	 * 已存在该孩子
	 */
	public static final int ERROR_EXIST_KID = -7;
	/**
	 * 不存在该课程
	 */
	public static final int ERROR_NO_COURSE = -9;
	/**
	 * 不能正常获得结果，过程发生了错误
	 */
	public static final int ERROR_NO_RESULT = -100;
	/**
	 * 错误
	 */
	public static final int ERROR = -200;
	/**
	 * 没有数据
	 */
	public static final int NO_RESULT = -101;
	
	/**
	 * 提交，保存失败
	 */
	public static final int ERROR_COMMIT = -102;
	/**
	 * 无权限操作
	 */
	public static final int ERROR_NO_PERMISSION = -103;
	/**
	 * 登录失败
	 */
	public static final int ERROR_LOGIN = -104;
}
