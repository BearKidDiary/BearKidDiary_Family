package bearkid.com.bearkiddiaryfamily.utils;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import bearkid.com.bearkiddiaryfamily.model.bean.ChartPoint;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by hx on 2016/8/31.
 */
public class DataChart {
    private LineChartView lineChartView;
    private LineChartData lineChartData;

    private List<Line> lines;//坐标容器中的线集合
    private Line line;

    private Axis axisX;//x轴
    private Axis axisY;//Y轴

    private Viewport viewport;

    private List<AxisValue> axisValues;//x轴对应的坐标系集合
    private List<PointValue> pointValues;//y轴值

    public DataChart(LineChartView lineChartView){
        this.lineChartView = lineChartView;
        lineChartData = new LineChartData();
    }

    /**
     * 可以在坐标系中呈现多条折线
     * @param chartLists 折线的值的集合
     * @param axisXColor x轴的颜色
     * @param axisXName x轴的名称
     * @param axisYColors Y轴的颜色集合
     * @param axisYnames  Y轴名称的集合
     * @param axisXSize   x轴字体的大小
     * @param axisYSize   y轴字体的大小
     * @param type   什么类型，身高，体重，视力
     */
    public void setAxis(List<List<ChartPoint>> chartLists,int axisXColor,String axisXName, int[] axisYColors, String[] axisYnames, int axisXSize, int axisYSize, int type){

        lines = new ArrayList<>();
        switch (type){
            case ChartPoint.HEIGHT:
                setAxis(chartLists.get(0), axisXColor, axisXName, axisYColors[0], axisYnames[0], axisXSize, axisYSize , ChartPoint.HEIGHT);
                break;
            case ChartPoint.WEIGHT:
                setAxis(chartLists.get(0), axisXColor, axisXName, axisYColors[0], axisYnames[0], axisXSize, axisYSize , ChartPoint.WEIGHT);
                break;
            case ChartPoint.VISION:
                setAxis(chartLists.get(0), axisXColor, axisXName, axisYColors[0], axisYnames[0], axisXSize, axisYSize , ChartPoint.VISION_LEFT);
                setAxis(chartLists.get(1), axisXColor, axisXName, axisYColors[1], axisYnames[1], axisXSize, axisYSize , ChartPoint.VISION_RIGHT);
                break;
        }
    }

    /**
     * 设置x轴的坐标和折线的值，坐标轴的颜色等属性
     * @param chartPoints
     * @param axisXColor
     * @param axisXName
     * @param axisYColor
     * @param axisYname
     * @param axisXSize
     * @param axisYSize
     * @param type
     */
    private void setAxis(List<ChartPoint> chartPoints, int axisXColor, String axisXName, int axisYColor, String axisYname, int axisXSize, int axisYSize, int type){
        axisValues = new ArrayList<>();
        pointValues = new ArrayList<>();

        for (int i = 0; i < chartPoints.size(); i++){
            axisValues.add(new AxisValue(i).setLabel(chartPoints.get(i).getPoint_X()));
            pointValues.add(new PointValue().set(i, chartPoints.get(i).getPoint_Y()));
        }

        //X轴的属性
        axisX = new Axis(axisValues)
                .setHasLines(true)
                .setTextColor(axisXColor)
                .setMaxLabelChars(chartPoints.size())
                .setInside(false)
                .setName(axisXName)
                .setTextSize(axisXSize)
                .setHasTiltedLabels(false);

        switch (type){
            case ChartPoint.HEIGHT:
                axisY = new Axis()
                        .setHasLines(true)
                        .setTextColor(axisYColor)
                        .setName(axisYname)
                        .setTextSize(axisYSize);

                lineChartData.setAxisXBottom(axisX);
                lineChartData.setAxisYLeft(axisY);
                setViewport(0, 200, chartPoints.size() - 1, 50);
                break;
            case ChartPoint.WEIGHT:
                axisY = new Axis()
                        .setHasLines(true)
                        .setTextColor(axisYColor)
                        .setName(axisYname)
                        .setTextSize(axisYSize);

                lineChartData.setAxisXBottom(axisX);
                lineChartData.setAxisYLeft(axisY);
                setViewport(0, 100, chartPoints.size() - 1, 0);
                break;
            case ChartPoint.VISION_LEFT:
                axisY = new Axis()
                        .setHasLines(true)
                        .setTextColor(axisYColor)
                        .setName(axisYname)
                        .setTextSize(axisYSize);
                setViewport(0, 6, chartPoints.size() - 1, 4);

                lineChartData.setAxisXBottom(axisX);
                lineChartData.setAxisYLeft(axisY);
                break;
            case ChartPoint.VISION_RIGHT:
                axisY = new Axis()
                        .setHasLines(true)
                        .setTextColor(axisYColor)
                        .setName(axisYname)
                        .setTextSize(axisYSize);
                lineChartData.setAxisXBottom(axisX);
                lineChartData.setAxisYRight(axisY);
                setViewport(0, 6, chartPoints.size() - 1, 4);
                break;
        }

        line = new Line(pointValues)
                .setColor(axisYColor)
                .setCubic(true)
                .setHasLabels(false)
                .setHasPoints(true)
                .setShape(ValueShape.CIRCLE);
        lines.add(line);

    }

    /**
     * 在原来的折线上改变折线的数据，增加过度的效果
     */
    public void changeDataList(List<List<ChartPoint>> chartLists){
        for(int i = 0; i < lineChartData.getLines().size(); i++){
            Line line = lineChartData.getLines().get(i);
            List<ChartPoint> chartPoint = new ArrayList<>();
            chartPoint = chartLists.get(i);
            for(int j = 0; j < chartPoint.size(); j++){
                line.getValues().get(j).setTarget(line.getValues().get(j).getX(), chartPoint.get(j).getPoint_Y());
            }
        }
    }

    /**
     * 设置折线图的lineChartData和lines
     */
    public void setLineChart(){

        lineChartView.cancelDataAnimation();

        lineChartData.setLines(lines);

        lineChartView.setLineChartData(lineChartData);
        lineChartView.setVisibility(View.VISIBLE);
        lineChartView.setZoomType(ZoomType.HORIZONTAL);

        lineChartView.setViewportCalculationEnabled(false);
        lineChartView.setCurrentViewport(viewport);
        lineChartView.setMaximumViewport(viewport);

        lineChartView.startDataAnimation(500);
    }

    private void setViewport(float left, float top, float right, float bottom){
        viewport = new Viewport(left, top, right, bottom);
    }

}
