// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class DriveForwardShoot extends CommandBase {
  private final Drivetrain m_drivetrain;
  private final Shooter m_shooter;
  private final Timer m_timer = new Timer();

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveForwardShoot(Drivetrain driveSubsystem, Shooter shootSubsystem) {
    m_drivetrain = driveSubsystem;
    m_shooter = shootSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drivetrain);
    addRequirements(m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_timer.get() <= 0.3) {
      m_shooter.spinBottomShooter(.75);
      m_shooter.spinTopShooter(.75);
      
    }
    else if (m_timer.get() == 0.3) {
      m_shooter.stopBottomShooter();
      m_shooter.stopTopShooter();
    }
    else {
      m_drivetrain.drive(-0.55, -0.55);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.stopBottomShooter();
    m_shooter.stopTopShooter(); 
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (m_timer.get() >= 2.3) {
      m_timer.stop();
      m_timer.reset();
      return true;
    }
    else {
      return false;
    }
  }
}
