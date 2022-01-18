package ${package}.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${package}.domain.${rootAggregateLowerCase.toLowerCase()}.model.${rootAggregate};
import ${package}.domain.${rootAggregateLowerCase.toLowerCase()}.exception.${rootAggregate}NotFoundException;
import ${package}.domain.${rootAggregateLowerCase.toLowerCase()}.repository.${rootAggregate}RepositoryPort;
#if($orm != 'xml')
import ${package}.infrastructure.persistence.model.${rootAggregate}Entity;
#end
import ${package}.infrastructure.persistence.repository.${rootAggregate}Repository;

/**
 * Right place for the infrastructure adapter implementation here
 */
@Service
public class ${rootAggregate}JpaAdapter implements ${rootAggregate}RepositoryPort {
    
    #if($orm != 'xml')
    @Autowired
    private ${rootAggregate}Mapper ${rootAggregateLowerCase}Mapper;
    #end

    @Autowired
    private ${rootAggregate}Repository ${rootAggregateLowerCase}Repository;

    @Override
    public ${rootAggregate} add${rootAggregate}(${rootAggregate} ${rootAggregateLowerCase}) {
    #if($orm == 'xml')
        return ${rootAggregateLowerCase}Repository.save(${rootAggregateLowerCase});
    #else
        final ${rootAggregate}Entity ${rootAggregateLowerCase}Entity = ${rootAggregateLowerCase}Mapper.domainToEntity(${rootAggregateLowerCase});
        return ${rootAggregateLowerCase}Mapper.entityToDomain(${rootAggregateLowerCase}Repository.save(${rootAggregateLowerCase}Entity));
    #end
    }

    @Override
    public void remove${rootAggregate}(${rootAggregate} ${rootAggregateLowerCase}) {
    #if($orm == 'xml')
        ${rootAggregateLowerCase}Repository.deleteById(${rootAggregateLowerCase}.getId().getValue());
    #else
        ${rootAggregateLowerCase}Repository.deleteById(${rootAggregateLowerCase}Mapper.domainToEntity(${rootAggregateLowerCase}).getId());
    #end
    }

    @Override
    public void update${rootAggregate}(${rootAggregate} ${rootAggregateLowerCase}) {
    #if($orm == 'xml')
        ${rootAggregateLowerCase}Repository.save(${rootAggregateLowerCase});
    #else
        final ${rootAggregate}Entity ${rootAggregateLowerCase}Entity = ${rootAggregateLowerCase}Repository.findById(${rootAggregateLowerCase}Mapper.domainToEntity(${rootAggregateLowerCase}).getId()).orElse(null);
        if (Objects.nonNull(${rootAggregateLowerCase}Entity)) {
            ${rootAggregateLowerCase}Entity.setFirstName(${rootAggregateLowerCase}.getFirstName());
            ${rootAggregateLowerCase}Entity.setLastName(${rootAggregateLowerCase}.getLastName());
            ${rootAggregateLowerCase}Repository.save(${rootAggregateLowerCase}Entity);
        }
    #end
    }

    @Override
    public List<${rootAggregate}> getAll${rootAggregate}s() {
    #if($orm == 'xml')
        return ${rootAggregateLowerCase}Repository.findAll();
    #else
        return ${rootAggregateLowerCase}Repository.findAll().stream().map(${rootAggregateLowerCase}Mapper::entityToDomain).collect(Collectors.toList());
    #end
    }

}