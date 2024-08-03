package frc.robot.commands.visionCommands;

import frc.robot.LimelightHelpers;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem.IntakeSubsystem;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.VisionConstants;;
public class qaram extends Command{

    ArmSubsystem subsystem;
    IntakeSubsystem intake;
    double speed;
    public qaram(ArmSubsystem subsystem, IntakeSubsystem intake){
this.intake = intake;
this.subsystem = subsystem;
addRequirements(subsystem);
    }
    @Override
    public void initialize() 
    {
    }

    @Override
    public void execute() 
    {
        if(LimelightHelpers.getFiducialID("limelight") ==   4){
         System.out.println("test");
            subsystem.move_arm1(0.5);
            subsystem.move_arm2(0.5); 
        }
    }
    
    @Override
    public void end(boolean interrupted) {
       subsystem.move_arm2(0);
        subsystem.move_arm1(0);   
    }

    @Override
    public boolean isFinished() {
        return false;
    }


}
