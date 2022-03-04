// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveForward;
import frc.robot.commands.DriveForwardShoot;
import frc.robot.commands.Shoot;
import frc.robot.commands.Yoink;
import frc.robot.commands.TankDrive;
import frc.robot.commands.Unyeet;
import frc.robot.commands.Yeet;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Intake m_intake = new Intake();
  private final Shooter m_shooter = new Shooter();

  private final DriveForward m_driveForward = new DriveForward(m_drivetrain);
  private final DriveForwardShoot m_driveForwardShoot = new DriveForwardShoot(m_drivetrain, m_shooter);
  private final TankDrive m_tankDrive = new TankDrive(m_drivetrain);

  private final Yoink m_Yoink = new Yoink(m_intake, m_shooter);
  private final Shoot m_shoot = new Shoot(m_shooter);
  private final Yeet m_Yeet = new Yeet(m_shooter);
  private final Unyeet m_Unyeet = new Unyeet(m_shooter);

  private final SendableChooser<Command> m_chooser = new SendableChooser<>();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    addAutonomous();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    m_drivetrain.setDefaultCommand(m_tankDrive);

    RobotOI.xboxAButton.whileActiveContinuous(m_Yoink);
    RobotOI.xboxBButton.whileActiveContinuous(m_shoot);
    RobotOI.xboxLBButton.whileActiveContinuous(m_Yeet);
    RobotOI.xboxRBButton.whileActiveContinuous(m_Unyeet);
  }

  private void addAutonomous() {
    m_chooser.addOption("Drive Forwards", m_driveForward);
    m_chooser.addOption("Drive Forwards & Shoot", m_driveForwardShoot);
    SmartDashboard.putData(m_chooser);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
}
