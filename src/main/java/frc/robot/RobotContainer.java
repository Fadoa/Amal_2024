// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Intake;
import frc.robot.commands.climberCom;
import frc.robot.commands.dpad;
import frc.robot.commands.expel;
import frc.robot.commands.outtake;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem.IntakeSubsystem;
import frc.robot.subsystems.IntakeSubsystem.ShooterSubsystem;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import java.io.File;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a "declarative" paradigm, very
 * little robot logic should actually be handled in the {@link Robot} periodic methods (other than the scheduler calls).
 * Instead, the structure of the robot (including subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer
{

  // The robot's subsystems and commands are defined here...
  private final SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),
                                                                         "swerve"));

  // Replace with CommandPS4Controller or CommandJoystick if needed
  final CommandPS4Controller driverXbox = new CommandPS4Controller(0);

  private final frc.robot.subsystems.IntakeSubsystem.IntakeSubsystem Intake = new IntakeSubsystem();
  
  private final ShooterSubsystem shooter = new ShooterSubsystem();

  private final ArmSubsystem Arm = new ArmSubsystem(9, 10, 11, 12);
  
  private final Intake intakecom= new Intake(Intake); 
  
  private final outtake outtake = new outtake(Intake);

  private final dpad dpadComUp = new dpad(Arm, 0.65);

  private final dpad dpadComdown = new dpad(Arm, -0.45
  );

  private final climberCom climberComUp = new climberCom(Arm, -0.5);
  
  
  private final climberCom climberComDown = new climberCom(Arm, 0.5);

  private final expel expeliat = new expel(Intake,shooter);

  private final SequentialCommandGroup test = new SequentialCommandGroup();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer()
  {
    // Configure the trigger bindings
    configureBindings();

    
    // Applies deadbands and inverts controls because joysticks
    // are back-right positive while robot
    // controls are front-left positive
    // left stick controls translation
    // right stick controls the angular velocity of the robot

    //TODO stop when the left joystick button is pressed.
    //TODO dpad tuşlarına basıldığında x derece hareket edecek.
    Command driveFieldOrientedAnglularVelocity = drivebase.driveCommand(
        () -> -MathUtil.applyDeadband(driverXbox.getLeftY(), 0.7),
        () -> -MathUtil.applyDeadband(driverXbox.getLeftX(), 0.7),
        () -> driverXbox.getRightX() * 0.7);

    Command driveFieldOrientedDirectAngleSim = drivebase.simDriveCommand(
        () -> MathUtil.applyDeadband(driverXbox.getLeftX(), OperatorConstants.LEFT_Y_DEADBAND),
        () -> MathUtil.applyDeadband(driverXbox.getLeftY(), OperatorConstants.LEFT_X_DEADBAND),
        () -> driverXbox.getRawAxis(2));

    drivebase.setDefaultCommand(
        !RobotBase.isSimulation() ? driveFieldOrientedAnglularVelocity : driveFieldOrientedDirectAngleSim);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary predicate, or via the
   * named factories in {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller PS4}
   * controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight joysticks}.
   */
  private void configureBindings()
  {
    driverXbox.cross().whileTrue(intakecom);
    
    driverXbox.square().whileTrue(outtake); 
    
    driverXbox.circle().toggleOnTrue(expeliat);

    driverXbox.povDown().whileTrue(dpadComdown);
    
    driverXbox.povUp().whileTrue(dpadComUp);

    driverXbox.povLeft().whileTrue(climberComDown);
    driverXbox.povRight().whileTrue(climberComUp);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand()
  {
    // An example command will be run in autonomous
    return drivebase.getAutonomousCommand("New Auto");
  }

  public void setDriveMode()
  {
    //drivebase.setDefaultCommand();
  }

  public void setMotorBrake(boolean brake)
  {
    drivebase.setMotorBrake(false);
  }
}
