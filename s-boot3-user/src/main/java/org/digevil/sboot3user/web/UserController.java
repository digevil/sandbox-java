package org.digevil.sboot3user.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.digevil.sandbox.s.model.User;
import org.digevil.sboot3user.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "User API Controller", description = "用户API")
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Resource
    UserService userService;

    @Operation(summary = "根据用户id获取用户详情(json)", description = "根据用户id返回单个用户对象",
            parameters = {@Parameter(name = "id", description = "用户id")})
    @ApiResponse(responseCode = "2xx", description = "用户对象(json)")
    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User findById(@PathVariable int id) {
        User user = userService.findById(id);
        logger.info("this is a info log message: {}", user::getUuid);
        logger.debug("this is a debug log message: {}", () -> user);
        return user;
    }

    /**
     * TODO 需要注册一个 xml 的 HttpMessageConverter
     *
     * @param id
     * @return
     */
    @Operation(summary = "根据用户id获取用户详情(xml)", description = "根据用户id返回单个用户对象，需要注册一个 xml 的 HttpMessageConverter，引入 jaxb-runtime 或者 jackson-dataformat-xml 均可以让 springboot 自动注册一个 xml的转换器",
            parameters = {@Parameter(name = "id", description = "用户id")})
    @ApiResponse(responseCode = "2xx", description = "用户对象(xml)")
    @GetMapping(value = "/find/xml/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public User findXmlById(@PathVariable int id) {
        return userService.findById(id);
    }

    /**
     * TODO 需要注册一个 xml 的 HttpMessageConverter
     *
     * @param id
     * @return
     */
    @Operation(summary = "根据用户id获取用户详情或者给出样例(json)", description = "根据用户id返回单个用户对象",
            parameters = {@Parameter(name = "id", description = "用户id")})
    @ApiResponse(responseCode = "2xx", description = "用户对象(json)")
    @GetMapping(value = {"find/sample/{id}", "find/sample"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public User findSampleOrById(@PathVariable Optional<Integer> id) {
        return id
                .map(userService::findById)
                .orElseGet(() -> {
                    logger.info("id is empty, so get sample instead");
                    return userService.findById(1);
                });
    }

    @Operation(summary = "新建用户", description = "新建用户对象",
            parameters = {@Parameter(name = "user", description = "用户实体对象")})
    @PostMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User insert(@RequestBody User user) {
        userService.insert(user);
        return user;
    }
}
