// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.RobotOI;

public class Hanger extends SubsystemBase {
  private Spark m_lHangMotor = new Spark(RobotMap.leftHang);
  private Spark m_rHangMotor = new Spark(RobotMap.rightHang);

  /** Creates a new ExampleSubsystem. */
  public Hanger() {
    m_rHangMotor.setInverted(true);
  }

  @Override
  public void periodic() {}

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void spinHang(double speed) {
    m_lHangMotor.set(speed);
    m_rHangMotor.set(speed);
  }

  public void spinLHang(double speed) {
    m_lHangMotor.set(speed);
  }

  public void spinRHang(double speed) {
    m_rHangMotor.set(speed);
  }

  public void stopHang() {
    m_lHangMotor.stopMotor();
    m_rHangMotor.stopMotor();
  }
}
