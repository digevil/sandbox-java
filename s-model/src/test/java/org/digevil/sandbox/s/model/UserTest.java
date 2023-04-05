package org.digevil.sandbox.s.model;

import org.testng.annotations.Test;

import static org.digevil.sandbox.s.model.UserGender.MALE;
import static org.testng.Assert.assertEquals;

public class UserTest {

    @Test
    public void testUser() {
        User user1 = new User();
        user1.setId(100);
        user1.setUuid("abc");
        user1.setName("toni");
        user1.setGender(MALE);

        assertEquals(100, user1.getId());
        assertEquals("abc", user1.getUuid());
        assertEquals("toni", user1.getName());
        assertEquals(MALE, user1.getGender());

        User user2 = new User();
        user2.setId(100);
        user2.setUuid("abc");
        user2.setName("toni");
        user2.setGender(MALE);

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
        assertEquals(user1.toString(), user2.toString());
    }

}