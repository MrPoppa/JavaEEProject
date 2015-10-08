package com.rappandpoppa.controllers;

import com.rappandpoppa.entities.Attendancelist;
import com.rappandpoppa.entities.Course;
import java.io.Serializable;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Anders
 */
@ManagedBean
@ViewScoped
public class PeriodStatisticsController extends StatisticsController implements Serializable {

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

    public void loadAttendanceRates() {
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
