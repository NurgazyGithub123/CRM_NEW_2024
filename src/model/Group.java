package model;

import java.time.LocalDateTime;
import java.time.LocalTime;
public final class Group{

    private Long id;
    private LocalDateTime dateCreated;
    private String name;
    private int groupTime;
    private Course course;
    //private Mentor mentor;

    public Group() {
        this.dateCreated = LocalDateTime.now();
    }

    public Group(Long id, String name, int groupTime, Course course) {
        this.id = id;
        this.dateCreated = LocalDateTime.now();
        this.name = name;
        this.groupTime = groupTime;
        this.course = course;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroupTime() {
        return groupTime;
    }

    public void setGroupTime(int groupTime) {
        this.groupTime = groupTime;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", dateCreated=" + dateCreated +
                ", name='" + name + '\'' +
                ", groupTime=" + groupTime +
                ", course=" + course +
                '}';
    }
}
