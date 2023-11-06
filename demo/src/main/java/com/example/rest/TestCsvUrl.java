package com.example.rest;


import com.example.util.AlertConstants;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "v2/test")
public class TestCsvUrl {


    @Hidden
    //@PostMapping(path = "/subscriber", produces = MediaType.APPLICATION_JSON_VALUE)
   /* public ResponseEntity<String> getNotified(@RequestBody String message,
                                              @Valid @RequestHeader(AlertConstants.HEADER_TRACEABILITY_ID) String traceabilityId) {

        log.error("Subscriber Received Message : {} Reference traceabilityId {}", message, traceabilityId);

        return ResponseEntity.
                status(HttpStatus.OK).
                body("Ack Received message : " + message);
    }*/
    @PostMapping(path = "/subscriber", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> testfun(@RequestBody String message){
        log.error("Subscriber Received Message :" +  message + " Reference traceabilityId {}");

        return ResponseEntity.
                status(HttpStatus.OK).
                body("Ack Received message : " + message);
    }

}
