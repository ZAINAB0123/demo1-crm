package ru.zainab.customerservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.zainab.customerservice.dto.CustomerFilterRequest;
import ru.zainab.customerservice.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    // Вариант 1: обычное получение Customer
    List<Customer> findAll();

    Customer save(Customer customer);

    Optional<Customer> findById(Long id);

    void delete(Customer customer);

    Page<Customer> findAll(CustomerFilterRequest filter, Pageable pageable);
}
