package ru.zainab.taskservice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.zainab.taskservice.entity.Task;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class TaskRepositoryAdapter implements TaskRepository {
    private final TaskJpaRepository repository;

    @Override
    public List<Task> findAll() {
        return repository.findAll();
    }

    @Override
    public Task save(Task task) {
        return repository.save(task);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Task task) {
        repository.delete(task);
    }

    @Override
    public List<Task> findAllWithTaskGraph() {
        return repository.findAllWithTaskGraph();
    }


}
