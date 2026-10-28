package ru.zainab.dealservice.mapper;

import org.springframework.stereotype.Component;
import ru.zainab.dealservice.dto.DealCreateRequest;
import ru.zainab.dealservice.dto.DealResponse;
import ru.zainab.dealservice.entity.Deal;

import java.util.ArrayList;
import java.util.List;

@Component
public class DealMapper {

    public Deal toEntity(DealCreateRequest request) {
        return new Deal(
                request.title(),
                request.description(),
                request.amount()
        );
    }

    public DealResponse toResponse(Deal deal) {
        return new DealResponse(
                deal.getId(),
                deal.getTitle(),
                deal.getDescription(),
                deal.getAmount(),
                deal.getStatus(),
                deal.getCreatedAt(),
                deal.getVersion()
        );
    }

    public List<DealResponse> toResponseList(List<Deal> deals) {
        List<DealResponse> dealList = new ArrayList<>();
        for (Deal deal1 : deals) {
            dealList.add(toResponse(deal1));
        }
        return dealList;
    }

}
