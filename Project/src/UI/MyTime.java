package UI;

/**
 *
 * @author Salma
 */
public class MyTime {
    //this class is for time (hours,minutes,seconds)

    long hour, min, sec;

    public MyTime(long h, long m, long s) {
        this.hour = h;
        this.min = m;
        this.sec = s;
    }

    public MyTime() {
    }

    public long getHour() {
        return hour;
    }

    public void setHour(long hour) {
        this.hour = hour;
    }

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public long getSec() {
        return sec;
    }

    public void setSec(long sec) {
        this.sec = sec;
    }

}
