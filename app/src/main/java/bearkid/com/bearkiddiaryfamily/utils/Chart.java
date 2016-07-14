package bearkid.com.bearkiddiaryfamily.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.List;
import java.util.Objects;

/**
 * Created by -HungXum on 2015/12/22.
 */
public class Chart {
    private GraphicalView mGraphcalView;
    private XYMultipleSeriesDataset multipleSeriesDataset;// 数据集容器
    private XYMultipleSeriesRenderer multipleSeriesRenderer;// 渲染器容器
    private XYSeries series; // 单条曲线数据集
    private XYSeriesRenderer seriesRenderer;// 单条曲线渲染器

    private Context context;

    public Chart(Context context){
        this.context = context;
    }

    //获取图标
    public GraphicalView getmGraphcalView(){
        //get函数取决于要哪一种图表
        mGraphcalView = ChartFactory.getCubeLineChartView(context,multipleSeriesDataset,multipleSeriesRenderer,0);
        return mGraphcalView;
    }

    //获取数据集，及xy坐标的集合


    public void setMultipleSeriesDataset(String[] curveTitle) {//设置每个图底下的点与线的样板
        multipleSeriesDataset = new XYMultipleSeriesDataset();
        for (int i = 0; i < curveTitle.length;i++){
            series = new XYSeries(curveTitle[i]);
            multipleSeriesDataset.addSeries(series);
        }
    }


    public void setMultipleSeriesDataset(String curveTitle) {//设置每个图底下的点与线的样板
        multipleSeriesDataset = new XYMultipleSeriesDataset();
        series = new XYSeries(curveTitle);
        multipleSeriesDataset.addSeries(series);
    }

    /*
    * maxX      x轴的最大
    * maxX      y轴的最大
    * chartTlie 曲线的标题
    * xTile     x轴上的标题
    * yTile     y轴上的标题
    * axeColor  坐标轴的颜色
    * labelColor 标题颜色
    * curveColor 曲线颜色
    * gridColor  网格颜色
    * */

    public void setMultipleSeriesRenderer(int yMax , String chartTitle, String xTitle, String yTitle, int axeColor,
                                          int labelColor,int xyTitleColor, int curveColor) {
        multipleSeriesRenderer = new XYMultipleSeriesRenderer();
        if (chartTitle != null){
            multipleSeriesRenderer.setChartTitle(chartTitle);
        }

        multipleSeriesRenderer.setXTitle(xTitle);
        multipleSeriesRenderer.setYTitle(yTitle);
        multipleSeriesRenderer.setYAxisMin(0);
        multipleSeriesRenderer.setYAxisMax(yMax);
        multipleSeriesRenderer.setXLabelsColor(labelColor);//设置X坐标轴上坐标点的颜色
        multipleSeriesRenderer.setYLabelsColor(0, labelColor);//设置Y坐标轴上坐标点的颜色
        multipleSeriesRenderer.setLabelsColor(xyTitleColor);//包括x,y标题，顶上的charttile颜色
        multipleSeriesRenderer.setXLabels(0);//将X轴可见区域分成0分,这样坐标是文字显示的出来不会有数字
        multipleSeriesRenderer.setYLabels(10);
        multipleSeriesRenderer.setXLabelsAlign(Paint.Align.CENTER);
        multipleSeriesRenderer.setYLabelsAlign(Paint.Align.RIGHT);
        multipleSeriesRenderer.setAxisTitleTextSize(40);//坐标轴上title的字体大小
        multipleSeriesRenderer.setChartTitleTextSize(40);
        multipleSeriesRenderer.setLabelsTextSize(30); // x,y坐标上的坐标字体大小
        multipleSeriesRenderer.setLegendTextSize(40); // 每个图底下的点与线的样板字体大小
        multipleSeriesRenderer.setPointSize(5f);
        multipleSeriesRenderer.setFitLegend(false);
        multipleSeriesRenderer.setMargins(new int[]{50, 60, 50, 60});//上，左，下，右
        multipleSeriesRenderer.setZoomEnabled(false, false);
//        multipleSeriesRenderer.setGridColor(gridColor);
        multipleSeriesRenderer.setAxesColor(axeColor);//坐标轴颜色
        multipleSeriesRenderer.setPanEnabled(false, false);//不允许拖动
        multipleSeriesRenderer.setApplyBackgroundColor(true);//设置支持设置背景色
//        multipleSeriesRenderer.setBackgroundColor(Color.argb(255,238,118,33));//背景色,用平时的R.color.orange没有用
        multipleSeriesRenderer.setBackgroundColor(Color.argb(150, 255, 64, 129));//背景色,用平时的R.color.orange没有用
        multipleSeriesRenderer.setMarginsColor(Color.WHITE);
//        multipleSeriesRenderer.setMarginsColor(Color.argb(150,255,130,188));// 边距背景色，默认背景色为黑色，这里修改为白色

        seriesRenderer = new XYSeriesRenderer();
        seriesRenderer.setColor(curveColor);//曲线颜色
        seriesRenderer.setFillPoints(false);
        seriesRenderer.setDisplayChartValues(true);//设置显示曲线点上的数值
        seriesRenderer.setChartValuesSpacing(15);//数值与点的距离
        seriesRenderer.setChartValuesTextSize(30);//数值的大小
        seriesRenderer.setPointStyle(PointStyle.CIRCLE);//曲线点的形状
        seriesRenderer.setLineWidth(3f);
        seriesRenderer.setFillBelowLine(false);//是否曲线下涂色
        multipleSeriesRenderer.addSeriesRenderer(seriesRenderer);
    }

    //更新曲线，只能在主线程上运行
    public void updataChart(double x,double y){
        series.add(x,y);
        mGraphcalView.repaint();
    }

    public void updataChart(List<Double> xList,List<Double> yList){
        for (int i = 0; i < xList.size(); i++) {
            series.add(xList.get(i), yList.get(i));
        }
        mGraphcalView.repaint();//此处也可以调用invalidate()
    }

    public void addXTextLabel(int x,String text){
        multipleSeriesRenderer.addXTextLabel(x,text);
    }
}
