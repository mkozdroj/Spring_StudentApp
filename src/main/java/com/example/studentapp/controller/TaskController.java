package com.example.studentapp.controller;


import com.example.studentapp.model.TaskModel;
import com.example.studentapp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class TaskController {
private final TaskService taskService;

    @GetMapping("/tasks")
    public String getTaskList() {
        return "tasks/tasks";
    }


    @GetMapping("/addTask")
    public String getAddTask() {
        return "tasks/addTask";
    }



    @PostMapping("/addTask")
    public RedirectView postAddTask(TaskModel task) {
        taskService.addTask(task);
        return new RedirectView("/tasks");
    }

}
