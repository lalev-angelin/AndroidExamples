package bg.uni_svishtov.bi2016.retrofitdemo;

import java.util.Date;

public class Mark {
    private String uuid;
    private int semester;
    private String lecturer1;
    private String lecturer2;
    private String subject;
    private String mark;
    private Date date;

    public Mark(String uuid, int semester, String lecturer1, String lecturer2, String subject, String mark, Date date) {
        this.uuid = uuid;
        this.semester = semester;
        this.lecturer1 = lecturer1;
        this.lecturer2 = lecturer2;
        this.subject = subject;
        this.mark = mark;
        this.date = date;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getLecturer1() {
        return lecturer1;
    }

    public void setLecturer1(String lecturer1) {
        this.lecturer1 = lecturer1;
    }

    public String getLecturer2() {
        return lecturer2;
    }

    public void setLecturer2(String lecturer2) {
        this.lecturer2 = lecturer2;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
