package frc.robot.commands;


import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class expel extends Command {
  private final IntakeSubsystem subsystem;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public expel(IntakeSubsystem subsystem) {
    this.subsystem = subsystem;
    
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("expel başladi!!!");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("expel power", 0.2);
    subsystem.shoot(0.2);
    // TODO buradaki katsayı yzaklıkla birlikte değişen uzaklığın karesiyle hesaplanacak ,
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
System.out.println("expel bitti!!!!");    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
