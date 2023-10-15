package com.example.dto.responce;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.sql.Timestamp;

/**
 * AlertCategoryResponse
 * Describes Alert Category response DTO
 *
 * @author Binyamin (Dima) Pyanin
 * @version POC
 * @since April 24,2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertCategoryResponse  {
    private Integer Id;
    private String alertCategoryName;
    private String generalAlertId;
    private String createdBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "E, dd MMM yyyy HH:mm:ss")
    private Timestamp creationTimestamp;
    private String modifiedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "E, dd MMM yyyy HH:mm:ss")
    private Timestamp modificationTimestamp;
}
