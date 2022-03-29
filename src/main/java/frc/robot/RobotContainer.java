// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.FullAuto;
import frc.robot.commands.FullAutoDelayShoot;
import frc.robot.commands.FullAutoIntake;
import frc.robot.commands.FullAutoShoot;
import frc.robot.commands.HalfAuto;
import frc.robot.commands.HalfAutoDelayShoot;
import frc.robot.commands.HangAdjustL;
import frc.robot.commands.HangAdjustR;
import frc.robot.commands.HangDown;
import frc.robot.commands.HangUp;
import frc.robot.commands.Shoot;
import frc.robot.commands.Yoink;
import frc.robot.commands.TankDrive;
import frc.robot.commands.Unyeet;
import frc.robot.commands.Yaw;
import frc.robot.commands.Yeet;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Hanger;
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
  private final Hanger m_hanger = new Hanger();

  // private final AutoDrive m_autoDrive = new AutoDrive(m_drivetrain);
  // private final AutoShoot m_autoShoot = new AutoShoot(m_shooter);
  private final FullAuto m_fullAuto = new FullAuto(m_drivetrain, m_shooter, m_intake);
  private final HalfAuto m_halfAuto = new HalfAuto(m_drivetrain, m_shooter, m_intake);
  private final FullAutoShoot m_fullAutoShoot = new FullAutoShoot(m_drivetrain, m_shooter, m_intake);
  private final FullAutoIntake m_fullAutoIntake = new FullAutoIntake(m_drivetrain, m_shooter, m_intake);
  private final FullAutoDelayShoot m_fullAutoDelayShoot = new FullAutoDelayShoot(m_drivetrain, m_shooter, m_intake);
  private final HalfAutoDelayShoot m_halfAutoDelayShoot = new HalfAutoDelayShoot(m_drivetrain, m_shooter, m_intake);
  private final TankDrive m_tankDrive = new TankDrive(m_drivetrain);
  private final Yaw m_yaw = new Yaw(m_drivetrain);

  private final HangUp m_hangUp = new HangUp(m_hanger);
  private final HangAdjustL m_hangLUp = new HangAdjustL(m_hanger, RobotMap.hangUpSpeed);
  private final HangAdjustR m_hangRUp = new HangAdjustR(m_hanger, RobotMap.hangUpSpeed);
  private final HangDown m_hangDown = new HangDown(m_hanger);
  private final HangAdjustL m_hangLDown = new HangAdjustL(m_hanger, RobotMap.hangDownSpeed);
  private final HangAdjustR m_hangRDown = new HangAdjustR(m_hanger, RobotMap.hangDownSpeed);

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
    RobotOI.rightStickTrigger.whileActiveContinuous(m_yaw);
    
    RobotOI.xboxUpButton.whileActiveContinuous(m_hangUp);
    RobotOI.xboxUpLeftButton.whileActiveContinuous(m_hangLUp);
    RobotOI.xboxUpRightButton.whileActiveContinuous(m_hangRUp);
    RobotOI.xboxDownButton.whileActiveContinuous(m_hangDown);
    RobotOI.xboxDownLeftButton.whileActiveContinuous(m_hangLDown);
    RobotOI.xboxDownRightButton.whileActiveContinuous(m_hangRDown);
  }

  private void addAutonomous() {
    // m_chooser.addOption("Drive Forwards", m_autoDrive);
    // m_chooser.addOption("Drive Forwards", m_autoShoot);
    m_chooser.addOption("Full Auto Shoot+Intake+Shoot", m_fullAuto);
    m_chooser.addOption("Half Auto Shoot+Intake+Shoot", m_halfAuto);
    m_chooser.addOption("Full Auto Shoot", m_fullAutoShoot);
    m_chooser.addOption("Full Auto Intake", m_fullAutoIntake);
    m_chooser.addOption("Full Auto Delay Shoot", m_fullAutoDelayShoot);
    m_chooser.addOption("Half Auto Delay Shoot", m_halfAutoDelayShoot);
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
