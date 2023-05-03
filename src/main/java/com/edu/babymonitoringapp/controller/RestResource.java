package com.edu.babymonitoringapp.controller;

import com.edu.babymonitoringapp.dto.AccountDto;
import com.edu.babymonitoringapp.service.AccountService;
import com.edu.babymonitoringapp.service.RecordService;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

//
//@XmlAccessorType(XmlAccessType.NONE)
//@XmlRootElement(name = "RestResource")
@Path("/rest")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
public class RestResource {

    @Autowired
    private AccountService accountService;
//    @Autowired
//    private RecordService recordService;

    @PermitAll()
    @POST
    @Consumes("application/json")
    @Path("/createAccount")
//    @CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
    public Response createAccount(AccountDto accountDto) {
        accountService.createAccount(accountDto);

        return Response.status(201)
                .status(Response.Status.CREATED)
//                .header("Access-Control-Allow-Origin", "*")
//                .header("Access-Control-Allow-Credentials", "true")
//                .header("Access-Control-Allow-Headers",
//                        "origin, content-type, accept, authorization")
//                .header("Access-Control-Allow-Methods",
//                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity("Account created successfully !!").build();
    }

    @PermitAll
    @GET
    @Path("/getAllAccounts")
    @CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
    @Produces("application/json")
    public List<AccountDto> getAllAccounts() {
        return accountService.getAllAccounts();

    }

    @PermitAll
    @POST
    @Consumes("application/json")
    @Path("/login")
    public Response login(AccountDto accountDto) {
        //accountService.login(accountDto);
        if (accountService.login(accountDto)) {
            return Response.status(201)
                    .entity("Login successfully !!").build();
        } else {
            return Response.status(401)
                    .entity("Login failed !!").build();
        }

    }

    @PermitAll
    @GET
    @Path("/getAccountById/{id}")
    public Response getAccountById(@PathParam("id") Long id) {
        return Response.ok(accountService.getAccountById(id)).build();
    }

    @OPTIONS
    @Path("{path : .*}")
    public Response options() {
        return Response.ok("")
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .build();
    }
}
