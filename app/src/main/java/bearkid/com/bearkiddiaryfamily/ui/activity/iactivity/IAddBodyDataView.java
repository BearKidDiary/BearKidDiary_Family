package bearkid.com.bearkiddiaryfamily.ui.activity.iactivity;

/**
 * Created by YarenChoi on 2016/8/29.
 * 添加身体数据界面接口
 */
public interface IAddBodyDataView {

    /**
     * 显示加载进度
     */
    void showProgress();

    /**
     * 隐藏加载进度
     */
    void hideProgress();

    /**
     * 显示加载信息
     */
    void showResult(String result);

    /**
     * 获取想要添加的身高信息
     * @return 界面返回的身高信息
     */
    String getHeight();

    /**
     * 获取想要添加的体重信息
     * @return 界面返回的体重信息
     */
    String getWeight();

    /**
     * 获取想要添加的左眼视力信息
     * @return 界面返回的左眼视力信息
     */
    String getLeftVision();

    /**
     * 获取想要添加的右眼视力信息
     * @return 界面返回的右眼视力信息
     */
    String getRightVision();

    /**
     * 获取想要添加的身高日期信息
     * @return 界面返回的身高日期信息
     */
    Long getHeightDate();

    /**
     * 获取想要添加的体重日期信息
     * @return 界面返回的体重日期信息
     */
    Long getWeightDate();

    /**
     * 获取想要添加的视力日期信息
     * @return 界面返回的视力日期信息
     */
    Long getVisionDate();

    /**
     * 添加数据成功后finish当前界面并刷新身体数据
     */
    void exit();
}
