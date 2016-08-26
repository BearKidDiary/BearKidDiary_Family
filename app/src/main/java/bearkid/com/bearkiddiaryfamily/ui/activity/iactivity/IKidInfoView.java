package bearkid.com.bearkiddiaryfamily.ui.activity.iactivity;

import java.util.List;

import bearkid.com.bearkiddiaryfamily.model.bean.Height;
import bearkid.com.bearkiddiaryfamily.model.bean.Vision;
import bearkid.com.bearkiddiaryfamily.model.bean.Weight;

/**
 * Created by YarenChoi on 2016/7/20.
 * 显示孩子信息界面接口
 */
public interface IKidInfoView extends IBaseView {

    void showHeight(List<Height> heightList);

    void clearHeightData();

    void showWeight(List<Weight> weightList);

    void clearWeightData();

    void showVision(List<Vision> visionList);

    void clearVisionData();

    void setExhort(String exhort);
}
