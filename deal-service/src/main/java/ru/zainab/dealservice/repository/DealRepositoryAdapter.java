package ru.zainab.dealservice.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import ru.zainab.dealservice.dto.DealFilterRequest;
import ru.zainab.dealservice.entity.Deal;
import ru.zainab.dealservice.specification.DealSpecification;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DealRepositoryAdapter implements DealRepository {
    private final DealJpaRepository repository;

    @Override
    public List<Deal> findAll() {
        return repository.findAll();
    }

    @Override
    public Deal save(Deal deal) {
        return repository.save(deal);
    }

    @Override
    public Optional<Deal> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Deal deal) {
        repository.delete(deal);
    }

    @Override
    public List<Deal> findAllWithCustomerByGraph() {
        return repository.findAllWithCustomerByGraph();
    }


    @Override
    public Page<Deal> findAll(DealFilterRequest filterRequest, Pageable pageable) {
        Specification<Deal> specification = DealSpecification.getSpecification(filterRequest);
        return repository.findAll(specification, pageable);
    }
}
