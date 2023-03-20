package com.pritimahajan.crudfirestore.controller;

import com.google.api.ResourceDescriptor;
import com.pritimahajan.crudfirestore.domain.HistoryData;
import com.pritimahajan.crudfirestore.domain.User;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/v1/")
@Tag(name = "User API", description = "Add new user to database.")
public interface UserApi
{

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK"),
    })
    @PostMapping(value = "user/add")
    ResponseEntity<String> addUser(@Parameter(description = "User request object.", required = true) @Valid @RequestBody User body) throws IOException, ExecutionException, InterruptedException;


    @PostMapping(value = "history/add")
    ResponseEntity<String> addHistory(@Parameter(description = "HistoryData request object.", required = true) @Valid @RequestBody HistoryData body) throws IOException, ExecutionException, InterruptedException;


}
