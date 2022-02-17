// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class Drivetrain extends SubsystemBase {
  /** Initialize the Drivetrain. */
  
  // Initialize our Spark Max Motor Controllers 
  private CANSparkMax m_topLeftMotor = new CANSparkMax(RobotMap.topLeftDriveMotor, MotorType.kBrushless);
  private CANSparkMax m_bottomLeftMotor = new CANSparkMax(RobotMap.bottomLeftDriveMotor, MotorType.kBrushless);
  private CANSparkMax m_topRightMotor = new CANSparkMax(RobotMap.topRightDriveMotor, MotorType.kBrushless);
  private CANSparkMax m_bottomRightMotor = new CANSparkMax(RobotMap.bottomRightDriveMotor, MotorType.kBrushless);
  
  // Create motor groups for drive.
  private MotorControllerGroup m_leftMotorGroup = new MotorControllerGroup(m_topLeftMotor, m_bottomLeftMotor);
  private MotorControllerGroup m_rightMotorGroup = new MotorControllerGroup(m_topRightMotor, m_bottomRightMotor);

  // Initialize our Drivetrain
  private DifferentialDrive m_drive = new DifferentialDrive(m_leftMotorGroup, m_rightMotorGroup);

  public Drivetrain() {
    m_topLeftMotor.restoreFactoryDefaults();
    m_bottomLeftMotor.restoreFactoryDefaults();
    m_topRightMotor.restoreFactoryDefaults();
    m_bottomRightMotor.restoreFactoryDefaults();

    m_rightMotorGroup.setInverted(true);
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
