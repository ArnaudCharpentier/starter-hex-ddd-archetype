package ${package}.infrastructure.event;

import org.springframework.lang.Nullable;

public interface Consumer<T> {
    void consume(@Nullable T dto);
}
