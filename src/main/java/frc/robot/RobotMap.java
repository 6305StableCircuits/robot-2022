package frc.robot;

/**
 * A map of all of the ports that sensors and actuators are wired into.
 * This allows for quick changes to be made to accomodate for wiring
 * changes on the fly.
 */
public class RobotMap {
    // The left drive motor port
    public static int leftDriveMotor = 1;
    // The right drive motor port
    public static int rightDriveMotor = 2;

    // Default PID coefficients
    public static double kP = 0.1; 
    public static double kI = 1e-4;
    public static double kD = 1; 
    public static double kIz = 0; 
    public static double kFF = 0; 
    public static double kMaxOutput = 1; 
    public static double kMinOutput = -1;
}
