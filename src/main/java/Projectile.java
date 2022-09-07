public record Projectile(double initVelocity, double angleInDegrees) // in Java 18, can use record rather than class
    // record has a defined hash code, toString() and equals()
    // for record-type classes, don't put logic inside constructor
    // shorthand way of writing getters for private variables passed through the constructor:
    /*

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

     */
{
    private final static double GRAVITY = 9.8;

    public double getXAtTime(double time)
    {
        return initVelocity * time * Math.cos(Math.toRadians(angleInDegrees));
    }

    public double getYAtTime(double time)
    {
        return initVelocity * time * Math.sin(Math.toRadians(angleInDegrees)) - (.5 * GRAVITY * (time * time));
    }
}
