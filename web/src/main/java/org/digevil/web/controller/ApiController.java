package org.digevil.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huangtao729 on 2017/12/15.
 */
@RestController
@RequestMapping("/api")
@Api(value = "API", description = "description for API")
public class ApiController {

    @RequestMapping(value = "/api1", method = RequestMethod.GET)
    @ApiOperation(value = "sample api returns a simple string", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully response"),
            @ApiResponse(code = 401, message = "You are not authorized to call the api")
    })
    public String api1() {
        return "hello, api 1 caller";
    }

}
