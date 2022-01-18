package ${package}.exposition.adapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import ${package}.domain.common.model.UniqueId;

import org.apache.commons.lang3.StringUtils;

public interface EntityMapperUtil {
    
    default UUID map(UniqueId uid) {
        return UUID.fromString(uid.getValue());
    }
    default UniqueId map(UUID uid) {
        return new UniqueId(uid.toString());
    }
    default String map(Optional<String> value){
        return value.orElse(StringUtils.EMPTY);
    }
    default String localDateToString(LocalDate value){
        return value.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    default LocalDate stringToLocalDate(String value){
        return LocalDate.parse(value, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
