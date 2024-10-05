package com.dimas.stockdataaggregator.event;

import com.dimas.stockdataaggregator.model.external.MoexData;

import lombok.Getter;

import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
public class MoexResponseEvent extends ApplicationEvent {
    private final List<MoexData> moexDataList;

    public MoexResponseEvent(Object source, List<MoexData> moexDataList) {
        super(source);
        this.moexDataList = moexDataList;
    }
}
