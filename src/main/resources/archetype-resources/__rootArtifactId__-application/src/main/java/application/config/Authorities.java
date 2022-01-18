package ${package}.application.config;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service("authorities")
public class Authorities {

    public boolean hasViewPrivileges(Authentication auth) {
        return auth.getAuthorities().stream().anyMatch(autority -> listConsult().contains(autority.getAuthority()));
    }

    public boolean hasManagePrivileges(Authentication auth) {
        return auth.getAuthorities().stream().anyMatch(autority -> listManage().contains(autority.getAuthority()));
    }

    /**
     * Create here list of roles
     * that can perform type of actions
     */

    private List<String> listConsult() {
        return Arrays.asList(
                Roles.CAN_READ,
                Roles.CAN_CREATE,
                Roles.CAN_UPDATE,
                Roles.CAN_DELETE);
    }

    private List<String> listManage() {
        return Arrays.asList(
                Roles.CAN_CREATE,
                Roles.CAN_UPDATE,
                Roles.CAN_DELETE);
    }

    public class Roles {

        private Roles(){}
        /**
         * Put here your applicative roles
         */
        public static final String CAN_READ     = "ROLE_CAN_READ";
        public static final String CAN_CREATE   = "ROLE_CAN_CREATE";
        public static final String CAN_UPDATE   = "ROLE_CAN_UPDATE";
        public static final String CAN_DELETE   = "ROLE_CAN_DELETE";
    }
}
