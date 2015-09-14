package com.rappandpoppa.archive;

import com.rappandpoppa.model.AttendanceListMB;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Benjamin
 */
public class AttendanceListArchive {

    private static final Map<Date, AttendanceListMB> attendanceLists = new HashMap<>();

    public static Map<Date, AttendanceListMB> getAttendanceLists() {
        return attendanceLists;
    }

    public static void addAttendanceList(AttendanceListMB attendanceList) {
        if (attendanceLists.containsValue(attendanceList)) {
            attendanceLists.remove(attendanceList.getDate());
        }
        attendanceLists.put(attendanceList.getDate(), attendanceList);
    }

    public static void removeAttendanceList(AttendanceListMB attendanceList) {
        if (attendanceLists.containsValue(attendanceList)) {
            attendanceLists.remove(attendanceList.getDate());
        }
    }
}
