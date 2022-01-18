package ${package}.application.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.security.access.prepost.PreAuthorize;

#if ($database == 'h2' || $database == 'postgresql')
import org.springframework.transaction.annotation.Transactional;
#end 

import ${package}.application.service.Supprimer${rootAggregate}Service;

import ${package}.domain.${rootAggregateLowerCase.toLowerCase()}.model.${rootAggregate};
import ${package}.domain.${rootAggregateLowerCase.toLowerCase()}.repository.${rootAggregate}RepositoryPort;

@Service
#if ($database == 'h2' || $database == 'postgresql')
@Transactional
#end 
public class Supprimer${rootAggregate}ServiceImpl implements Supprimer${rootAggregate}Service {

    /**
     * These are automatically created method
     * for example purpose only
     * Remove or rename it depending of your needs
     */

    private final ${rootAggregate}RepositoryPort ${rootAggregateLowerCase}RepositoryPort;

    public Supprimer${rootAggregate}ServiceImpl(${rootAggregate}RepositoryPort ${rootAggregateLowerCase}RepositoryPort) {
        this.${rootAggregateLowerCase}RepositoryPort = ${rootAggregateLowerCase}RepositoryPort;
    }

    @Override
    @PreAuthorize("@authorities.hasManagePrivileges(authentication)")
    public void remove${rootAggregate}(${rootAggregate} ${rootAggregateLowerCase}) {
        ${rootAggregateLowerCase}RepositoryPort.remove${rootAggregate}(${rootAggregateLowerCase});
    }

}
