
package com.pritimahajan.crudfirestore.controller;

import com.pritimahajan.crudfirestore.domain.HistoryData;
import com.pritimahajan.crudfirestore.domain.User;
import com.pritimahajan.crudfirestore.service.UserHistoryService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@Validated
public class UserApiController implements UserApi
{

    @Autowired
    private UserHistoryService userService;

    @Override
    public ResponseEntity<String> addUser(@Parameter(description = "User request object.", required = true) @Valid @RequestBody User body) throws IOException, ExecutionException, InterruptedException
    {
        userService.saveUser(body);
        return ResponseEntity.status(HttpStatus.CREATED).body( "Request accepted");
    }

    @Override
    public ResponseEntity<String> addHistory(HistoryData body) throws IOException, ExecutionException, InterruptedException
    {
        userService.saveHistory(body);
        return ResponseEntity.status(HttpStatus.CREATED).body( "Request accepted");
    }
}
