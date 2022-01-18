package ${package}.application.service;

import ${package}.domain.${rootAggregateLowerCase.toLowerCase()}.model.${rootAggregate};

import ${package}.domain.common.ddd.DDD;

/**
 * Ajouter${rootAggregate} Service
 * Should contains only one method
 * This service should have is own 
 * integration test file
 */
@DDD.ApplicationService
public interface Ajouter${rootAggregate}Service {

    /**
     * These are automatically created method
     * for example purpose only
     * Remove or rename it depending of your needs
     */

    void add${rootAggregate}(${rootAggregate} ${rootAggregateLowerCase});

}
