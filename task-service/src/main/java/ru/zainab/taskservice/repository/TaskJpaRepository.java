package ru.zainab.taskservice.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ru.zainab.taskservice.entity.Task;

import java.util.List;

public interface TaskJpaRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {

    @EntityGraph(attributePaths = "customer")
    @Query("select t from Task t")
    List<Task> findAllWithTaskGraph();

}
