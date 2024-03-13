package frc.robot.commands;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Constants;


public class mastahpiece {

    static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    static NetworkTableEntry ty = table.getEntry("ty");
    static double targetOffsetAngle_Vertical = ty.getDouble(0.0);

    // how many degrees back is your limelight rotated from perfectly vertical?
    static double limelightMountAngleDegrees = 25.0; 

    static double limelightLensHeightInches = Units.metersToInches(0.325); 

    // distance from the target to the floor
    static double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
    static double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);

    //calculate distance
    
    public static double Meter(double goalHeightInches){
    double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches) / Math.tan(angleToGoalRadians);
    
        return distanceFromLimelightToGoalInches;
    }

    public static double AngleGiver(double targetHeight){
    double y = Units.inchesToMeters(targetHeight) - Constants.VisionConstants.robotheight;
    double x = Meter(targetHeight);
    double alpha = Math.atan(y/x);

    return alpha;

    }
    public static double AngleToMotor(double angle){

        return 0.0;

    }

    public void test(double r, double angle){
        // 2 * pi * r = 360
        // alpha / 360 = h / 2 * pi * r
        //Cim rpm 5310
    
        
        double cevre = Math.PI * 2 * r;
        double ratio = angle /360;
        double gearRatio = 48 * 15;
        double yay_h = cevre * ratio;
        

    }

}
