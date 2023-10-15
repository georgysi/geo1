package com.example.demo.rest;

import com.example.demo.service.convService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class convController {


    @Autowired
    private convService ConvService;
    @GetMapping(path = "/conv")

    public String getCSV() {
        System.out.print("GEORGY IMPL ***********************************");
        String kva = this.ConvService.getCSV();
        return kva;
    }

}
