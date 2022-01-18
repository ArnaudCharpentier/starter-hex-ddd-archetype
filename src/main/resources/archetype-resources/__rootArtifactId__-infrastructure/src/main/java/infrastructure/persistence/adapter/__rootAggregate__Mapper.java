#if($orm == 'xml')
__REMOVE_ME__
#end
package ${package}.infrastructure.persistence.adapter;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import ${package}.domain.${rootAggregateLowerCase.toLowerCase()}.model.${rootAggregate};
import ${package}.infrastructure.persistence.model.${rootAggregate}Entity;
import ${package}.infrastructure.common.adapter.EntityMapperUtil;


/**
 * This mapper will convert modelEntity to domain Model and vice versa
 * To implement generic methods converter, add it in EntityMapperUtil class  
 */
@Mapper(componentModel = "spring")
public interface ${rootAggregate}Mapper extends EntityMapperUtil{
    
    /**
     * If you have a group of complexes Object in your domain and
     * that objects are stored as a flat Object in your infrastructure layer,
     * use the annotation below to map source to target.
     * Exemple is: You have a Signataire object in your ${rootAggregate} object, 
     * this class has 3 properties :
     *  - signataireFirstName
     *  - signataireLastName
     *  - signataireEmail
     * But this properties are directly acessible from your ${rootAggregate}Entity object,
     * in this cas use the mapping below :
     * 
     *  @Mapping(source = "signataire.signataireFirstName", target = "signataireFirstName")
     *  @Mapping(source = "signataire.signataireLastName", target = "signataireLastName")
     *  @Mapping(source = "signataire.signataireEmail", target = "signataireEmailAddress")
     */
    ${rootAggregate}Entity domainToEntity(${rootAggregate} domain);


    /**
     * The @InheritInverseConfiguration( name = "domainToEntity" ) will apply rules
     * described in domainToEntity method as inverse
     */
    @InheritInverseConfiguration( name = "domainToEntity" )
    ${rootAggregate} entityToDomain(${rootAggregate}Entity entity);
}
