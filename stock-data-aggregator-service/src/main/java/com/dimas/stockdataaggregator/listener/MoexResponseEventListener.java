package com.dimas.stockdataaggregator.listener;

import com.dimas.stockdataaggregator.event.MoexResponseEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MoexResponseEventListener {
    @EventListener
    public void handleMoexResponse(MoexResponseEvent event) {
        // some actions... Kafka maybe
        System.out.println(event.getMoexDataList());
    }
}
