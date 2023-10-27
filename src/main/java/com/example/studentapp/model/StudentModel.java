package com.example.studentapp.model;



import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class StudentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "git_hub")
    private String gitHub;

    @Column(name = "start")
    private String start;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentModel")
    private List<TaskModel> taskModel = new ArrayList<>();
    //private Set<TaskModel> taskModel = new HashSet<>();


}
