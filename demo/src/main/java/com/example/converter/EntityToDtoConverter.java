package com.example.converter;

import com.example.dao.entity.dbalert;
import com.example.dto.responce.AlertCategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class EntityToDtoConverter {
    public AlertCategoryResponse convertAlertToAlertResponse(dbalert alert) {
        return AlertCategoryResponse.builder().
                Id(alert.getId()).
                alertCategoryName(alert.getAlertCategoryName()).
                generalAlertId(alert.getGeneralAlertId()).
                createdBy(alert.getCreatedBy()).
                creationTimestamp(alert.getCreationTimestamp()).
                modifiedBy(alert.getModifiedBy()).
                modificationTimestamp(alert.getModificationTimestamp()).
                build();
    }

}
