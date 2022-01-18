
package ${package}.infrastructure;

import org.hamcrest.MatcherAssert;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
#if($orm != 'xml')
import ${package}.infrastructure.persistence.model.${rootAggregate}Entity;
#else
import ${package}.domain.${rootAggregate.toLowerCase()}.model.${rootAggregate};
import ${package}.domain.common.model.UniqueId;
import java.time.LocalDate;
#end
import ${package}.infrastructure.persistence.repository.${rootAggregate}Repository;

/**
 * Test class.
 * 
 * Keep in mind that, by default, @DataJpaTest :
 * <ul>
 * <li>keep the same database of all test classes</li>
 * <li>prevent transaction commit and execute</li>
 * <li>rollback transaction between each test method</li>
 * </ul>
 */
@DataJpaTest(showSql = true)
@Tag(Tags.COMPONENT)
@ActiveProfiles("test")
@ContextConfiguration(classes = {${rootAggregate}Repository.class})
@EnableAutoConfiguration
public class ${rootAggregate}RepositoryTest {

    @Autowired
    private ${rootAggregate}Repository ${rootAggregateLowerCase}Repository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void loadData() throws Exception {

    #if($orm != 'xml')
        ${rootAggregate}Entity p1 = new ${rootAggregate}Entity();
        p1.setId(UUID.randomUUID().toString());
        p1.setFirstName("A");
        p1.setLastName("A");
        entityManager.persistAndFlush(p1);

        ${rootAggregate}Entity p2 = new ${rootAggregate}Entity();
        p2.setId(UUID.randomUUID().toString());
        p2.setFirstName("B");
        p2.setLastName("B");
        entityManager.persistAndFlush(p2);

        ${rootAggregate}Entity p3 = new ${rootAggregate}Entity();
        p3.setId(UUID.randomUUID().toString());
        p3.setFirstName("C");
        p3.setLastName("C");
        entityManager.persistAndFlush(p3);

        ${rootAggregate}Entity p4 = new ${rootAggregate}Entity();
        p4.setId(UUID.randomUUID().toString());
        p4.setFirstName("D");
        p4.setLastName("D");
        entityManager.persistAndFlush(p4);

        ${rootAggregate}Entity p5 = new ${rootAggregate}Entity();
        p5.setId(UUID.randomUUID().toString());
        p5.setFirstName("E");
        p5.setLastName("E");
        entityManager.persistAndFlush(p5);
        
        ${rootAggregate}Entity p6 = new ${rootAggregate}Entity();
        p6.setId(UUID.randomUUID().toString());
        p6.setFirstName("F");
        p6.setLastName("F");
        entityManager.persistAndFlush(p6);
    #else
        ${rootAggregate} p1 = ${rootAggregate}.builder()
            .id(new UniqueId())
            .birthDate(LocalDate.now().minusYears(20))
            .firstName("A")
            .lastName("A")
            .build();
        entityManager.persistAndFlush(p1);

        ${rootAggregate} p2 = ${rootAggregate}.builder()
            .id(new UniqueId())
            .birthDate(LocalDate.now().minusYears(20))
            .firstName("B")
            .lastName("B")
            .build();
        entityManager.persistAndFlush(p2);

        ${rootAggregate} p3 = ${rootAggregate}.builder()
            .id(new UniqueId())
            .birthDate(LocalDate.now().minusYears(20))
            .firstName("C")
            .lastName("C")
            .build();
        entityManager.persistAndFlush(p3);

        ${rootAggregate} p4 = ${rootAggregate}.builder()
            .id(new UniqueId())
            .birthDate(LocalDate.now().minusYears(20))
            .firstName("D")
            .lastName("D")
            .build();
        entityManager.persistAndFlush(p4);

        ${rootAggregate} p5 = ${rootAggregate}.builder()
            .id(new UniqueId())
            .birthDate(LocalDate.now().minusYears(20))
            .firstName("E")
            .lastName("E")
            .build();
        entityManager.persistAndFlush(p5);
        
        ${rootAggregate} p6 = ${rootAggregate}.builder()
            .id(new UniqueId())
            .birthDate(LocalDate.now().minusYears(20))
            .firstName("F")
            .lastName("F")
            .build();
        entityManager.persistAndFlush(p6);
    #end
    }

    /**
     * Test save method for a successful call.
     * 
     * @throws Exception if error occurs.
     */
    @Test
    public void testSaveSuccess() throws Exception {
    #if($orm != 'xml')
        ${rootAggregate}Entity ${rootAggregateLowerCase} = new ${rootAggregate}Entity();
        ${rootAggregateLowerCase}.setId(UUID.randomUUID().toString());
        ${rootAggregateLowerCase}.setFirstName("Pierre");
        ${rootAggregateLowerCase}.setLastName("Dupont");

        // act
        ${rootAggregate}Entity result = ${rootAggregateLowerCase}Repository.save(${rootAggregateLowerCase});
    #else
        ${rootAggregate} ${rootAggregateLowerCase} = ${rootAggregate}.builder()
            .id(new UniqueId())
        .birthDate(LocalDate.now().minusYears(20))
            .firstName("Pierre")
            .lastName("Dupont")
            .build();

        // act
        ${rootAggregate} result = ${rootAggregateLowerCase}Repository.save(${rootAggregateLowerCase});
    #end

        entityManager.flush(); // Synchronize to activate validation
        // assert
        assertNotNull(result);
        assertNotNull(result.getId());
        
    }

        #if($orm != 'xml')
    /**
     * Test save method for an invalid object.
     * 
     * @throws Exception if error occurs.
     */
    @Test
    public void testSaveValidationFailed() throws Exception {
        // arrange
        ${rootAggregate}Entity ${rootAggregateLowerCase} = new ${rootAggregate}Entity();

        // act
        Exception result = null;
        try {
            ${rootAggregateLowerCase}Repository.save(${rootAggregateLowerCase});
            entityManager.flush(); // Synchronize to activate validation
        } catch (Exception e) {
            result = e;
        }
        // assert
        assertNotNull(result);
    }
    #end

    /**
     * Test find all using {@link Pageable}.
     * 
     * @throws Exception if error occurs.
     */
    @Test
    public void testFindAllWithPagination() throws Exception {
        // arrange
        Pageable pageable = PageRequest.of(1, 2, Sort.by("lastName"));

        // act
        Page<${rootAggregate}#if($orm != 'xml')Entity#end> result = ${rootAggregateLowerCase}Repository.findAll(pageable);

        // assert
        assertNotNull(result);
        assertEquals(3, result.getTotalPages());
        assertEquals(6L, result.getTotalElements());
        List<${rootAggregate}#if($orm != 'xml')Entity#end> helloList = result.stream().collect(Collectors.toList());
        assertEquals(2, helloList.size());
        MatcherAssert.assertThat(helloList, hasItem(allOf(hasProperty("lastName", equalTo("C")))));
        MatcherAssert.assertThat(helloList, hasItem(allOf(hasProperty("lastName", equalTo("D")))));
    }

    /**
     * Test find by firstName for a name returning results.
     * 
     * @throws Exception if error occurs.
     */
    @Test
    public void testFindByNameHavingResult() throws Exception {

        // act
        List<${rootAggregate}#if($orm != 'xml')Entity#end> result = ${rootAggregateLowerCase}Repository.findAllByFirstName("B");

        // assert
        assertNotNull(result);
        assertEquals(1, result.size());
        MatcherAssert.assertThat(result, hasItem(allOf(hasProperty("firstName", equalTo("B")))));
    }

}