package com.rappandpoppa.controllers;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author Anders
 */
@ManagedBean
public class PeriodStatisticsController extends StatisticsController {

    private LineChartModel lineModel;
    private Date periodStartDate;
    private Date periodEndDate;

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    }

    public Date getPeriodStartDate() {
        return periodStartDate;
    }

    public void setPeriodStartDate(Date periodStartDate) {
        this.periodStartDate = periodStartDate;
    }

    public Date getPeriodEndDate() {
        return periodEndDate;
    }

    public void setPeriodEndDate(Date periodEndDate) {
        this.periodEndDate = periodEndDate;
    }

    @PostConstruct
    public void init() {
        createLineModels();
    }

    private void createLineModels() {
        lineModel = initCategoryModel();
        lineModel.setTitle("Category chart");
        lineModel.setLegendPosition("e");
        lineModel.setShowPointLabels(true);
        lineModel.getAxes().put(AxisType.X, new CategoryAxis("Years"));
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setLabel("Births");
        yAxis.setMin(0);
        yAxis.setMax(300);
    }

    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();
        ChartSeries male = new ChartSeries();
        male.setLabel("Male");
        male.set("2004", 120);
        male.set("2005", 100);
        male.set("2006", 44);
        male.set("2007", 150);
        male.set("2008", 25);

        ChartSeries female = new ChartSeries();
        female.setLabel("Female");
        female.set("2004", 52);
        female.set("2005", 60);
        female.set("2006", 110);
        female.set("2007", 90);
        female.set("2008", 120);

        ChartSeries overall = new ChartSeries();
        overall.setLabel("Overall");
        overall.set("2004", 172);
        overall.set("2005", 160);
        overall.set("2006", 154);
        overall.set("2007", 240);
        overall.set("2008", 145);

        model.addSeries(male);
        model.addSeries(female);
        model.addSeries(overall);

        return model;
    }

}
