package ${package}.exposition.adapter;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import ${package}.domain.${rootAggregateLowerCase.toLowerCase()}.model.${rootAggregate};
import ${package}.exposition.model.${rootAggregate}Dto;

/**
 * This mapper will convert expositionDTO to domain Model and vice versa
 * To implement generic methods converter, add it in EntityMapperUtil class  
 */
@Mapper(componentModel = "spring")
public interface ${rootAggregate}ExpositionMapper extends EntityMapperUtil{
    
    ${rootAggregate}Dto domainToDto(${rootAggregate} domain);

    /**
     * The @InheritInverseConfiguration( name = "domainToDto" ) will apply rules
     * described in domainToEntity method as inverse
     */
    @InheritInverseConfiguration( name = "domainToDto" )
    ${rootAggregate} dtoToDomain(${rootAggregate}Dto entity);
}
