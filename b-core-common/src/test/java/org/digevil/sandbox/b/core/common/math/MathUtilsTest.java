package org.digevil.sandbox.b.core.common.math;

import org.testng.annotations.Test;

import static org.digevil.sandbox.b.core.common.math.MathUtils.gcd;
import static org.testng.Assert.*;

public class MathUtilsTest {

    @Test
    public void testMathUtils_gcd(){
        assertEquals(1, gcd(1,2));
        assertEquals(2, gcd(2,2));
        assertEquals(2, gcd(4,2));
        assertEquals(3, gcd(3,9));
        assertEquals(3, gcd(6,9));
        assertEquals(1, gcd(3,5));
    }
}