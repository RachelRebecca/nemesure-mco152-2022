import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ProjectileTest
{
    @Test // little markers in code that automated processes searching through your code can find
    public void getXAtTime()
    {
        // given, set up the variables and conditions for your test

        var projectile = new Projectile(17, 52);
        // you already know it's a projectile, because projectile is about to be defined, you already know that
        // so you can use the var keyword to tell it that projectile is about to be defined
        // with new Projectile()), so compiler should use that definition

        // when, where the test actually occurs

        var x = projectile.getXAtTime(3);
        // you can do this too, replacing double with var,
        // but shouldn't because you don't automatically know that it's a double
        // whereas above it's clear that it's a Projectile

        // then, where you actually check the results


        /*
        JAVA 11 - 18 CHANGE - can also do block Strings
        String message = """
                "Hello to Rachel's projectile program!"
                - Rachel Nemesure
                """;
        System.out.println(message);

        // OTHER JAVA 11 - 18 CHANGE
        List<String> list1 = new ArrayList<>();
        list1.add("one");
        list1.add("two");
        list1.add("three");

        OR:

        List<String> list2 = Arrays.asList("one", "two", "three");

        OR:

        List<String> list3 = List.of("one", "two", "three");

        OR:

        Set<String> set = Set.of("one", "two", "three");

        OR:

        Map<String, String> map = Map.of(
                "k1", "one",
                "k2", "two",
                "k3", "three"
        );

        // FOR LOOP REPLACEMENT:

        for (int i = 0; i < list1.size(); i++)
        {
            String s = list1.get(i);
            System.out.println(s);
        }

        OR:

        for (String s : list1)
        {
            System.out.println(s);
        }

        OR:

        list1.stream().forEach(s -> { System.out.println(s); }); // take the list, grab a stream on the list
        // whatever is inside the parentheses in the foreach, do that for each object, s is the variable for the string,
        // for each s, print the line

        OR:
        list1.forEach(System.out::println); // take System.out, call the println method on it,
        // passing in each element on the list

        OR:

        set.forEach(System.out::println);

        map.forEach((k, v) -> System.out.println(k + ":" + v));
         */

        assertEquals(31.3987, x, .0001);

    }

    @Test
    public void getYAtTime()
    {
        // given

        Projectile projectile = new Projectile(17, 52);

        // when

        double y = projectile.getYAtTime(3);

        // then

        assertEquals(-3.9115, y, .0001);
    }
}