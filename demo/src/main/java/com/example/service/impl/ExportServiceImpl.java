package com.example.service.impl;

import com.example.service.NotificationService;
import com.example.dto.request.NotificationRequest;
import com.example.dto.responce.AlertCategoryResponse;
import com.example.error.ErrorCode;
import com.example.exception.AlertException;
import com.example.service.AlertService;
import com.example.service.ExportService;
import com.example.util.AlertsFormatUtils;
import com.example.util.FlowType;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.example.util.AlertConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.Token;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.dozer.CsvDozerBeanWriter;
import org.supercsv.io.dozer.ICsvDozerBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
@Slf4j
public class ExportServiceImpl implements ExportService {

    private static final String OUTPUT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final DateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    private static final String HEADER_KEY = "Content-Disposition";
    private static final String CONTENT_TYPE = "text/csv";

    private static final String[] FIELD_MAPPING = new String[]{
            "id",
            "alertCategoryName",
            "generalAlertId",
            "createdBy",
            "creationTimestamp",
            "modifiedBy",
            "modificationTimestamp"
    };

    private static final CellProcessor[] PROCESSORS = new CellProcessor[]{
            new Token(0, null),
            new Token(0, null),
            new Token(0, null),
            new Token(0, null),
            new FmtDate(OUTPUT_DATE_FORMAT),
            new Token(0, null),
            new FmtDate(OUTPUT_DATE_FORMAT)

    };
    private static final String[] CSV_HEADERS = new String[]{
            "ID",
            "CATEGORY NAME",
            "GENERAL ALERT ID",
            "CREATED BY",
            "CREATION DATE",
            "MODIFIER",
            "MODIFICATION DATE"
    };


    @Autowired
    private AlertService alertService;

    @Autowired
    private NotificationService notificationService;

    @Value("${notify.enabled}")
    private boolean notifyEnabled;

    @Value("${callback.url}")
    private String callbackUrl;

    @Value("${file.name.prefix}")
    private String filePrefix;

    @Override
    public void exportToCsv(HttpServletResponse response) throws IOException, AlertException {


        AlertsFormatUtils.printFlowSeparator(true, FlowType.EXPORT_TO_CSV);

        response.setContentType(CONTENT_TYPE);

        final String fileName = this.filePrefix + DATE_FORMATTER.format(new Date()) + ".csv";

        final String headerValue = "attachment; filename=" + fileName;

        response.setHeader(HEADER_KEY, headerValue);

        final List<AlertCategoryResponse> alerts = this.alertService.getAllAlerts();

        ICsvDozerBeanWriter beanWriter = null;
        try {
            beanWriter = new CsvDozerBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

            beanWriter.configureBeanMapping(AlertCategoryResponse.class, FIELD_MAPPING);

            beanWriter.writeHeader(CSV_HEADERS);

            for (final AlertCategoryResponse alert : alerts) {
                beanWriter.write(alert, PROCESSORS);
            }

            if (this.notifyEnabled) {

                StringBuilder sb = new StringBuilder();
                sb.append("Flow ");
                sb.append(FlowType.EXPORT_TO_CSV);
                sb.append(" ");
                sb.append("COMPLETED");
                sb.append(" with status ");
                sb.append("SUCCESS");
                sb.append(". Alerts exported into csv file ");
                sb.append(fileName);

                final String notifyMessage = sb.toString();

                log.info(notifyMessage);

                notificationService.notify(
                        UUID.randomUUID().toString(),
                        FlowType.EXPORT_TO_CSV,
                        NotificationRequest.builder().
                                url(callbackUrl).
                                message(notifyMessage).
                                build());
            } else {
                log.info("Skipping success notification...");
            }

            AlertsFormatUtils.printFlowSeparator(false, FlowType.EXPORT_TO_CSV);

        } catch (Exception e) {

            final String errorMessage =
                    ErrorCode.CSV_EXPORT_ERROR.getCode() + AlertConstants.ERROR_MESSAGE_DELIMITER +
                            "Exception : " + e.getLocalizedMessage() +
                            AlertConstants.ERROR_MESSAGE_DELIMITER;
            if (this.notifyEnabled) {

                StringBuilder sb = new StringBuilder();
                sb.append("Flow ");
                sb.append(FlowType.EXPORT_TO_CSV);
                sb.append(" ");
                sb.append("FAILED");
                sb.append(" with status ");
                sb.append("FAILURE");
                sb.append(". Unable To Export Alerts into csv file ");
                sb.append(errorMessage);

                notificationService.notify(
                        UUID.randomUUID().toString(),
                        FlowType.EXPORT_TO_CSV,
                        NotificationRequest.builder().
                                url(callbackUrl).
                                message(sb.toString()).
                                build());
            } else {
                log.info("Skipping failure notification...");
            }



            log.error(errorMessage);

            AlertsFormatUtils.printFlowSeparator(false, FlowType.EXPORT_TO_CSV);

            throw new AlertException(errorMessage);

        } finally {
            if (beanWriter != null) {
                beanWriter.close();
            }
        }
    }

}
