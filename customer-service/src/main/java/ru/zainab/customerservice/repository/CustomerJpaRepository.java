package ru.zainab.customerservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ru.zainab.customerservice.entity.Customer;


public interface CustomerJpaRepository extends JpaRepository<Customer, Long>,
        JpaSpecificationExecutor<Customer> {

    @Query("""
            select c.id
            from Customer c
            order by c.id
            """)
    Page<Long> findCustomerIds(Pageable pageable);


    // встроен в JpaSpecificationExecutor(не нужно писать)
    Page<Customer> findAll(
            Specification<Customer> spec,
            Pageable pageable
    );
}
