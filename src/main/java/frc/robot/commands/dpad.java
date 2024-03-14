package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class dpad extends Command{


  private final ArmSubsystem subsystem;

  private final double speed;


    public dpad(ArmSubsystem subsystem, double speed) {
    this.speed = speed;
    this.subsystem = subsystem;
      addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
System.out.println("dpad çalıştı!");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {


SmartDashboard.putNumber("Arm speed", speed);
subsystem.move_arm(speed);


      
    }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.move_arm(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

}
