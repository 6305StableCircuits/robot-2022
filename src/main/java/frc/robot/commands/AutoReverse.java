// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class AutoReverse extends CommandBase {
  private final Shooter m_shooter;
  private final Timer m_timer = new Timer();
  private double endTime = 0;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutoReverse(Shooter shootSubsystem, double time) {
    m_shooter = shootSubsystem;
    endTime = time;
    // Use addRequirements() here to declare subsystem dependencies.
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
    if (m_timer.get() <= endTime) {
      m_shooter.spinTopShooter(-0.4);
      m_shooter.spinBottomShooter(-0.2);
    }
    else {
      m_shooter.stopTopShooter();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.stopTopShooter(); 
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
