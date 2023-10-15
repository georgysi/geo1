package com.example.rest;

import com.example.dto.responce.AlertCategoryResponse;
import com.example.exception.AlertException;
import com.example.service.ExportService;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ExportController
 * Describes CSV Export API EPs related operations.
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
@RequestMapping(path = "v1/export")
//@ConditionalOnProperty(value = "export.endpoint.enabled", havingValue = "false")
@Tag(name = "CSV Export API Operations", description = "Operations related to CSV Export.")
public class ExportController {

    @Autowired
    private ExportService exportService;

    @Timed(value = "alert.export.time", description = "Time taken to export alerts")
    @Operation(summary = "Endpoint To Export All Alerts To CSV file", description = "Fetches All Alerts From DB And Export To CSV File.")
    @ApiResponse(responseCode = "200")
    @GetMapping(path = "/export")
    public void exportAlertsToCsv(HttpServletResponse response) throws IOException, AlertException {

        this.exportService.exportToCsv(response);

    }

}
