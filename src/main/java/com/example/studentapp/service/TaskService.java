package com.example.studentapp.service;

import com.example.studentapp.model.StudentModel;
import com.example.studentapp.model.TaskModel;

import com.example.studentapp.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {

    private final TaskRepository taskRepository;

    public void addTask(TaskModel task) {
        task.setCreationDate(new Date());
        log.info("Zapisuje taska: {}" , task);
        taskRepository.save(task);

    }

    public List<TaskModel> getTaskList(){
        return taskRepository.findAll();
    }

    public TaskModel getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public void saveEditTask(TaskModel editTask) {

        editTask.setCreationDate(getTaskById(editTask.getId()).getCreationDate());
       // editTask.setCreationDate(editTask.getCreationDate());
        System.out.println("SOUT: " +editTask.getCreationDate());
        if (editTask.getStudentModel() == getTaskById(editTask.getId()).getStudentModel()){
            editTask.setStudentModel( getTaskById(editTask.getId()).getStudentModel());
        }
        taskRepository.save(editTask);
    }

    public void delTask(Long id) {
        taskRepository.deleteById(id);
    }

}
