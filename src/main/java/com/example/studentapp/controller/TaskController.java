package com.example.studentapp.controller;


import com.example.studentapp.model.StudentModel;
import com.example.studentapp.model.TaskModel;
import com.example.studentapp.service.StudentService;
import com.example.studentapp.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.List;

@Controller
@Slf4j

public class TaskController {
    private final TaskService taskService;
    private final StudentService studentService;

    public TaskController(TaskService taskService, StudentService studentService) {
        this.taskService = taskService;
        this.studentService = studentService;
    }


    @GetMapping("/tasks")
    public String getTaskList(Model model) {
        List<TaskModel> taskList = taskService.getTaskList();
        model.addAttribute("taskModel", taskList);
        return "tasks/tasks";
    }


    @GetMapping("/addTask")
    public String getAddTask(Model model) {
        List<StudentModel> studentList = studentService.getStudentList();
        model.addAttribute("studentModel", studentList);
        return "tasks/addTask";
    }


    @PostMapping("/addTask")
    public RedirectView postAddTask(TaskModel task) {
        taskService.addTask(task);
        return new RedirectView("/tasks");
    }

    @GetMapping("/editTask/{id}")
    public String getEditTask(@PathVariable("id") Long id, Model studentModel, Model model) {
        List<StudentModel> studentList = studentService.getStudentList();
        studentModel.addAttribute("studentModel", studentList);

        TaskModel taskModel = taskService.getTaskById(id);
        model.addAttribute("task", taskModel);

        return "tasks/editTask";
    }

    @PostMapping("/editTask/{id}")
    public RedirectView postEditTask(@PathVariable("id") Long id, TaskModel editTask, Model model) {
        List<StudentModel> studentList = studentService.getStudentList();
        model.addAttribute("studentModel", studentList);
        taskService.saveEditTask(editTask);
        return new RedirectView("/editTask/{id}");
    }

    @PostMapping("/delTask/{id}")
    public RedirectView delTask(@PathVariable("id") Long id) {
        taskService.delTask(id);
        return new RedirectView("/tasks");
    }


}
