package org.digevil.sboot3user.service;

import jakarta.annotation.Resource;
import org.digevil.sandbox.s.model.User;
import org.digevil.sboot3user.SBoot3UserApplicationTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserServiceTest extends SBoot3UserApplicationTests {

    @Resource
    UserService userService;

    @Test
    public void testFindById() {
        User user = userService.findById(1);
        assertNotNull(user);
    }

    @Test
    public void testFindByUuid() {
    }

    @Test
    public void testInsert() {
    }
}