package com.example.studentapp.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.util.Date;



@Entity
@Data
@ToString(exclude = "studentModel")
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "description")
    private String description;

    @Column(name = "color")
    private String color;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDate;

    @ManyToOne
    private StudentModel studentModel;

}


