package ru.zainab.taskservice.repository;

import ru.zainab.taskservice.entity.Task;

import java.util.List;
import java.util.Optional;


public interface TaskRepository {
    List<Task> findAll();

    Task save(Task task);

    Optional<Task> findById(Long id);

    void delete(Task task);

    List<Task> findAllWithTaskGraph();


}
