package frc.robot.commands.armCommand;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class reft extends Command{

    private final Timer timer = new  Timer();
  private final ArmSubsystem subsystem;

  private final double speed1;

  private final PIDController pid = new PIDController(0.001
  , 0 , 0);

private double idk ;
    public reft(ArmSubsystem subsystem, double speed1) {
    this.speed1 = speed1;
    this.subsystem = subsystem;
    pid.setSetpoint(90);//derece birim

      addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
System.out.println("reft çalıştı!");
timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
if(subsystem.getEncoder2() <= 2520){
    subsystem.move_arm(0.05);

}else{
    subsystem.move_arm(0);
    
    timer.stop();
    System.out.println(timer.get());
    
}
    
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
  }}

