// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.RobotMap;
import frc.robot.RobotOI;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class Unyeet extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Shooter m_subsystem;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Unyeet(Shooter subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_subsystem.spinBottomShooter(-RobotMap.bottomShooterSpeed / 2);
    m_subsystem.spinTopShooter(-RobotMap.bottomShooterSpeed / 2);
    RobotOI.xboxController.setRumble(RumbleType.kLeftRumble, 0.1);
    RobotOI.xboxController.setRumble(RumbleType.kRightRumble, 0.1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.stopBottomShooter();
    m_subsystem.stopTopShooter();
    RobotOI.xboxController.setRumble(RumbleType.kLeftRumble, 0);
    RobotOI.xboxController.setRumble(RumbleType.kRightRumble, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
