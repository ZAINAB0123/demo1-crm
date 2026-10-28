package ru.zainab.customerservice.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import ru.zainab.customerservice.dto.CustomerFilterRequest;
import ru.zainab.customerservice.entity.Customer;


public class CustomerSpecification {
    public static Specification<Customer> getSpecification(
            CustomerFilterRequest request) {
        return (root, query, cb) -> {

            query.distinct(true);
            Predicate predicate = cb.conjunction();

            // фильтр по имени Customer
            if (StringUtils.hasText(request.name())) {
                predicate = cb.and(
                        predicate,
                        cb.like(cb.lower(
                                        root.get("name")),
                                "%" + request.name().toLowerCase() + "%"
                        )
                );
            }
            // фильтр по статусу Customer
            if (request.status() != null) {
                predicate = cb.and(
                        predicate,
                        cb.equal(
                                root.get("status"),
                                request.status()
                        )
                );
            }

            return predicate;
        };
    }
}




