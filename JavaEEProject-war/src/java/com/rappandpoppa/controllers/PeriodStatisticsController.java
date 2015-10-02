package com.rappandpoppa.controllers;

import com.rappandpoppa.entities.Attendancelist;
import com.rappandpoppa.entities.Course;
import com.rappandpoppa.entities.Student;
import java.math.BigDecimal;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
@ViewScoped
public class PeriodStatisticsController extends StatisticsController {

    private LineChartModel lineModel;
    private List<Attendancelist> attendanceLists = new ArrayList<>();
    private List<Date> attendanceListsDates = new ArrayList<>();
    private List<Attendancelist> attendancelistsInPeriod = new ArrayList<>();
    private Date periodStartDate;
    private Date periodEndDate;
    private Map<Course, Map<Date, Integer>> attendanceRatesByCourseAndDate = new HashMap<>();
    private Set<Course> mapKeys = new HashSet<>();
    private Map<Date, Integer> attendancebyDate = new HashMap<>();

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
        Date today = new Date(System.currentTimeMillis());

        attendanceLists = attendancelistFacade.findAll();
        if (!attendanceListsDates.isEmpty()) {
            attendanceListsDates.clear();
        }

        if (!attendanceLists.isEmpty()) {
            for (Attendancelist attendancelist : attendanceLists) {
                attendanceListsDates.add(attendancelist.getAttendanceDate());
            }
            Collections.sort(attendanceListsDates);
        }

        if (periodStartDate == null && !attendanceListsDates.isEmpty()) {
            periodStartDate = attendanceListsDates.get(0);
        }
        if (periodStartDate.getTime() > today.getTime()) {
//            periodStartDate = today.getTime() - 
        }

        if (periodEndDate == null && !attendanceListsDates.isEmpty()) {
            periodEndDate = attendanceListsDates.get(attendanceListsDates.size() - 1);
        }
        Period p = Period.between(convertDateToLocalDate(periodStartDate), convertDateToLocalDate(periodEndDate));
        if (!p.isNegative()) {
            attendancelistsInPeriod = attendancelistFacade.findPeriod(periodStartDate, periodEndDate);
        }

        lineModel = initCategoryModel();
        String genericTitle = "Course attendance between ";
        String and = " and ";
        lineModel.setTitle(genericTitle + convertDateToLocalDate(periodStartDate) + and + convertDateToLocalDate(periodEndDate));
        lineModel.setLegendPosition("e");
        lineModel.setShowPointLabels(true);
        lineModel.getAxes().put(AxisType.X, new CategoryAxis("Month"));
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setLabel("%");
        yAxis.setMin(0);
        yAxis.setMax(100);
    }

    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();
        setMapKeys();
        ChartSeries chartSeries = new ChartSeries();
        
        for (Attendancelist attendancelist : attendancelistsInPeriod) {
            attendancebyDate.put(attendancelist.getAttendanceDate(), calculateAttendance(attendancelist));
        }
        
        for (Course course : mapKeys) {
            attendanceRatesByCourseAndDate.put(course, attendancebyDate);
            chartSeries.setLabel(course.getCourseName());
        }

        for (Date date : attendancebyDate.keySet()) {
            chartSeries.set(convertDateToLocalDate(date), attendancebyDate.get(date));
        }
        model.addSeries(chartSeries);

        return model;
    }

    public void setMapKeys() {
        for (Attendancelist attendancelist : attendancelistsInPeriod) {
            mapKeys.add(attendancelist.getCourse());
        }
    }

    public int calculateAttendance(Attendancelist attendancelist) {
//        List<Student> attendendedCourseStudents = attendancelist.getStudentList();
        return (attendancelist.getStudentList().size() * 100) / (attendancelist.getCourse().getStudentList().size() * 100);
    }

}

//ChartSeries male = new ChartSeries();
//        male.setLabel("Male");
//        male.set("2004", 120);
//        male.set("2005", 100);
//        male.set("2006", 44);
//        male.set("2007", 150);
//        male.set("2008", 25);
//
//        ChartSeries female = new ChartSeries();
//        female.setLabel("Female");
//        female.set("2004", 52);
//        female.set("2005", 60);
//        female.set("2006", 110);
//        female.set("2007", 90);
//        female.set("2008", 120);
//
//        ChartSeries overall = new ChartSeries();
//        overall.setLabel("Overall");
//        overall.set("2004", 172);
//        overall.set("2005", 160);
//        overall.set("2006", 154);
//        overall.set("2007", 240);
//        overall.set("2008", 145);
//
//        model.addSeries(male);
//        model.addSeries(female);
//        model.addSeries(overall);
