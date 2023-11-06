package com.example.rest;

import com.google.gson.Gson;
import com.example.dto.responce.AlertCategoryResponse;
//import io.swagger.v3.oas.annotations.Timed;
//import io.micrometer.core.annotation.Timed;

import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.service.AlertService;

/**
 * AlertController
 * Describes Alert API EPs related operations.
 * A
 * {@link AlertCategoryResponse},
 * <p>
 * REST controller.
 *
 * @author Binyamin (Dima) Pyanin
 * @version POC
 * @since April 24,2023
 */
@Slf4j
@RestController
@RequestMapping(path = "v1/alert")
@ConditionalOnProperty(value = "alert.endpoint.enabled", havingValue = "false")
@Tag(name = "Alert API Operations", description = "Operations related to Alerts.")
public class AlertController {

    @Autowired
    private AlertService alertService;

    //@Timed(value = "alert.get.by.id.time", description = "Time taken to fetch alert by id")
    //@Operation(summary = "Endpoint To Fetch Alert By Id", description = "Fetches Alert By Id From DB.")
    //@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = AlertCategoryResponse.class)))
    //@GetMapping(path = "/{alertId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(path = "/{alertId}")

    public ResponseEntity<String> getAlertById(@PathVariable("alertId") Integer alertId) {

        System.out.print("GEORGY IMPL ALERT******************************");
        return ResponseEntity.
                status(HttpStatus.OK).
                body(new Gson().toJson(
                        this.alertService.getAlertById(alertId))
                );
    }

    //@Timed(value = "alert.get.all.time", description = "Time taken to fetch all alerts")
    //@Operation(summary = "Endpoint To Fetch All Alerts", description = "Fetches All Alerts From DB.")
    //@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = AlertCategoryResponse.class)))
    //@GetMapping(path = "/alert", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(path = "/alert")
    public ResponseEntity<String> getAllAlerts() {
        System.out.print("GEORGY IMPL ALERT******************************");
        return ResponseEntity.
                status(HttpStatus.OK).
                body(new Gson().toJson(
                        this.alertService.getAllAlerts())
                );
    }

}
