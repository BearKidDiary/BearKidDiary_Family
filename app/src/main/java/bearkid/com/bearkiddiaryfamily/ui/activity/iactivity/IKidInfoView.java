package bearkid.com.bearkiddiaryfamily.ui.activity.iactivity;

import java.util.List;

import bearkid.com.bearkiddiaryfamily.model.bean.Height;
import bearkid.com.bearkiddiaryfamily.model.bean.Kid;
import bearkid.com.bearkiddiaryfamily.model.bean.Vision;
import bearkid.com.bearkiddiaryfamily.model.bean.Weight;

/**
 * Created by YarenChoi on 2016/7/20.
 * 显示孩子信息界面接口
 */
public interface IKidInfoView extends IBaseView {

    /**
     * 查询到kid信息后初始化kid对象
     * @param kid 从服务器返回的kid对象
     */
    void initKid(Kid kid);

    /**
     * 显示孩子姓名
     * @param name 孩子姓名
     */
    void showName(String name);

    /**
     * 显示孩子生日
     * @param birthday 格式化的生日日期
     */
    void showBirthday(String birthday);

    /**
     * 显示孩子性别
     * @param gender 男或女
     */
    void showGender(String gender);

    /**
     * 显示身高数据
     * @param heightList 从服务器拿下来的数据
     */
    void showHeight(List heightList);

    /**
     * 清除界面上的身高数据
     */
    void clearHeightData();

    /**
     * 显示体重数据
     * @param weightList 从服务器拿下来的数据
     */
    void showWeight(List weightList);

    /**
     * 清除界面上的体重数据
     */
    void clearWeightData();

    /**
     * 显示视力数据
     * @param visionList 从服务器上拿下来的数据
     */
    void showVision(List visionList);

    /**
     * 清除界面上的视力数据
     */
    void clearVisionData();

    /**
     * 显示家长叮嘱
     * @param ask 家长叮嘱
     */
    void setAsk(String ask);
}
