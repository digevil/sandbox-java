package org.digevil.sboot3user.mapper;

import jakarta.annotation.Resource;
import org.digevil.sandbox.s.model.User;
import org.digevil.sboot3user.SBoot3UserApplicationTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class UserMapperTest extends SBoot3UserApplicationTests {

    @Resource
    UserMapper userMapper;

    @BeforeClass
    public void setup() {
        assertNotNull(userMapper);
    }

    @Test
    public void testFindById() {
        User user = userMapper.findById(1);

        assertNotNull(user);
        assertEquals(1, user.getId());
        assertEquals("5e00c197-eab3-11ea-ae6a-0242ac110003", user.getUuid());
        assertEquals("toni", user.getName());
    }

    @Test
    public void testFindByUuid() {
        User user = userMapper.findByUuid("5e00c197-eab3-11ea-ae6a-0242ac110003");

        assertNotNull(user);
        assertEquals(1, user.getId());
        assertEquals("5e00c197-eab3-11ea-ae6a-0242ac110003", user.getUuid());
        assertEquals("toni", user.getName());
    }
}