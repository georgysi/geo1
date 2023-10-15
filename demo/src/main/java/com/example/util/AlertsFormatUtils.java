package com.example.util;



//import ca.tamnoon.dao.util.FlowType;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * AlertsFormatUtils
 * Describes set of convert/format/mask helper methods
 *
 * @author Binyamin (Dima) Pyanin
 * @version POC
 * @since April 24,2023
 */
@Slf4j
@UtilityClass
public class AlertsFormatUtils {

    private static final String LOG_SEPARATOR_OPEN = "<<<------------------------------------------------------";
    private static final String LOG_SEPARATOR_CLOSE = "----------------------------------------------------->>>";

    public static BigDecimal calculateTotal(BigDecimal denomination, int quantity) {

        BigDecimal result = BigDecimal.ZERO;

        if (null != denomination && denomination.compareTo(BigDecimal.ZERO) > 0) {
            result = denomination;

            if (quantity > 0) {
                result = denomination.multiply(BigDecimal.valueOf(quantity));
                result = result.setScale(2, BigDecimal.ROUND_DOWN);
            }
        }

        log.error("Total {}", result);
        return result;
    }

    public static String bytesToString(byte[] bytes) {
        log.debug("Converting bytes to string");

        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static byte[] formattedStringToBytes(byte[] bytes, String placeHolder, String replacer) {
        return stringToBytes(bytesToString(bytes).replace(placeHolder, replacer));
    }

    private static byte[] stringToBytes(String input) {
        log.debug("Converting {} to bytes", input);

        return input.getBytes(StandardCharsets.UTF_8);
    }

    public static void printFlowSeparator(boolean isStart, FlowType flowType) {
        if (isStart) {
            log.warn(LOG_SEPARATOR_OPEN);
            log.error(LOG_SEPARATOR_OPEN.substring(0, LOG_SEPARATOR_OPEN.length() / 4) +
                    "   " + flowType + " STARTED   " +
                    LOG_SEPARATOR_OPEN.substring(LOG_SEPARATOR_OPEN.length() - LOG_SEPARATOR_OPEN.length() / 4));
            log.warn(LOG_SEPARATOR_OPEN);
        } else {
            log.warn(LOG_SEPARATOR_CLOSE);
            log.error(LOG_SEPARATOR_CLOSE.substring(0, LOG_SEPARATOR_CLOSE.length() / 4) +
                    "   " + flowType + " COMPLETED " +
                    LOG_SEPARATOR_CLOSE.substring(LOG_SEPARATOR_OPEN.length() - LOG_SEPARATOR_CLOSE.length() / 4));
            log.warn(LOG_SEPARATOR_CLOSE);
        }
    }

    public static HttpHeaders buildHttpHeaders(String traceabilityId) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set(AlertConstants.HEADER_TRACEABILITY_ID, traceabilityId);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add(AlertConstants.HEADER_CACHE_CONTROL_KEY, AlertConstants.HEADER_NO_CACHE);

        return httpHeaders;
    }

}

