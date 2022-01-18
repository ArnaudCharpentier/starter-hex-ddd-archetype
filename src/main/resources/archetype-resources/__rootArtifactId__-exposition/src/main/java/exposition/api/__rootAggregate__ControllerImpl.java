#set($aggregateApi = "${rootAggregateLowerCase.substring(0,1).toUpperCase()}${rootAggregateLowerCase.substring(1)}" )
package ${package}.exposition.api;

import java.util.List;
import java.util.stream.Collectors;

import ${package}.application.service.Ajouter${rootAggregate}Service;
import ${package}.application.service.Modifier${rootAggregate}Service;
import ${package}.application.service.Supprimer${rootAggregate}Service;
import ${package}.application.service.Recuperer${rootAggregate}Service;

import ${package}.exposition.adapter.${rootAggregate}ExpositionMapper;
import ${package}.exposition.model.${rootAggregate}Dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${appContext}")
public class ${rootAggregate}ControllerImpl implements ${aggregateApi}V1Api {

    @Autowired
    private Ajouter${rootAggregate}Service ajouter${rootAggregate}Service;

    @Autowired
    private Modifier${rootAggregate}Service modifier${rootAggregate}Service;

    @Autowired
    private Supprimer${rootAggregate}Service supprimer${rootAggregate}Service;

    @Autowired
    private Recuperer${rootAggregate}Service recuperer${rootAggregate}Service;

    @Autowired
    private ${rootAggregate}ExpositionMapper ${rootAggregateLowerCase}Mapper;

    @Override
    public ResponseEntity<Void> add${rootAggregate}(${rootAggregate}Dto ${rootAggregateLowerCase}Dto) {
        ajouter${rootAggregate}Service.add${rootAggregate}(${rootAggregateLowerCase}Mapper.dtoToDomain(${rootAggregateLowerCase}Dto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> remove${rootAggregate}(${rootAggregate}Dto ${rootAggregateLowerCase}Dto) {
        supprimer${rootAggregate}Service.remove${rootAggregate}(${rootAggregateLowerCase}Mapper.dtoToDomain(${rootAggregateLowerCase}Dto));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Object> update${rootAggregate}(${rootAggregate}Dto ${rootAggregateLowerCase}Dto) {
        modifier${rootAggregate}Service.update${rootAggregate}(${rootAggregateLowerCase}Mapper.dtoToDomain(${rootAggregateLowerCase}Dto));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<${rootAggregate}Dto>> getAll${rootAggregate}s() {
        return new ResponseEntity<>(recuperer${rootAggregate}Service.getAll${rootAggregate}s().stream().map(${rootAggregateLowerCase}Mapper::domainToDto).collect(Collectors.toList()), HttpStatus.OK);
    }

}
