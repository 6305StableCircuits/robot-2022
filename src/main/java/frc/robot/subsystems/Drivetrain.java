// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class Drivetrain extends SubsystemBase {
  /** Initialize the Drivetrain. */
  
  // Initialize our Spark Max Motor Controllers 
  private CANSparkMax m_frontLeftMotor = new CANSparkMax(RobotMap.frontLeftDriveMotor, MotorType.kBrushless);
  private CANSparkMax m_backLeftMotor = new CANSparkMax(RobotMap.backLeftDriveMotor, MotorType.kBrushless);
  private CANSparkMax m_frontRightMotor = new CANSparkMax(RobotMap.frontRightDriveMotor, MotorType.kBrushless);
  private CANSparkMax m_backRightMotor = new CANSparkMax(RobotMap.backRightDriveMotor, MotorType.kBrushless);
  
  // Get Encoders from Each Motor Controller
  private RelativeEncoder m_frontLeftEncoder = m_frontLeftMotor.getEncoder();
  private RelativeEncoder m_backLeftEncoder = m_backLeftMotor.getEncoder();
  private RelativeEncoder m_frontRightEncoder = m_frontRightMotor.getEncoder();
  private RelativeEncoder m_backRightEncoder = m_backRightMotor.getEncoder();

  // Get PID Controllers from Front Motors
  private SparkMaxPIDController m_leftController = m_frontLeftMotor.getPIDController();
  private SparkMaxPIDController m_rightController = m_frontRightMotor.getPIDController();

  // Create motor groups for drive.
  private MotorControllerGroup m_leftMotorGroup = new MotorControllerGroup(m_frontLeftMotor, m_backLeftMotor);
  private MotorControllerGroup m_rightMotorGroup = new MotorControllerGroup(m_frontRightMotor, m_backRightMotor);

  // Initialize our Drivetrain
  private DifferentialDrive m_drive = new DifferentialDrive(m_leftMotorGroup, m_rightMotorGroup);

  // PID Gains
  private double kP = 5e-5; 
  private double kI = 1e-6;
  private double kD = 0; 
  private double kIZone = 0; 
  private double kF = 0.000156;
  
  private double minVel;
  private double maxVel = 2000; // rpm
  private double minAcc;
  private double maxAcc = 1500;
  private double allowedErr;
  
  public Drivetrain() {
    m_frontLeftMotor.restoreFactoryDefaults();
    m_backLeftMotor.restoreFactoryDefaults();
    m_frontRightMotor.restoreFactoryDefaults();
    m_backRightMotor.restoreFactoryDefaults();
    
    m_backLeftMotor.follow(m_frontLeftMotor, true);
    m_backRightMotor.follow(m_frontRightMotor, true);

    m_frontLeftEncoder.setPositionConversionFactor(1/5.95);
    m_frontRightEncoder.setPositionConversionFactor(1/5.95);

    m_leftController.setP(kP);
    m_rightController.setP(kP);
    m_leftController.setI(kI);
    m_rightController.setI(kI);
    m_leftController.setD(kD);
    m_rightController.setD(kD);
    m_leftController.setFF(kF);
    m_rightController.setFF(kF);
    m_leftController.setIZone(kIZone);
    m_rightController.setIZone(kIZone);

    m_leftController.setSmartMotionMaxVelocity(maxVel, 0);
    m_leftController.setSmartMotionMinOutputVelocity(minVel, 0);
    m_leftController.setSmartMotionMaxAccel(maxAcc, 0);
    m_leftController.setSmartMotionAllowedClosedLoopError(allowedErr, 0);
    m_rightController.setSmartMotionMaxVelocity(maxVel, 0);
    m_rightController.setSmartMotionMinOutputVelocity(minVel, 0);
    m_rightController.setSmartMotionMaxAccel(maxAcc, 0);
    m_rightController.setSmartMotionAllowedClosedLoopError(allowedErr, 0);

    m_leftController.setOutputRange(-1, 1);
    m_rightController.setOutputRange(-1, 1);
    
    m_leftMotorGroup.setInverted(true);
  }

  public void drive(double xaxisSpeed, double yaxisSpeed) {
    m_drive.tankDrive(xaxisSpeed, yaxisSpeed);
  }

  public void SmartMotion(double setLeftPoint, double setRightPoint) {
    m_leftController.setReference(setLeftPoint, CANSparkMax.ControlType.kSmartMotion);
    // m_leftController.setReference(distance, ControlType.kPosition);
    m_rightController.setReference(setRightPoint, CANSparkMax.ControlType.kSmartMotion);
    // m_rightController.setReference(distance, ControlType.kPosition);
  }

  public double getFrontLeftMotorPosition() {
    return m_frontLeftEncoder.getPosition();
  }

  public double getFrontRightMotorPosition() {
    return m_frontRightEncoder.getPosition();
  }

  public double getBackLeftMotorPosition() {
    return m_backLeftEncoder.getPosition();
  }

  public double getBackRightMotorPosition() {
    return m_backRightEncoder.getPosition();
  }

  public double getFrontLeftMotorVelocity() {
    return m_frontLeftEncoder.getVelocity();
  }

  public double getFrontRightMotorVelocity() {
    return m_frontRightEncoder.getVelocity();
  }

  public double getBackLeftMotorVelocity() {
    return m_backLeftEncoder.getVelocity();
  }

  public double getBackRightMotorVelocity() {
    return m_backRightEncoder.getVelocity();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // Dump Encoder Data to Shuffleboard
    SmartDashboard.putNumber("Front Left Encoder Position", m_frontLeftEncoder.getPosition());
    SmartDashboard.putNumber("Front Left Encoder Velocity", m_frontLeftEncoder.getVelocity());
    SmartDashboard.putNumber("Front Left Motor Temperature", m_frontLeftMotor.getMotorTemperature());
    SmartDashboard.putNumber("Back Left Encoder Position", m_backLeftEncoder.getPosition());
    SmartDashboard.putNumber("Back Left Encoder Velocity", m_backLeftEncoder.getVelocity());
    SmartDashboard.putNumber("Back Left Motor Temperature", m_backLeftMotor.getMotorTemperature());
    SmartDashboard.putNumber("Front Right Encoder Position", m_frontRightEncoder.getPosition());
    SmartDashboard.putNumber("Front Right Encoder Velocity", m_frontRightEncoder.getVelocity());
    SmartDashboard.putNumber("Front Right Motor Temperature", m_frontRightMotor.getMotorTemperature());
    SmartDashboard.putNumber("Back Right Encoder Position", m_backRightEncoder.getPosition());
    SmartDashboard.putNumber("Back Right Encoder Velocity", m_backRightEncoder.getVelocity());
    SmartDashboard.putNumber("Back Right Motor Temperature", m_backRightMotor.getMotorTemperature());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}