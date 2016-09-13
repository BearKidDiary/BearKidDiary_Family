package bearkid.com.bearkiddiaryfamily.model.bean;

/**
 * Created by hx on 2016/8/31.
 */
public class ChartPoint {

    public static final int HEIGHT = 0;
    public static final int WEIGHT = 1;
    public static final int VISION_LEFT = 2;
    public static final int VISION_RIGHT = 3;
    public static final int VISION = 4;

    private String point_X;
    private Float point_Y;

    public ChartPoint(String point_X, Float point_Y){
        this.point_X = point_X;
        this.point_Y = point_Y;
    }

    public String getPoint_X() {
        return point_X;
    }

    public void setPoint_X(String point_X) {
        this.point_X = point_X;
    }

    public Float getPoint_Y() {
        return point_Y;
    }

    public void setPoint_Y(Float point_Y) {
        this.point_Y = point_Y;
    }
}
