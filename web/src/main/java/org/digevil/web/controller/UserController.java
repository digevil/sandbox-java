package org.digevil.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.digevil.web.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huangtao729 on 2017/12/15.
 */
@RestController
@RequestMapping("/user")
@Api(value = "User API", description = "these APIs operates users")
public class UserController {

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ApiOperation(value = "get user by user id", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully got the user"),
            @ApiResponse(code = 401, message = "You are not authorized to get the user info")
    })
    public User getUserById(@PathVariable Integer userId) {
        return new User();
    }

}
