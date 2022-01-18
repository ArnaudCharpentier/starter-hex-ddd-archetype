package ${package}.application.service;

import java.util.List;

import ${package}.domain.${rootAggregateLowerCase.toLowerCase()}.model.${rootAggregate};

import ${package}.domain.common.ddd.DDD;

/**
 * Ajouter${rootAggregate} Service
 * Should contains only one method
 * This service should have is own 
 * integration test file
 */
@DDD.ApplicationService
public interface Recuperer${rootAggregate}Service {

    /**
     * These are automatically created method
     * for example purpose only
     * Remove or rename it depending of your needs
     */

    List<${rootAggregate}> getAll${rootAggregate}s();

}
