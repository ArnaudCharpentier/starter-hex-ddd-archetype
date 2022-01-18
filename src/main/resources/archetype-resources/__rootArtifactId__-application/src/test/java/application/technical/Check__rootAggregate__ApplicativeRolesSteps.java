package ${package}.application.technical;

import ${package}.application.config.Authorities;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonnéqu;
import io.cucumber.java.fr.Quand;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Check${rootAggregate}ApplicativeRolesSteps {

    private final static String SERVICE_NAME    = "servicePath";
    private final static String METHOD_NAME     = "methodName";

    @Resource(name="authorities")
    private Authorities         authorities;
    private Optional<Method>    authorityMethod;
    private Method              methodToTest;

    @Etantdonnéqu("^il y a des méthodes sécurisées dans la couche application service:$")
    public void thereIsTheFollowingApplicationServiceAndOperation(Map<String, String> serviceAndMethodNames) throws NoSuchMethodException, ClassNotFoundException {
        String serviceClassName = serviceAndMethodNames.get(SERVICE_NAME);
        Class<?> serviceClass = tryToGetServiceClass(serviceClassName);
        String methodName = serviceAndMethodNames.get(METHOD_NAME);
        methodToTest = tryToGetMethod(serviceClass, methodName);
    }

    @Quand("^un utilisateur essaie d'exécuter la méthode spécifique \"([^\"]*)\"$")
    public void aUserTriesToDoTheSpecifiedOperation(String operation) {
        PreAuthorize actualRolesContainer = Optional.ofNullable(methodToTest.getAnnotation(PreAuthorize.class))
                .orElseThrow(() -> new NoSuchElementException(
                        format("PreAuthorize annotation not found on method <%s>", methodToTest.getName()))
                );
        if(actualRolesContainer != null  && actualRolesContainer.value().startsWith("@authorities"))
            authorityMethod = stream(authorities.getClass().getDeclaredMethods())
                .filter(m -> m.getName().equals(
                        actualRolesContainer.value().substring(
                            actualRolesContainer.value().indexOf(".") + 1,
                            actualRolesContainer.value().indexOf("("))))
                .findFirst();
    }

    @Alors("^il est autorisé a exécuter cette opération si il a le rôle parmi \"([^\"]*)\"$")
    public void heIsAllowedToDoTheOperationOnlyIfHeHasTheFollowing(String expectedRolesConcatenated) {
        List<String> expectedRoles = getRawExpectedRoles(expectedRolesConcatenated);
        for(String role: expectedRoles) {
            Authentication authentication = mockAuthenticationWithRoles(role);
            if (authorityMethod.isPresent()) {
                Boolean result = false;
                try {
                    result = (Boolean) authorityMethod.get().invoke(authorities, authentication);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                assertThat(result).isTrue();
            }
        }
    }

    @Alors("^il n'est pas autorisé à exécuter cette opération si il a le rôle parmi \"([^\"]*)\"$")
    public void heIsNotAllowedToDoTheOperationIfHeHasTheFollowing(String deniedRolesConcatenated) {
        List<String> expectedRoles = getRawExpectedRoles(deniedRolesConcatenated);
        for (String role : expectedRoles) {
            Authentication authentication = mockAuthenticationWithRoles(role);
            if (authorityMethod.isPresent()) {
                Boolean result = true;
                try {
                    result = (Boolean) authorityMethod.get().invoke(authorities, authentication);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                assertThat(result).isFalse();
            }
        }
    }

    private Authentication mockAuthenticationWithRoles(String role) {
        List<String> neededRoles = asList(role);
        if(role.startsWith("["))
            neededRoles = asList(role.substring(1, role.length() - 1).split(","));

        Collection grantedAuhorities = neededRoles.stream().map(s -> new SimpleGrantedAuthority(s)).collect(Collectors.toList());

        Authentication authentication = mock(Authentication.class);
        when(authentication.getAuthorities()).thenReturn(grantedAuhorities);
        return  authentication;
    }

    private List<String> getRawExpectedRoles(String expectedRolesConcatenated) {
        return asList(
                expectedRolesConcatenated.replace(" ", "")
                .split(","));
    }

    private Class<?> tryToGetServiceClass(String serviceClassName) throws ClassNotFoundException {
        Class<?> serviceClass;
        try {
            serviceClass = Class.forName(serviceClassName);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException(
                    format("Class with name <%s> was not found, if you used simple name try to use full path name", serviceClassName)
            );
        }
        return serviceClass;
    }

    private Method tryToGetMethod(Class<?> serviceClass, String methodName) throws NoSuchMethodException {
        List<Method> methodsFound = stream(serviceClass.getDeclaredMethods())
                .filter(m -> m.getName().equals(methodName))
                .collect(toList());
        if (methodsFound.size() == 0) {
            throw new NoSuchMethodException(format("No method found with name: <%s>", methodName));
        }
        if (methodsFound.size() > 1) {
            throw new IllegalStateException(format("More than one method found with name: <%s>", methodName));
        }
        return methodsFound.get(0);
    }
}
