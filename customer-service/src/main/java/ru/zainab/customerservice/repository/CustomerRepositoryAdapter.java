package ru.zainab.customerservice.repository;

import ru.zainab.customerservice.dto.CustomerFilterRequest;
import ru.zainab.customerservice.entity.Customer;
import ru.zainab.customerservice.specification.CustomerSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryAdapter
        implements CustomerRepository {

    private final CustomerJpaRepository repository;

    @Override
    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Customer customer) {
        repository.delete(customer);
    }

    @Override
    public Page<Customer> findAll(
            CustomerFilterRequest filter,
            Pageable pageable
    ) {
        Specification<Customer> specification =
                CustomerSpecification.getSpecification(filter);

        return repository.findAll(specification, pageable);
    }
}
