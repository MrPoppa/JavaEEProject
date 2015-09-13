package com.rappandpoppa.archive;

import com.rappandpoppa.model.AttendanceList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Benjamin
 */
public class AttendanceListArchive {

    private static final Map<Date, AttendanceList> attendanceLists = new HashMap<>();

    public static Map<Date, AttendanceList> getAttendanceLists() {
        return attendanceLists;
    }

    public static void addAttendanceList(AttendanceList attendanceList) {
        if (attendanceLists.containsValue(attendanceList)) {
            attendanceLists.remove(attendanceList.getDate());
        }
        attendanceLists.put(attendanceList.getDate(), attendanceList);
    }

    public static void removeAttendanceList(AttendanceList attendanceList) {
        if (attendanceLists.containsValue(attendanceList)) {
            attendanceLists.remove(attendanceList.getDate());
        }
    }
}
