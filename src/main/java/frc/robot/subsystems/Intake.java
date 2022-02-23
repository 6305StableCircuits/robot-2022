// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Intake extends SubsystemBase {
  private Spark m_intakeMotor = new Spark(RobotMap.intake);

  /** Creates a new ExampleSubsystem. */
  public Intake() {}

  public void spinIntakeMotor(double speed) {
    m_intakeMotor.set(speed);
  }

  public void stopIntakeMotor() {
    this.spinIntakeMotor(0);
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
