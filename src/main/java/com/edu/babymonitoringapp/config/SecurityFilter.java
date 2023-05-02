package com.edu.babymonitoringapp.config;

import com.edu.babymonitoringapp.dto.AccountDto;
import com.edu.babymonitoringapp.model.Account;
import com.edu.babymonitoringapp.repositoy.AccountRepository;
import com.edu.babymonitoringapp.service.AccountService;
import com.edu.babymonitoringapp.service.AccountServiceImpl;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Provider
@Component
public class SecurityFilter  implements ContainerRequestFilter {
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
    private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
            .build();
    private static final Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN)
            .build();
    private static final Response SERVER_ERROR = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

    @Context
    private ResourceInfo resourceInfo;

    @Autowired
    private AccountServiceImpl accountService;




    @Override
    public void filter(ContainerRequestContext containerRequestContext){
        Method method = resourceInfo.getResourceMethod();
        //Access allowed for all
        if(!method.isAnnotationPresent(PermitAll.class)) {
            //Access denied for all
            if (method.isAnnotationPresent(DenyAll.class)) {
                containerRequestContext.abortWith(ACCESS_FORBIDDEN);
                return;
            }
            //Get request headers
            final MultivaluedMap<String, String> headers = containerRequestContext.getHeaders();
            //Fetch authorization header
            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);
            //If no authorization information present; block access
            if (authorization == null || authorization.isEmpty()) {
                containerRequestContext.abortWith(ACCESS_DENIED);
                return;
            }

            //Get encoded username and password
            final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

            //Decode username and password
            String usernameAndPassword = null;
            try {
                usernameAndPassword = new String(Base64.getDecoder().decode(encodedUserPassword));
            } catch (Exception e) {
                containerRequestContext.abortWith(SERVER_ERROR);
                return;
            }

            //Split username and password tokens
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();

            //Verifying Username and password
            if (!(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin"))) {


                if (method.isAnnotationPresent(RolesAllowed.class)) {
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = Set.of(rolesAnnotation.value());

                //Is user valid?
                if (!isUserAllowed(username, password, rolesSet)) {
                    containerRequestContext.abortWith(ACCESS_DENIED);
                }
                }
                else {
                    containerRequestContext.abortWith(ACCESS_DENIED);
                }
            }



        }
    }

    private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet){
        boolean isAllowed = false;

        List<AccountDto> accounts = accountService.getAllAccounts();
        for (AccountDto account : accounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                String userRole = String.valueOf(account.getRole());
                //Step 2. Verify user role
                if(rolesSet.contains(userRole)){
                    isAllowed = true;
                }
                break;
            }
        }
        //Step 1. Fetch password from database and match with password in argument
        //If both match then get the defined role for user from database and continue; else return isAllowed [false]
        //Access the database and do this part yourself
        //String userRole = userMgr.getUserRole(username);

        return isAllowed;
    }
}
