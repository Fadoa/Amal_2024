package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class PIDarm extends Command{
    private final ArmSubsystem subsystem;
    private final PIDController pid;
    double sets = 0;
    public PIDarm(ArmSubsystem subsystem,double setpoint){
this.subsystem = subsystem;
pid = new PIDController(0.1, 0, 0);
pid.setSetpoint(setpoint);
addRequirements(subsystem);
    }
@Override
public void initialize() {
    System.out.println("ElevatorPIDCmd started!");
    pid.reset();
}

@Override
public void execute() {

    double speed = pid.calculate(sets);
    subsystem.move_arm(speed, speed);
    sets += 0.1;
if (sets >= 10){
    subsystem.move_arm(0, 0);
}

    
}

@Override
public void end(boolean interrupted) {
    subsystem.move_arm(0, 0);
    sets = 0;
}
@Override
public boolean isFinished() {
    return false;
}
}
