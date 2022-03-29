// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class AutoDrive extends CommandBase {
  private final Drivetrain m_drivetrain;
  private final Timer m_timer = new Timer();
  private double endTime = 0;
  private double leftSpeed = 0;
  private double rightSpeed = 0;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutoDrive(Drivetrain subsystem, double lspeed, double rspeed, double time) {
    endTime = time;
    leftSpeed = lspeed;
    rightSpeed = rspeed;
    m_drivetrain = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivetrain.drive(leftSpeed, rightSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.drive(0.1,0.1);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (m_timer.get() >= endTime) {
      m_timer.stop();
      m_timer.reset();
      return true;
    }
    else {
      return false;
    }
  }
}
