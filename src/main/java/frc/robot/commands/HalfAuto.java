package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

/** A complex auto command that drives forward, releases a hatch, and then drives backward. */
public class HalfAuto extends SequentialCommandGroup {
  public HalfAuto(Drivetrain drive, Shooter shoot, Intake intake) {
    addCommands(
        new AutoShoot(shoot, 0.8),
        new AutoDrive(drive, -0.55, -0.55, 0.75),
        new ParallelCommandGroup(
          new AutoDrive(drive, -0.55, -0.55, 0.75),
          new AutoIntake(shoot, intake, 0.5)
        ),
        new AutoIntake(shoot, intake, 0.25),
        new ParallelCommandGroup(
          new AutoDrive(drive, 0.55, 0.55, 1.9),
          new AutoReverse(shoot, 0.4)
        ),
        new AutoShoot(shoot, 1),
        new AutoDrive(drive, -0.55, -0.55, 2)
    );
  }
}
