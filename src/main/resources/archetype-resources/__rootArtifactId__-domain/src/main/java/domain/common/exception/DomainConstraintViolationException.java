package ${package}.domain.common.exception;

public class DomainConstraintViolationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DomainConstraintViolationException(String message) {
        super(message);
    }
}
