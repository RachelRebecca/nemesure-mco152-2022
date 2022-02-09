public class ProjectileMotion
{
    public static void main(String[] args)
    {
        Projectile p = new Projectile(43, 52);

        System.out.println(p.getXAtTime(7));
        System.out.println(p.getYAtTime(7));

    }
}
