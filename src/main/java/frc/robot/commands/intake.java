package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.IntakeSubsystem;

public class intake extends Command{

    IntakeSubsystem Intake;
    double speed;
    public intake(IntakeSubsystem Intake){

    this.Intake = Intake;
addRequirements(Intake);
    }
    @Override
    public void initialize() 
    {
        System.out.println("intake basladi");
    }

    @Override
    public void execute() 
    {
        Intake.intake(0.75);
    }

    @Override
    public SequentialCommandGroup andThen(Command... next) {
        Intake.intake(0);
        return super.andThen(next);
    }
    @Override
    public void end(boolean interrupted) {
        Intake.intake(0);
        System.out.println("intake bitti"); 
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
