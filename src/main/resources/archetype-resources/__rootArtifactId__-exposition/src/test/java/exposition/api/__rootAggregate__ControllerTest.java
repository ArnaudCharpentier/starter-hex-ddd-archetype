#set($aggregateApi = "${rootAggregateLowerCase.substring(0,1).toUpperCase()}${rootAggregateLowerCase.substring(1)}" )
package ${package}.exposition.api;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import ${package}.application.service.Ajouter${rootAggregate}Service;
import ${package}.application.service.Modifier${rootAggregate}Service;
import ${package}.application.service.Recuperer${rootAggregate}Service;
import ${package}.application.service.Supprimer${rootAggregate}Service;
import ${package}.exposition.adapter.${rootAggregate}ExpositionMapper;
import ${package}.exposition.model.${rootAggregate}Dto;
import com.bnpp.pf.pfspring.core.profile.PfSpringProfiles;
import com.bnpp.pf.pfspring.test.Tags;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Integration Test (launch server during tests)
 */
@Tag(Tags.COMPONENT)
@ActiveProfiles(PfSpringProfiles.TEST)
@ExtendWith(SpringExtension.class)
@WebMvcTest(${rootAggregate}ControllerImpl.class)
@ContextConfiguration(classes = { ${aggregateApi}V1Api.class, ${rootAggregate}ControllerImpl.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class ${rootAggregate}ControllerTest {

    @MockBean
    private Ajouter${rootAggregate}Service ajouter${rootAggregateLowerCase}Service;

    @MockBean
    private Modifier${rootAggregate}Service modifier${rootAggregate}Service;

    @MockBean
    private Supprimer${rootAggregate}Service supprimer${rootAggregate}Service;

    @MockBean
    private Recuperer${rootAggregate}Service recuperer${rootAggregate}Service;

    @MockBean
    private ${rootAggregate}ExpositionMapper ${rootAggregateLowerCase}Mapper;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext webAppCtx;
    
    private MockMvc mvc;

    @BeforeEach
    public void before() {
        mvc = MockMvcBuilders.webAppContextSetup(webAppCtx)
                .apply(springSecurity())
                .alwaysDo(print())
                .build();
    }

    @Test
    @WithMockUser
    public void add${rootAggregate}Success() throws Exception {
        final ResultActions resultActions = makeASecurizedWebCall();
        resultActions.andExpect(status().isCreated());
    }

    @Test
    @WithAnonymousUser
    public void whenCallSecurizedEndpointWithoutLoggedUserShouldReceived401() throws Exception {
        final ResultActions resultActions = makeASecurizedWebCall();
        resultActions.andExpect(status().is(401));
    }

    private ResultActions makeASecurizedWebCall() throws JsonProcessingException, Exception {
        return mvc.perform(
                    MockMvcRequestBuilders.post("/${appContext}/v1/${rootAggregateLowerCase}")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(build${rootAggregate}Dto()))
                        .characterEncoding("utf-8")
                        .accept(APPLICATION_JSON)
                        .with(csrf())
                );
    }

    private ${rootAggregate}Dto build${rootAggregate}Dto(){
        ${rootAggregate}Dto ${rootAggregateLowerCase} = new ${rootAggregate}Dto();
        ${rootAggregateLowerCase}.setId(UUID.randomUUID());
        ${rootAggregateLowerCase}.setFirstName("John");
        ${rootAggregateLowerCase}.setLastName("Doe");
        ${rootAggregateLowerCase}.setBirthDate("01/01/1980");
        return ${rootAggregateLowerCase};
    }

}
