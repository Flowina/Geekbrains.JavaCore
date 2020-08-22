package Lesson1;

public enum DayOfWeek {
    MONDAY(40, true),
    TUESDAY(32, true),
    WEDNESDAY(24, true),
    THURSDAY(16, true),
    FRIDAY(8, true),
    SATURDAY(0, false),
    SUNDAY(0, false);
    private int hour;
    private boolean workingDay;

    DayOfWeek(int hour, boolean workingDay) {
        this.hour = hour;
        this.workingDay = workingDay;
    }
    public int gotHour(){
        return hour;
    }
    public boolean IsWorkingDay(){
        return workingDay;
    }
}
