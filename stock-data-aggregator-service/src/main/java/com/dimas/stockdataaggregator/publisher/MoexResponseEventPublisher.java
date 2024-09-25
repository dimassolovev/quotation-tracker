package com.dimas.stockdataaggregator.publisher;

import com.dimas.stockdataaggregator.event.MoexResponseEvent;
import com.dimas.stockdataaggregator.model.external.MoexData;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MoexResponseEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public void publishMoexResponseEvent(List<MoexData> moexDataList) {
        eventPublisher.publishEvent(new MoexResponseEvent(this, moexDataList));
    }
}
