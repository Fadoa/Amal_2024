package frc.robot.commands;


import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.LimelightHelpers;


public class LimelightSwerve extends Command{
     
  public static double limelight_aim_proportional()
  {    
    //sabit değer
    double kP = .035;

    
    double targetingAngularVelocity = LimelightHelpers.getTX("limelight") * kP;

    // convert to radians per second for our drive method
    targetingAngularVelocity *= Units.feetToMeters(14.5);

    //invert since tx is positive when the target is to the right of the crosshair
    targetingAngularVelocity *= -1.0;

    return targetingAngularVelocity;
  }

  // simple proportional ranging control with Limelight's "ty" value
  // this works best if your Limelight's mount height and target mount height are different.
  // if your limelight and target are mounted at the same or similar heights, use "ta" (area) for target ranging rather than "ty"
  double limelight_range_proportional()
  {    
    double kP = 0.1;
    double targetingForwardSpeed = LimelightHelpers.getTY("limelight") * kP;
    targetingForwardSpeed *= Units.feetToMeters(14.5);
    targetingForwardSpeed *= -1.0;
    return targetingForwardSpeed;
  }
}