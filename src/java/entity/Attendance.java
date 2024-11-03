package entity;

import java.sql.Date;

public class Attendance {
    private int id;
    private ScheduleEmployee scheduleEmployee;
    private Date attendanceDate;
    private boolean present;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ScheduleEmployee getScheduleEmployee() {
        return scheduleEmployee;
    }

    public void setScheduleEmployee(ScheduleEmployee scheduleEmployee) {
        this.scheduleEmployee = scheduleEmployee;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }
}
