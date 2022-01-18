package ${package}.application.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.security.access.prepost.PreAuthorize;

#if ($database == 'h2' || $database == 'postgresql')
import org.springframework.transaction.annotation.Transactional;
#end 

import ${package}.application.service.Recuperer${rootAggregate}Service;

import ${package}.domain.${rootAggregateLowerCase.toLowerCase()}.model.${rootAggregate};
import ${package}.domain.${rootAggregateLowerCase.toLowerCase()}.repository.${rootAggregate}RepositoryPort;

@Service
#if ($database == 'h2' || $database == 'postgresql')
@Transactional
#end 
public class Recuperer${rootAggregate}ServiceImpl implements Recuperer${rootAggregate}Service {

    /**
     * These are automatically created method
     * for example purpose only
     * Remove or rename it depending of your needs
     */

    private final ${rootAggregate}RepositoryPort ${rootAggregateLowerCase}RepositoryPort;

    public Recuperer${rootAggregate}ServiceImpl(${rootAggregate}RepositoryPort ${rootAggregateLowerCase}RepositoryPort) {
        this.${rootAggregateLowerCase}RepositoryPort = ${rootAggregateLowerCase}RepositoryPort;
    }

    @Override
    @PreAuthorize("@authorities.hasViewPrivileges(authentication)")
    public List<${rootAggregate}> getAll${rootAggregate}s() {
        return ${rootAggregateLowerCase}RepositoryPort.getAll${rootAggregate}s();
    }
}
