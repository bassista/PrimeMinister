/*
 * Prime, just checkin ...
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 */
package com.techcasita.ask;

/* Doing the math.*/
public class Prime {
    public static long isPrime(final long n) {
        if (n < 2) {
            return 0;
        }
        if (n % 2 == 0) {
            return 2;
        }
        final long top = (long) Math.sqrt(n) + 1;
        for (long i = 3; i < top; i += 2) {
            if (n % i == 0) {
                return i;
            }
        }
        return n;
    }
}
