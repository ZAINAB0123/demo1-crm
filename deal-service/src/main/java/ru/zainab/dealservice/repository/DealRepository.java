package ru.zainab.dealservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.zainab.dealservice.dto.DealFilterRequest;
import ru.zainab.dealservice.entity.Deal;

import java.util.List;
import java.util.Optional;

public interface DealRepository {
    List<Deal> findAll();

    Deal save(Deal deal);

    Optional<Deal> findById(Long id);

    void delete(Deal deal);

    List<Deal> findAllWithCustomerByGraph();

    Page<Deal> findAll(DealFilterRequest filterRequest, Pageable pageable);

}
