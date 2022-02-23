// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Shooter extends SubsystemBase {
  private Spark m_topRightShooterMotor = new Spark(RobotMap.topRightShooter);
  private Spark m_bottomRightShooterMotor = new Spark(RobotMap.bottomRightShooter);
  private Spark m_topLeftShooterMotor = new Spark(RobotMap.topLeftShooter);
  private Spark m_bottomLeftShooterMotor = new Spark(RobotMap.bottomLeftShooter);

  // Create motor groups for shooter.
  private MotorControllerGroup m_topShooterGroup = new MotorControllerGroup(m_topRightShooterMotor, m_topLeftShooterMotor);
  private MotorControllerGroup m_bottomShooterGroup = new MotorControllerGroup(m_bottomRightShooterMotor, m_bottomLeftShooterMotor);

  /** Creates a new Shooter. */
  public Shooter() {
    // Invert right-side motors
    m_topRightShooterMotor.setInverted(true);
    m_bottomRightShooterMotor.setInverted(true);
  }

  public void spinBottomShooter(double speed) {
    m_bottomShooterGroup.set(speed);
  }

  public void stopBottomShooter() {
    this.spinBottomShooter(0);
  }

  public void spinTopShooter(double speed) {
    m_topShooterGroup.set(speed);
  }

  public void stopTopShooter() {
    this.spinTopShooter(0);
  }
  
  public void spinShooter(double bottomSpeed, double topSpeed) {
    this.spinBottomShooter(bottomSpeed);
    this.spinTopShooter(topSpeed);
  }

  public void stopShooter() {
    this.spinBottomShooter(0);
    this.spinTopShooter(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
