package ru.zainab.dealservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zainab.dealservice.dto.DealCreateRequest;
import ru.zainab.dealservice.dto.DealResponse;
import ru.zainab.dealservice.dto.DealUpdateRequest;
import ru.zainab.dealservice.entity.Deal;
import ru.zainab.dealservice.exception.DealNotFoundException;
import ru.zainab.dealservice.mapper.DealMapper;
import ru.zainab.dealservice.repository.DealRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DealService {
    private final DealRepository dealRepository;
    private final DealMapper dealMapper;

    @Transactional
    public DealResponse createDeal(DealCreateRequest request) {
        Deal deal = dealMapper.toEntity(request);
        Deal savedDeal = dealRepository.save(deal);
        return dealMapper.toResponse(savedDeal);
    }

    @Transactional
    public DealResponse updateDeal(Long id, DealUpdateRequest request) {
        Deal deal = dealRepository.findById(id).orElseThrow(() ->
                new DealNotFoundException("Deal not found"));
        deal.setTitle(request.title());
        deal.setDescription(request.description());
        deal.setAmount(request.amount());
        return dealMapper.toResponse(deal);
    }

    @Transactional(readOnly = true)
    public DealResponse getDeal(Long id) {
        Deal deal = dealRepository.findById(id).orElseThrow(() ->
                new DealNotFoundException("Deal not found"));
        return dealMapper.toResponse(deal);
    }

    @Transactional(readOnly = true)
    public List<DealResponse> findAllDeals() {
        return dealMapper.toResponseList(dealRepository.findAll());
    }

    @Transactional
    public void deleteDeal(Long id) {
        Deal deal = dealRepository.findById(id).orElseThrow(() ->
                new DealNotFoundException("Deal not found"));
        dealRepository.delete(deal);
    }
}
