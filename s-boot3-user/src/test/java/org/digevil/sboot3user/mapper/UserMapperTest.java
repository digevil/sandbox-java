package org.digevil.sboot3user.mapper;

import jakarta.annotation.Resource;
import org.digevil.sandbox.s.model.User;
import org.digevil.sboot3user.SBoot3UserApplicationTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.digevil.sandbox.s.model.UserGender.FEMALE;
import static org.digevil.sandbox.s.model.UserGender.MALE;
import static org.testng.Assert.*;

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
        assertEquals(MALE, user.getGender());
    }

    @Test
    public void testFindByUuid() {
        User user = userMapper.findByUuid("5e00c197-eab3-11ea-ae6a-0242ac110003");

        assertNotNull(user);
        assertEquals(1, user.getId());
        assertEquals("5e00c197-eab3-11ea-ae6a-0242ac110003", user.getUuid());
        assertEquals("toni", user.getName());
        assertEquals(MALE, user.getGender());
    }

    @Test
    public void testInsert() {
        User user1 = new User().setUuid(UUID.randomUUID().toString()).setName("testGirl").setGender(FEMALE);

        assertNull(user1.getId());

        userMapper.insert(user1);

        assertTrue(user1.getId() > 0);
        User user2 = userMapper.findById(user1.getId());

        assertNotNull(user2);
        assertEquals(user1, user2);
    }
}