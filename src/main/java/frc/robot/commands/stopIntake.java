package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class stopIntake extends Command {
    private IntakeSubsystem subsystem;

    public stopIntake(IntakeSubsystem subsystem){
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
            subsystem.intake(0);
        }
    
        @Override
        public void end(boolean interrupted) {
            System.out.println("intake bitti"); 
        }
    
        @Override
        public boolean isFinished() {
            return true;
        }
}