package frc.robot.commands.armCommand;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class reft extends Command{

    private final Timer timer = new  Timer();
  private final ArmSubsystem subsystem;

  private double current_time;
  private double middle_time;


  private double middle_delta_encoder;
  
  private double kP_value;
  private double delta_encoder;
  private double p_value ;

  private double kD_Value;

  private double D_Value ;
  private double delta_ender ;



    public reft(ArmSubsystem subsystem) {
        
  this.subsystem = subsystem;

  
  addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
System.out.println("reft çalıştı!");

delta_encoder = subsystem.getEncoder2() - subsystem.getEncoder1();
timer.start();
current_time = timer.get();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    subsystem.ArmMotor1.set(1);
    middle_time = current_time;
    middle_delta_encoder = delta_encoder;

    current_time = timer.get();

    delta_encoder = subsystem.getEncoder1() - subsystem.getEncoder2();
    
    delta_ender= (delta_encoder-middle_delta_encoder)/(current_time-middle_time);
    
    p_value = delta_encoder *kP_value;
    
    D_Value = Math.abs(delta_ender)*kD_Value;
    
    double final_value = p_value + D_Value;

    if(final_value > 1){
        final_value =1;
    }else if (final_value < -1){
        final_value= -1;
    }


    System.out.println(current_time);
    System.out.println(delta_encoder);

    System.out.println(delta_ender);
    
    System.out.println(p_value);
    
    System.out.println(D_Value);
    
    System.out.println(final_value);
    subsystem.ArmMotor1.set(1);
    subsystem.ArmMotor2.set(-final_value);
}


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.move_arm1(0);
    subsystem.move_arm2(0);
    subsystem.resetEncoder();
    timer.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }







}

