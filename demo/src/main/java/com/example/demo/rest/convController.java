package com.example.demo.rest;

import com.example.demo.service.convService;
import lombok.extern.slf4j.Slf4j;
//import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.commons.io.FileUtils;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

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
