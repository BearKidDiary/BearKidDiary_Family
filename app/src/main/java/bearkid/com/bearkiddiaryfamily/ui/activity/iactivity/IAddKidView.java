package bearkid.com.bearkiddiaryfamily.ui.activity.iactivity;

/**
 * Created by admin on 2016/8/23.
 */
public interface IAddKidView {

    /**
     *
     * @return 孩子id
     */
    Long getKidId();

    /**
     *
     * @return 孩子姓名
     */
    String getKidName();

    /**
     *
     * @return 孩子性别
     */
    String getKidGender();

    /**
     *
     * @return 孩子生日
     */
    long getKidBirthday();

    /**
     *
     * @return 家长叮嘱
     */
    String getKidAsk();

    /**
     * 显示加载进度条
     */
    void showProgressDialog();

    /**
     * 隐藏加载进度条
     */
    void hideProgressDialog();

    /**
     * 显示加载结果
     * @param result 加载结果文本描述
     */
    void showResult(String result);

    /**
     * 修改成功后退出界面
     */
    void exit();
}
