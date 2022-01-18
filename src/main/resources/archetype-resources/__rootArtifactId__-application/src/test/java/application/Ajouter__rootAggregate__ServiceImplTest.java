package ${package}.application;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ${package}.application.service.impl.Ajouter${rootAggregate}ServiceImpl;
import ${package}.domain.${rootAggregateLowerCase.toLowerCase()}.model.${rootAggregate};
import ${package}.domain.${rootAggregateLowerCase.toLowerCase()}.repository.${rootAggregate}RepositoryPort;

@ExtendWith(MockitoExtension.class)
public class Ajouter${rootAggregate}ServiceImplTest {

    @InjectMocks
    private Ajouter${rootAggregate}ServiceImpl ${rootAggregateLowerCase}ServicePort;

    @Mock
    private ${rootAggregate}RepositoryPort ${rootAggregateLowerCase}RepositoryPort;

    @Test
    public void given${rootAggregate}_whenAdd_thenAddPortCalled() {
        final ${rootAggregate} mock${rootAggregate} = mock(${rootAggregate}.class);

        ${rootAggregateLowerCase}ServicePort.add${rootAggregate}(mock${rootAggregate});

        verify(${rootAggregateLowerCase}RepositoryPort, only()).add${rootAggregate}(mock${rootAggregate});
    }


}
