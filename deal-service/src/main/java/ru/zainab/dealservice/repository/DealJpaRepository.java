package ru.zainab.dealservice.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ru.zainab.dealservice.entity.Deal;

import java.util.List;

public interface DealJpaRepository extends JpaRepository<Deal, Long>, JpaSpecificationExecutor<Deal> {

    @EntityGraph(attributePaths = {"customer"})
    @Query("select d from Deal  d")
    List<Deal> findAllWithCustomerByGraph();


}
