package org.digevil.sboot3user.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.digevil.sandbox.s.model.User;
import org.digevil.sboot3user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User API Controller", description = "用户API")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @Operation(summary = "根据用户id获取用户详情", description = "根据用户id返回单个用户对象",
            parameters = {@Parameter(name = "id", description = "用户id")})
    @ApiResponse(responseCode = "2xx", description = "用户对象")
    @GetMapping(value = "/find/{id}", produces = "application/json")
    public User findById(@PathVariable int id) {
        return userService.findById(id);
    }
}
