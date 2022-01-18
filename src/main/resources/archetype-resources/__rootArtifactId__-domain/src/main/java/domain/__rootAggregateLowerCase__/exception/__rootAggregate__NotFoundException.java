package ${package}.domain.${rootAggregateLowerCase.toLowerCase()}.exception;

public class ${rootAggregate}NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public ${rootAggregate}NotFoundException(String name) {
        super(name);
    }
}
