package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotOI {
    // Initialize Joysticks
    public static Joystick m_leftStick = new Joystick(0);
    public static Joystick m_rightStick = new Joystick(1);

    public static XboxController xboxController = new XboxController(2);

    public static JoystickButton xboxAButton = new JoystickButton(xboxController, Button.kA.value);
    public static JoystickButton xboxBButton = new JoystickButton(xboxController, Button.kB.value);
}
