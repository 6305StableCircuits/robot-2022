// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class Drivetrain extends SubsystemBase {
  /** Initialize the Drivetrain. */
  
  // Initialize our Spark Max Motor Controllers 
  private CANSparkMax m_leftMotor = new CANSparkMax(RobotMap.leftDriveMotor, MotorType.kBrushless);
  private CANSparkMax m_rightMotor = new CANSparkMax(RobotMap.rightDriveMotor, MotorType.kBrushless);
  
  // Initialize our Drivetrain
  private DifferentialDrive m_drive = new DifferentialDrive(m_leftMotor, m_rightMotor);

  public Drivetrain() {
    m_leftMotor.restoreFactoryDefaults();
    m_rightMotor.restoreFactoryDefaults();
  }

  public void drive(double xaxisSpeed, double yaxisSpeed) {
    m_drive.tankDrive(xaxisSpeed, yaxisSpeed);
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
