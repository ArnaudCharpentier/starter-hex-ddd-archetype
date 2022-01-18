package ${package}.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ${package}.application.service.impl.Recuperer${rootAggregate}ServiceImpl;
import ${package}.domain.${rootAggregateLowerCase.toLowerCase()}.model.${rootAggregate};
import ${package}.domain.${rootAggregateLowerCase.toLowerCase()}.repository.${rootAggregate}RepositoryPort;

@ExtendWith(MockitoExtension.class)
public class Recuperer${rootAggregate}ServiceImplTest {

    @InjectMocks
    private Recuperer${rootAggregate}ServiceImpl ${rootAggregateLowerCase}ServicePort;

    @Mock
    private ${rootAggregate}RepositoryPort ${rootAggregateLowerCase}RepositoryPort;

    @Mock
    private List<${rootAggregate}> mock${rootAggregate}List;

    @Test
    public void givenCallToAll${rootAggregate}_whenNothingSpecified_thenGetAll${rootAggregate}sPortCalled() {
        when(${rootAggregateLowerCase}RepositoryPort.getAll${rootAggregate}s()).thenReturn(mock${rootAggregate}List);

        final List<${rootAggregate}> all${rootAggregate}s = ${rootAggregateLowerCase}ServicePort.getAll${rootAggregate}s();

        assertThat(all${rootAggregate}s).isSameAs(mock${rootAggregate}List);
        verify(${rootAggregateLowerCase}RepositoryPort, only()).getAll${rootAggregate}s();
    }

}
