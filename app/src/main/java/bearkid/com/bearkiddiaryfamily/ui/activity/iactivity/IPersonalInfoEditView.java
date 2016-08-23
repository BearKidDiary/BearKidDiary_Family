package bearkid.com.bearkiddiaryfamily.ui.activity.iactivity;

/**
 * Created by YarenChoi on 2016/8/23.
 * 个人信息编辑界面
 */
public interface IPersonalInfoEditView {

    /**
     * 获取想要修改的内容
     * @return 内容
     */
    String getEditContent();

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
