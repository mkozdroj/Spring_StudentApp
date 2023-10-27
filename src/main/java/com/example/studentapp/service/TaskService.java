package com.example.studentapp.service;

import com.example.studentapp.model.StudentModel;
import com.example.studentapp.model.TaskModel;

import com.example.studentapp.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public void addTask(TaskModel task) {
        taskRepository.save(task);

    }

    public List<TaskModel> getTaskList(){
        return taskRepository.findAll();
    }

    public void saveEditTask(TaskModel editTask) {
        taskRepository.save(editTask);
    }

    public void delTask(Long id) {
        taskRepository.deleteById(id);
    }

}
