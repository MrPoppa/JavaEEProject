package com.rappandpoppa.controllers;

import com.rappandpoppa.entities.Attendancelist;
import com.rappandpoppa.entities.Course;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author Anders
 */
@ManagedBean
@ViewScoped
public class PeriodStatisticsController extends StatisticsController {

    private LineChartModel lineModel;
    private List<Attendancelist> allAttendanceLists = new ArrayList<>();
    private List<Attendancelist> attendancelistsInPeriod = new ArrayList<>();
    private Date periodStartDate;
    private Date periodEndDate;
    private Map<Course, Map<Date, Integer>> attendanceRatesByCourseAndDate = new HashMap<>();
    private Map<Date, Integer> attendanceRateByDate;

    public LineChartModel getLineModel() {
        init();
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

    public void createLineModels() {
        loadAttendanceLists();
        loadAttendanceRates();

        lineModel = initCategoryModel();
        String genericTitle = "Course attendance between ";
        lineModel.setTitle(genericTitle + convertDateToLocalDate(periodStartDate) + " and " + convertDateToLocalDate(periodEndDate));
        lineModel.setLegendPosition("e");
        lineModel.setShowPointLabels(true);
        DateAxis xAxis = new DateAxis("Date");

        xAxis.setMin(convertDateToLocalDate(periodStartDate).toString());
        xAxis.setMax(convertDateToLocalDate(periodEndDate).toString());
        xAxis.setTickAngle(-90);
        xAxis.setTickCount(lineModel.getLegendCols());
        lineModel.getAxes().put(AxisType.X, xAxis);
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setLabel("%");
        yAxis.setMin(0);
        yAxis.setMax(100);
        yAxis.setTickCount(11);
    }

    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();

        for (Course course : attendanceRatesByCourseAndDate.keySet()) {
            ChartSeries chartSeries = new ChartSeries();
            String courseName = course.getCourseName();
            chartSeries.setLabel(courseName);
            attendanceRateByDate = attendanceRatesByCourseAndDate.get(course);
            for (Date date : attendanceRateByDate.keySet()) {
                chartSeries.set(convertDateToLocalDate(date), attendanceRateByDate.get(date));
            }
            model.addSeries(chartSeries);
        }
        return model;
    }

    private int calculateAttendance(Attendancelist attendancelist) {
        double some = attendancelist.getStudentList().size();
        double all = attendancelist.getCourse().getStudentList().size();
        int rate = (int) (some / all) * 100;
        return rate;
    }

    private void loadAttendanceRates() {
        for (Attendancelist attendancelist : attendancelistsInPeriod) {
            attendanceRateByDate = new HashMap<>();
            Course course = attendancelist.getCourse();
            Date attendedDate = attendancelist.getAttendanceDate();
            if (attendanceRatesByCourseAndDate.containsKey(course)) {
                attendanceRateByDate = attendanceRatesByCourseAndDate.get(course);
                attendanceRateByDate.put(attendedDate, calculateAttendance(attendancelist));
                attendanceRatesByCourseAndDate.put(course, attendanceRateByDate);
            } else {
                attendanceRateByDate.put(attendedDate, calculateAttendance(attendancelist));
                attendanceRatesByCourseAndDate.put(course, attendanceRateByDate);
            }
        }
    }

    private void loadAttendanceLists() {
        if (periodStartDate != null && periodEndDate != null) {
            Period p = Period.between(convertDateToLocalDate(periodStartDate), convertDateToLocalDate(periodEndDate));
            if (!p.isNegative()) {
                attendancelistsInPeriod = attendancelistFacade.findPeriod(periodStartDate, periodEndDate);
            } else {
                attendancelistsInPeriod = attendancelistFacade.findAll();
            }
        } else {
            attendancelistsInPeriod = attendancelistFacade.findAll();
            periodStartDate = attendancelistsInPeriod.get(0).getAttendanceDate();
            periodEndDate = attendancelistsInPeriod.get(attendancelistsInPeriod.size() - 1).getAttendanceDate();
        }
    }
}
