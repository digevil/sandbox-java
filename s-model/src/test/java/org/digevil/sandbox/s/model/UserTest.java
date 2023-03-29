package org.digevil.sandbox.s.model;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UserTest {

    @Test
    public void testUser() {
        User user1 = new User();
        user1.setId(100);
        user1.setIdHash("abc");
        user1.setName("toni");

        assertEquals(100, user1.getId());
        assertEquals("abc", user1.getIdHash());
        assertEquals("toni", user1.getName());

        User user2 = new User();
        user2.setId(100);
        user2.setIdHash("abc");
        user2.setName("toni");

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
        assertEquals(user1.toString(), user2.toString());
    }

}