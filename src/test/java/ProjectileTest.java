import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectileTest
{
    @Test // little markers in code that automated processes searching through your code can find
    public void getXAtTime()
    {
        // given, set up the variables and conditions for your test

        Projectile projectile = new Projectile(17, 52);

        // when, where the test actually occurs

        double x  = projectile.getXAtTime(3);

        // then, where you actually check the results

        assertEquals(31.3987, x, .0001);

    }

    @Test
    public void getYAtTime()
    {
        // given

        Projectile projectile = new Projectile(17, 52);

        // then

        double y = projectile.getYAtTime(3);

        // when

        assertEquals(-3.9115, y, .0001);
    }
}