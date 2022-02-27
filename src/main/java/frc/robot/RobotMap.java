package frc.robot;

/**
 * A map of all of the ports that sensors and actuators are wired into.
 * This allows for quick changes to be made to accomodate for wiring
 * changes on the fly.
 */
public class RobotMap {
    /** Ports */
    // The front left drive motor ID
    public static int frontLeftDriveMotor = 12;
    // The back left drive motor ID
    public static int backLeftDriveMotor = 13;
    // The front right drive motor ID
    public static int frontRightDriveMotor = 3;
    // The back right drive motor ID
    public static int backRightDriveMotor = 2;
    // The top right shooter motor ID
    public static int topRightShooter = 8;
    // The bottom right shooter motor ID
    public static int bottomRightShooter = 9;
    // The top left shooter motor ID
    public static int topLeftShooter = 0;
    // The bottom left shooter motor ID
    public static int bottomLeftShooter = 1;
    // The intake motor ID
    public static int intake = 7;

    /** Speeds */
    // Intake Motor Speed
    public static double intakeSpeed = 0.6;
    // Lower Shooter Motor Speed
    public static double bottomShooterSpeed = 0.8;
    // Lower Shooter When All Run
    public static double bottomShooterFullSpeed = 1;
    // Upper Shooter Motor Speed
    public static double topShooterSpeed = 1;

}
