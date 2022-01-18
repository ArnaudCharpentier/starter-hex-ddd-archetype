#if($orm == 'xml')
__REMOVE_ME__
#end
package ${package}.infrastructure;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

import ${package}.domain.common.model.UniqueId;
import ${package}.domain.${rootAggregateLowerCase.toLowerCase()}.model.${rootAggregate};
import ${package}.infrastructure.persistence.adapter.${rootAggregate}Mapper;
import ${package}.infrastructure.persistence.model.${rootAggregate}Entity;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ${rootAggregate}MapperTest {

    @Autowired
    private ${rootAggregate}Mapper ${rootAggregateLowerCase}Mapper;

    @Test
    public void whenCoreDomainIsMappedInDomainEntityItShouldBeOk() throws Exception {

        if(${rootAggregateLowerCase}Mapper == null)
            ${rootAggregateLowerCase}Mapper = Mappers.getMapper(${rootAggregate}Mapper.class);

        assertNotEquals(null, ${rootAggregateLowerCase}Mapper);

        ${rootAggregate} ${rootAggregateLowerCase} = ${rootAggregate}.builder()
                .id(new UniqueId())
                .firstName("John")
                .lastName("Doe")
                .birthDate(LocalDate.of(1980, Month.JANUARY, 1))
                .build();

        ${rootAggregate}Entity ${rootAggregateLowerCase}Entity = ${rootAggregateLowerCase}Mapper.domainToEntity(${rootAggregateLowerCase});
        assertEquals(${rootAggregateLowerCase}Entity.getFirstName(), ${rootAggregateLowerCase}.getFirstName());
        assertEquals(${rootAggregateLowerCase}Entity.getLastName(), ${rootAggregateLowerCase}.getLastName());
        assertEquals(${rootAggregateLowerCase}Entity.getId(), ${rootAggregateLowerCase}.getId().getValue());
    }

    @Test
    public void whenEntityDomainIsMappedIntoCoreDomainEntityItShouldBeOk() throws Exception {

        if(${rootAggregateLowerCase}Mapper == null)
            ${rootAggregateLowerCase}Mapper = Mappers.getMapper(${rootAggregate}Mapper.class);

        assertNotEquals(null, ${rootAggregateLowerCase}Mapper);
        ${rootAggregate}Entity ${rootAggregateLowerCase}Entity = new ${rootAggregate}Entity(UUID.randomUUID().toString(), "John", "Doe", "01/01/1980");
        ${rootAggregate} ${rootAggregateLowerCase} = ${rootAggregateLowerCase}Mapper.entityToDomain(${rootAggregateLowerCase}Entity);
        assertEquals(${rootAggregateLowerCase}.getFirstName(), ${rootAggregateLowerCase}Entity.getFirstName());
        assertEquals(${rootAggregateLowerCase}.getLastName(), ${rootAggregateLowerCase}Entity.getLastName());
        assertEquals(${rootAggregateLowerCase}.getId().getValue(), ${rootAggregateLowerCase}Entity.getId());
    }

}
