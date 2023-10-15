package com.example.service;




import com.example.exception.AlertException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ExportService
 * Describes CSV Export Service contract
 *
 * @author Binyamin (Dima) Pyanin
 * @version POC
 * @since April 24,2023
 */
public interface ExportService {

    void exportToCsv(HttpServletResponse response) throws IOException, AlertException;

}

