package com.edu.babymonitoringapp.controller;

import com.edu.babymonitoringapp.dto.AccountDto;
import com.edu.babymonitoringapp.service.AccountService;
import com.edu.babymonitoringapp.service.RecordService;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
//
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "RestResource")
@Path("/rest")
public class RestResource {

    @Autowired
    private AccountService accountService;
//    @Autowired
//    private RecordService recordService;

    @PermitAll()
    @POST
    @Consumes("application/json")
    @Path("/createAccount")
    public Response createAccount(AccountDto accountDto) {
        accountService.createAccount(accountDto);

        return Response.status(201)
                .entity("Account created successfully !!").build();
    }

    @PermitAll
    @GET
    @Path("/getAllAccounts")
    public Response getAllAccounts() {
        return Response.ok(accountService.getAllAccounts()).build();
    }

    @PermitAll
    @POST
    @Consumes("application/json")
    @Path("/login")
    public Response login(AccountDto accountDto) {
        //accountService.login(accountDto);

        return Response.status(201)
                .entity("Account created successfully !!").build();
    }
}
