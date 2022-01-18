package ${package}.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
#if($orm != 'xml')
import java.util.Optional;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ${package}.infrastructure.persistence.model.${rootAggregate}Entity;
#end

import ${package}.domain.${rootAggregateLowerCase.toLowerCase()}.model.${rootAggregate};


/**
 * {@link ${rootAggregate}} repository.
 */
public interface ${rootAggregate}Repository extends JpaRepository<${rootAggregate}#if($orm != 'xml')Entity#end, String> #if($orm != 'xml'), QuerydslPredicateExecutor<${rootAggregate}Entity>#end {

    /**
     * Basic query sample finding a list by name using query method. Declaring the method derived a query from the
     * method firstName.
     * 
     * @param firstName the firstName of the searched objects.
     * @return the matching list of the object.
     */
    #if($orm == 'xml')
    List<${rootAggregate}> findAllByFirstName(String firstName);
    #else
    List<${rootAggregate}Entity> findAllByFirstName(String firstName);
    #end
}
