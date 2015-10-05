package com.rappandpoppa.controllers;

import com.rappandpoppa.entities.Attendancelist;
import com.rappandpoppa.entities.Course;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
    private List<Date> courseDates;
    private Course course;
    private double attendedStudentCount;
    private double courseStudentCount;
    private double totalAttendanceRate;
    private double rateAsDouble;

    public LineChartModel getLineModel() {
//        init();
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

    public Map<Date, Integer> getAttendanceRateByDate() {
        return attendanceRateByDate;
    }

    public void setAttendanceRateByDate(Map<Date, Integer> attendanceRateByDate) {
        this.attendanceRateByDate = attendanceRateByDate;
    }

    public List<Date> getCourseDates() {
        return courseDates;
    }

    public void setCourseDates(List<Date> courseDates) {
        this.courseDates = courseDates;
    }

    public double getTotalAttendanceRate() {
        return totalAttendanceRate;
    }

    public void setTotalAttendanceRate(double totalAttendanceRate) {
        this.totalAttendanceRate = totalAttendanceRate;
    }
//
////    @PostConstruct
//    public void init() {
//        createLineModels();
//    }
//
//    public void createLineModels() {
//        loadAllAttendanceLists();
//        loadAttendanceRates();
//
//        lineModel = initCategoryModel();
//        String genericTitle = "Course attendance between ";
//        lineModel.setTitle(genericTitle + convertDateToLocalDate(periodStartDate) + " and " + convertDateToLocalDate(periodEndDate));
//        lineModel.setLegendPosition("e");
//        lineModel.setShowPointLabels(true);
//        DateAxis xAxis = new DateAxis("Date");
//
//        xAxis.setMin(convertDateToLocalDate(periodStartDate).toString());
//        xAxis.setMax(convertDateToLocalDate(periodEndDate).toString());
//        xAxis.setTickAngle(-90);
//        xAxis.setTickCount(lineModel.getLegendCols());
//        lineModel.getAxes().put(AxisType.X, xAxis);
//        Axis yAxis = lineModel.getAxis(AxisType.Y);
//        yAxis.setLabel("%");
//        yAxis.setMin(0);
//        yAxis.setMax(100);
//        yAxis.setTickCount(11);
//    }
//
//    private LineChartModel initCategoryModel() {
//        LineChartModel model = new LineChartModel();
//
//        for (Course course : attendanceRatesByCourseAndDate.keySet()) {
//            ChartSeries chartSeries = new ChartSeries();
//            String courseName = course.getCourseName();
//            chartSeries.setLabel(courseName);
//            attendanceRateByDate = attendanceRatesByCourseAndDate.get(course);
//            for (Date date : attendanceRateByDate.keySet()) {
//                chartSeries.set(convertDateToLocalDate(date), attendanceRateByDate.get(date));
//            }
//            model.addSeries(chartSeries);
//        }
//        return model;
//    }

    private void loadAllAttendanceLists() {
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

//    public void onCourseChange() {
//        loadAttendanceListByCourse();
//        loadAttendanceRates2();
//    }
    private void loadAttendanceListByCourse() {
        if (periodStartDate != null && periodEndDate != null) {
            Period p = Period.between(convertDateToLocalDate(periodStartDate), convertDateToLocalDate(periodEndDate));
            if (!p.isNegative()) {
                attendancelistsInPeriod = attendancelistFacade.findCoursePeriod(courseMB.getId(), periodStartDate, periodEndDate);
            }
        } else {
            attendancelistsInPeriod = courseFacade.find(courseMB.getId()).getAttendancelistList();
        }
    }

    public void loadAttendanceRates2() {
        loadAttendanceListByCourse();
        attendanceRateByDate = new TreeMap<>();
        courseStudentCount = 0;
        attendedStudentCount = 0;
        for (Attendancelist attendancelist : attendancelistsInPeriod) {
            Date attendedDate = attendancelist.getAttendanceDate();
            attendanceRateByDate.put(attendedDate, calculateAttendance(attendancelist));
            courseStudentCount += attendancelist.getCourse().getStudentList().size();
            courseDates = new ArrayList<>(attendanceRateByDate.keySet());
            periodStartDate = courseDates.get(0);
            periodEndDate = courseDates.get(courseDates.size() - 1);
        }
        setTotalAttendanceRate();
    }

    public void setTotalAttendanceRate() {
        totalAttendanceRate = 0;
        rateAsDouble = attendedStudentCount / courseStudentCount;
        totalAttendanceRate = (rateAsDouble * 100);
    }

    private void loadAttendanceRates() {
        for (Attendancelist attendancelist : attendancelistsInPeriod) {
            attendanceRateByDate = new TreeMap<>();
            course = attendancelist.getCourse();
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

    private int calculateAttendance(Attendancelist attendancelist) {
        double some;
        double all;
        some = attendancelist.getStudentList().size();
        attendedStudentCount += some;
        all = attendancelist.getCourse().getStudentList().size();
        int rate = (int) ((some / all) * 100);
        return rate;
    }
}
