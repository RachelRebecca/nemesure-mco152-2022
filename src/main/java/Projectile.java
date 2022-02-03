public class Projectile
{
    private final static double GRAVITY = 9.8;

    private final double initVelocity;
    private final double angleInDegrees;

    public Projectile(double initVelocity, double angleInDegrees)
    {
        this.initVelocity = initVelocity;
        this.angleInDegrees = angleInDegrees;
    }

    public double getInitVelocity()
    {
        return initVelocity;
    }

    public double getAngleInDegrees()
    {
        return angleInDegrees;
    }

    public double getXAtTime(double time)
    {
        return initVelocity * time * Math.cos(Math.toRadians(angleInDegrees));
    }

    public double getYAtTime(double time)
    {
        return initVelocity * time * Math.sin(Math.toRadians(angleInDegrees)) - (.5 * GRAVITY * (time * time));
    }
}
