package com.dimas.stockdataaggregator.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quotation")
@RequiredArgsConstructor
public class QuotationController {

    @GetMapping("/test")
    public ResponseEntity<Void> test() {
        return ResponseEntity.ok().build(); // saga pattern will be implemented here soon
    }
}
