package frc.robot.commands.armCommand;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.math.controller.PIDController;

import frc.robot.subsystems.ArmSubsystem;
public class calculate extends Command {
    
  private final ArmSubsystem subsystem;
private final PIDController pid = new PIDController(0.05, 0, 0.001);



    public calculate(ArmSubsystem subsystem) {
    
    this.subsystem = subsystem;
      addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
System.out.println("dpad çalıştı!");
pid.setSetpoint(350);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
subsystem.move_arm1(pid.calculate(subsystem.getEncoder1()));
subsystem.move_arm2(pid.calculate(subsystem.getEncoder1()));
}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
subsystem.move_arm1(0);
subsystem.move_arm2(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

}
