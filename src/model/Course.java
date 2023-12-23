package model;

import java.time.LocalDateTime;

public final class Course{

    private Long id;
    private  LocalDateTime dateCreated;
    private String courseName;
    private int durationCourse;
    private String typeCourse;
    private double priceCourse;

    public Course() {
        dateCreated = LocalDateTime.now();
    }

    public Course(Long id, String courseName, int durationCourse, String typeCourse, double priceCourse) {
        this.id = id;
        dateCreated = LocalDateTime.now();
        this.courseName = courseName;
        this.durationCourse = durationCourse;
        this.typeCourse = typeCourse;
        this.priceCourse = priceCourse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getDurationCourse() {
        return durationCourse;
    }

    public void setDurationCourse(int durationCourse) {
        this.durationCourse = durationCourse;
    }

    public String getTypeCourse() {
        return typeCourse;
    }

    public void setTypeCourse(String typeCourse) {
        this.typeCourse = typeCourse;
    }

    public double getPriceCourse() {
        return priceCourse;
    }

    public void setPriceCourse(double priceCourse) {
        this.priceCourse = priceCourse;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", dateCreated=" + dateCreated +
                ", courseName='" + courseName + '\'' +
                ", durationCourse=" + durationCourse +
                ", typeCourse='" + typeCourse + '\'' +
                ", priceCourse=" + priceCourse +
                '}';
    }
}
