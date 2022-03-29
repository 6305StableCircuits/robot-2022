package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick.ButtonType;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotOI {
    // Initialize Joysticks
    public static Joystick m_leftStick = new Joystick(0);
    public static Joystick m_rightStick = new Joystick(1);

    public static XboxController xboxController = new XboxController(2);

    public static JoystickButton xboxAButton = new JoystickButton(xboxController, Button.kA.value);
    public static JoystickButton xboxBButton = new JoystickButton(xboxController, Button.kB.value);
    public static JoystickButton xboxLBButton = new JoystickButton(xboxController, Button.kLeftBumper.value);
    public static JoystickButton xboxRBButton = new JoystickButton(xboxController, Button.kRightBumper.value);
    public static JoystickButton rightStickTrigger = new JoystickButton(m_rightStick, 1);

    public static Trigger xboxUpButton = new Trigger(() -> {return xboxController.getPOV() == 0;});
    public static Trigger xboxUpLeftButton = new Trigger(() -> {return xboxController.getPOV() == 45;});
    public static Trigger xboxLeftButton = new Trigger(() -> {return xboxController.getPOV() == 90;});
    public static Trigger xboxDownLeftButton = new Trigger(() -> {return xboxController.getPOV() == 135;});
    public static Trigger xboxDownButton = new Trigger(() -> {return xboxController.getPOV() == 180;});
    public static Trigger xboxDownRightButton = new Trigger(() -> {return xboxController.getPOV() == 225;});
    public static Trigger xboxRightButton = new Trigger(() -> {return xboxController.getPOV() == 270;});
    public static Trigger xboxUpRightButton = new Trigger(() -> {return xboxController.getPOV() == 315;});
}
