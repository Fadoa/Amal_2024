// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Intake;
import frc.robot.commands.LimelightSwerve;
import frc.robot.commands.PIDarm;
import frc.robot.commands.PIDarmDown;
import frc.robot.commands.climberCom;
import frc.robot.commands.dpad;
import frc.robot.commands.expel;
import frc.robot.commands.outtake;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem.IntakeSubsystem;
import frc.robot.subsystems.IntakeSubsystem.ShooterSubsystem;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import java.io.File;

import com.pathplanner.lib.auto.NamedCommands;

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
  final CommandPS4Controller armPS5 = new CommandPS5Controller(0);

  private final frc.robot.subsystems.IntakeSubsystem.IntakeSubsystem Intake = new IntakeSubsystem();
  
  private final ShooterSubsystem shooter = new ShooterSubsystem();

  private final ArmSubsystem Arm = new ArmSubsystem(9, 10, 11, 12);
  
  private final Intake intakecom= new Intake(Intake); 
  
  private final outtake outtake = new outtake(Intake);

  private final dpad dpadComUp = new dpad(Arm, 0.95,1);

  private final dpad dpadComdown = new dpad(Arm, -0.64875,-0.75);

  private final climberCom climberComUp = new climberCom(Arm, -0.5);
  
  private final climberCom climberComDown = new climberCom(Arm, 0.5);

  private final expel expeliat = new expel(shooter);

  private final LimelightSwerve retarded = new LimelightSwerve(drivebase);

  private final PIDarm dumb = new PIDarm(Arm, 10) ;
  
  private final PIDarmDown dumbass = new PIDarmDown(Arm, 5);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer()
  {
    // Configure the trigger bindings
    configureBindings();
    NamedCommands.registerCommand("shoot", expeliat);
    NamedCommands.registerCommand("intake", intakecom);
    NamedCommands.registerCommand("outtake", outtake);
    NamedCommands.registerCommand("arm down", dpadComdown);
    NamedCommands.registerCommand("arm up", dpadComUp);
    NamedCommands.registerCommand("", climberComDown);
    NamedCommands.registerCommand("", climberComDown);

    NamedCommands.registerCommand(null, climberComDown);
    // Applies deadbands and inverts controls because joysticks
    // are back-right positive while robot
    // controls are front-left positive
    // left stick controls translation
    // right stick controls the angular velocity of the robot

    Command driveFieldOrientedAnglularVelocity = drivebase.driveCommand(
        () -> MathUtil.applyDeadband(armPS5.getLeftY(), 0.025),
        () -> MathUtil.applyDeadband(armPS5.getLeftX(), 0.025),
        () -> MathUtil.applyDeadband(armPS5.getRightX(), 0.2));

    drivebase.setDefaultCommand( driveFieldOrientedAnglularVelocity);
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
    armPS5.cross().whileTrue(intakecom);
    
    armPS5.square().whileTrue(outtake); 
    
    armPS5.circle().toggleOnTrue(expeliat);
    
    armPS5.povDown().whileTrue(dpadComdown);
    
    armPS5.povUp().whileTrue(dpadComUp);

    armPS5.povLeft().whileTrue(climberComUp);

    armPS5.povRight().whileTrue(climberComDown);

    
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
