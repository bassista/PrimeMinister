/*
 * PrimeTest
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 */
package com.techcasita.ask;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrimeTest {

    @Test
    public void testPrime() {
        assertEquals(0, Prime.isPrime(-1)); // negative numbers are not a prime number
        assertEquals(0, Prime.isPrime(0)); // zero is not a prime number
        assertEquals(0, Prime.isPrime(1)); // one is not a prime number
        assertEquals(2, Prime.isPrime(2)); // 3 is a prime number
        assertEquals(3, Prime.isPrime(3)); // 3 is a prime number
        assertEquals(2, Prime.isPrime(4)); // 4 is divisible by 2
        assertEquals(5, Prime.isPrime(5)); // 5 is a prime number
        assertEquals(2, Prime.isPrime(6)); // 6 is divisible by 2
        assertEquals(7, Prime.isPrime(7)); // 7 is a prime number
        assertEquals(2, Prime.isPrime(8)); // 8 is divisible by 2
        assertEquals(3, Prime.isPrime(9)); // 9 is divisible by 3
        assertEquals(2, Prime.isPrime(10)); // 10 is divisible by 2
        // ..
    }
}
