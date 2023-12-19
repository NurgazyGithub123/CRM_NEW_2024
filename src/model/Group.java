package model;

import java.time.LocalDateTime;
import java.time.LocalTime;
public final class Group{

    private Long id;
    private LocalDateTime dateCreated;
    private String name;
    private LocalTime groupTime;
    private Student[] students;
    private Course course;
    private Mentor mentor;

}
