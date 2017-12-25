package org.digevil.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.digevil.web.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
    public User getUserById(@PathVariable Integer userId, HttpSession session) {
        System.out.println("session: " + session.getId());
        User user = new User();
        user.setId(1);
        user.setName(session.getId());
        user.setVersion(1);
        return user;
    }

    @RequestMapping(value = "/session", method = RequestMethod.GET)
    @ApiOperation(value = "get session of current user", response = Map.class)
    public Map<String, Object> getCurrentUserSession(HttpSession session) {
        Map<String, Object> result_session = new HashMap<>();
        Collections.list(session.getAttributeNames()).forEach(key -> result_session.put(key, session.getAttribute(key)));
        result_session.put("id", session.getId());
        result_session.put("creation_time", session.getCreationTime());
        result_session.put("last_access_time", session.getLastAccessedTime());
        result_session.put("is_new", session.isNew());
        return result_session;
    }

    @RequestMapping(value = "/session/add", method = RequestMethod.GET)
    @ApiOperation(value = "set attribute")
    public void setSessionAttribute(HttpSession session) {
        session.setAttribute("added", System.currentTimeMillis());
    }

    @RequestMapping(value = "/session/invalidate", method = RequestMethod.GET)
    @ApiOperation(value = "invalidate session")
    public void invalidateSession(HttpSession session) {
        session.invalidate();
    }

}
