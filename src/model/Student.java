package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class Student{

    private Long id;
    private LocalDateTime dateCreated;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dob;
    private Group group;
}
