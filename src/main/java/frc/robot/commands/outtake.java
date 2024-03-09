package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class outtake extends Command{

    IntakeSubsystem subsystem;
    double speed;
    outtake(IntakeSubsystem subsystem, double speed){
this.speed = speed;
this.subsystem = subsystem;
addRequirements(subsystem);
    }
    @Override
    public void initialize() 
    {
        System.out.println("intake basladi");
    }

    @Override
    public void execute() 
    {
        subsystem.intake(-0.75);
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("intake bitti"); 
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
