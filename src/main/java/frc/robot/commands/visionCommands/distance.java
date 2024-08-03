package frc.robot.commands.visionCommands;

import frc.robot.LimelightHelpers;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Constants.VisionConstants;;
public class distance {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ty = table.getEntry("ty");
    double offset = ty.getDouble(0.0);
    
    
    public float Uzaklik(float hedefBoy){
        double yukseklik = hedefBoy - VisionConstants.robotheight;
        
return 1;
    }

}
