package ${package}.domain.${rootAggregateLowerCase.toLowerCase()}.repository;

import ${package}.domain.${rootAggregateLowerCase.toLowerCase()}.model.${rootAggregate};
import java.util.List;

/**
 * Interface for ${rootAggregate} Repository Domain
 */
public interface ${rootAggregate}RepositoryPort {

    /**
     * Add the ${rootAggregateLowerCase} domain.
     *
     * @param ${rootAggregate} ${rootAggregateLowerCase} to Save
     * @return Saved ${rootAggregateLowerCase}
     */
    public ${rootAggregate} add${rootAggregate}(${rootAggregate} ${rootAggregateLowerCase});

    /**
     * Remove the ${rootAggregateLowerCase} domain.
     *
     * @param ${rootAggregate} ${rootAggregateLowerCase} to Save
     */
    public void remove${rootAggregate}(${rootAggregate} ${rootAggregateLowerCase});

    /**
     * Update the ${rootAggregateLowerCase} domain
     * 
     * @param ${rootAggregateLowerCase}
     */
    public void update${rootAggregate}(${rootAggregate} ${rootAggregateLowerCase});

    /**
     * Get all ${rootAggregateLowerCase}
     * 
     * @return a list of ${rootAggregateLowerCase} domain
     */
    public List<${rootAggregate}> getAll${rootAggregate}s();

}
