package bearkid.com.bearkiddiaryfamily.ui.activity.iactivity;

/**
 * Created by admin on 2016/7/20.
 */
public interface IKidInfoView extends IBaseView {
    void showHeightAndWeight(int height, int weight, String date);

    void showVision(double left, double right, String date);

    void showExhort(int count, String exhort);
}
