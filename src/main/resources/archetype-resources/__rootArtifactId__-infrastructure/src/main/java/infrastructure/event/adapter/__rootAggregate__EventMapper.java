package ${package}.infrastructure.event.adapter;

import org.mapstruct.Mapper;

import ${package}.domain.${rootAggregateLowerCase.toLowerCase()}.model.${rootAggregate};
import ${package}.infrastructure.event.model.K${rootAggregate};
import ${package}.infrastructure.common.adapter.EntityMapperUtil;

@Mapper(componentModel = "spring")
public interface ${rootAggregate}EventMapper extends EntityMapperUtil{
    ${rootAggregate} avroToDomain(K${rootAggregate} avro);
    K${rootAggregate} domainToAvro(${rootAggregate} domain);
}
