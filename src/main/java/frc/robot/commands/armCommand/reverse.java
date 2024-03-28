package frc.robot.commands.armCommand;

import java.time.Duration;
import java.time.Instant;

import com.ctre.phoenix.time.StopWatch;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class reverse extends Command{

    private final Timer timer = new  Timer();
  private final ArmSubsystem subsystem;

   private double current_time;
  private StopWatch stopWatch;


  private double middle_delta_encoder;
  
  private double kP_value;
  private double delta_encoder;
  private double p_value ;

  private double kD_Value;

  private double D_Value ;
  private double delta_ender ;
  private Instant start;



    public reverse(ArmSubsystem subsystem) {
        
  this.subsystem = subsystem;

  
  addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
System.out.println("reft çalıştı!");

delta_encoder = subsystem.getEncoder1() - subsystem.getEncoder2();
  
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
start = Instant.now();

    subsystem.move_arm1(1);
    

    middle_delta_encoder = delta_encoder;


    delta_encoder = subsystem.getEncoder1() - subsystem.getEncoder2();
    
    delta_ender= (delta_encoder-middle_delta_encoder)/Duration.between(Instant.now(), start).toMinutes();
    System.out.println(delta_encoder-middle_delta_encoder);
    System.out.println();
    System.out.println(delta_ender);

kP_value = -0.1;
kD_Value =0.75;

    p_value = delta_encoder *kP_value;
    
    D_Value = Math.abs(delta_ender)*kD_Value;
    
    double final_value = p_value + D_Value;

System.out.println(p_value);
System.out.println(D_Value);

    

    System.out.println(final_value);

    subsystem.ArmMotor2.set(final_value);
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.move_arm1(0);
    subsystem.move_arm2(0);
    subsystem.resetEncoder();
   
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }







}

