package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class dpad extends Command{


  private final ArmSubsystem subsystem;

  private final double speed1;

  private final double speed2;


    public dpad(ArmSubsystem subsystem, double speed1, double speed2) {
    this.speed1 = speed1;
    this.subsystem = subsystem;
    this.speed2 = speed2;
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

subsystem.move_arm(speed1,speed2);}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.move_arm(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

}
