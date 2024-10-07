package com.dimas.stockdataaggregator.event;

import com.dimas.stockdataaggregator.model.external.DataFromExternalServices;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class MoexResponseEvent extends ApplicationEvent {
    private final DataFromExternalServices dataFromExternalServices;

    public MoexResponseEvent(Object source, DataFromExternalServices dataFromExternalServices) {
        super(source);
        this.dataFromExternalServices = dataFromExternalServices;
    }
}
