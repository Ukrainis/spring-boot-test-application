package com.example.demo.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.*;

import com.example.demo.enums.TodoStatus;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<TodoStatus, String> {

    @Override
    public String convertToDatabaseColumn(TodoStatus status) {
        if (status == null) {
            return null;
        }
        return status.status;
    }

    @Override
    public TodoStatus convertToEntityAttribute(String status) {
        if (status == null) {
            return null;
        }
        return Stream.of(TodoStatus.values())
            .filter(st -> st.status.equals(status))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }    
}