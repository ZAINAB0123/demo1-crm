package ru.zainab.taskservice.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.zainab.taskservice.dto.TaskFilterRequest;
import ru.zainab.taskservice.entity.Task;

public class TaskSpecification {
    public static Specification<Task> getSpecification(TaskFilterRequest request) {
        if (request.status() != null) {
            return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("status"), request.status()));
        } else {
            return null;   //Specification.where(null);
        }
    }
}
