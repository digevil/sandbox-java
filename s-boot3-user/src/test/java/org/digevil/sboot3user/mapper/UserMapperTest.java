package org.digevil.sboot3user.mapper;

import jakarta.annotation.Resource;
import org.digevil.sandbox.s.model.User;
import org.digevil.sboot3user.SBoot3UserApplicationTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class UserMapperTest extends SBoot3UserApplicationTests {

    @Resource
    UserMapper userMapper;

    @Test
    public void testFindById() {
        assertNotNull(userMapper);
        User user = userMapper.findById(1);

        assertNotNull(user);
        assertEquals(1, user.getId());
        assertNotNull(user.getUuid());
        assertNotNull(user.getName());
    }
}