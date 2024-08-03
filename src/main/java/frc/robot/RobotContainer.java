// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.armCommand.calculate;
import frc.robot.commands.armCommand.dpad;
import frc.robot.commands.climberCommand.climberCom;
import frc.robot.commands.intakeCommand.Intake;
import frc.robot.commands.intakeCommand.expel;
import frc.robot.commands.intakeCommand.outtake;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem.IntakeSubsystem;
import frc.robot.subsystems.IntakeSubsystem.ShooterSubsystem;
import frc.robot.commands.visionCommands.qaram;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import java.io.File;
import frc.robot.commands.armCommand.calculate;

import com.pathplanner.lib.auto.NamedCommands;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a "declarative" paradigm, very
 * littobot logic should actually be handled in the {@link Robot} periodic methods (other than the scheduler calls).
 * Instead, the structure of the robot (including subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer
{

  // The robot's subsystems and commands are defined here...
  private final SwerveSubsystem drivebase;

  // Replace with CommandPS4Controller or CommandJoystick if needed
  final CommandXboxController armPS5 = new CommandXboxController(0);

  private final frc.robot.subsystems.IntakeSubsystem.IntakeSubsystem Intake ;

private final calculate odlekKerem;

  private final ShooterSubsystem shooter ;

  private final ArmSubsystem Arm;
  
  private final Intake intakecom; 
  
  private final outtake outtake;

  private final dpad dpadComUp;

  private final dpad dpadComdown ;

  private final climberCom climberComUp ;
  
  private final climberCom climberComDown ;


  private final qaram gor;

  private final expel expeliat ;
  

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer()
  {
    shooter = new ShooterSubsystem();
drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),
                                                                         "swerve"));
Intake = new IntakeSubsystem();

Arm = new ArmSubsystem(9, 10, 11, 12);

intakecom= new Intake(Intake);

outtake = new outtake(Intake);

 dpadComUp = new dpad(Arm, 0.5);

 dpadComdown = new dpad(Arm, -0.5);

 climberComUp = new climberCom(Arm, 1);
  
 climberComDown = new climberCom(Arm, -1);
 expeliat = new expel(shooter);
 odlekKerem = new calculate(Arm);
 gor = new qaram(Arm, Intake); 
;

    NamedCommands.registerCommand("shoot", expeliat);
    NamedCommands.registerCommand("intake", intakecom);
    NamedCommands.registerCommand("outtake", outtake);
    NamedCommands.registerCommand("arm down", dpadComdown);
    NamedCommands.registerCommand("arm up", dpadComUp);
    NamedCommands.registerCommand("", climberComDown);
    NamedCommands.registerCommand("", climberComDown);

    // Configure the trigger bindings
    configureBindings();
    
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
   * {@link CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandXboxController PS4}
   * controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight joysticks}.
   */
  private void configureBindings()
  {
    armPS5.povUp().whileTrue(dpadComUp);
armPS5.povDown().whileTrue(dpadComdown);
    armPS5.b().whileTrue(intakecom);
    armPS5.a().whileTrue(outtake);

    armPS5.y().whileTrue(odlekKerem);
    armPS5.x().toggleOnTrue(expeliat);
    
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the c;ommand to run in autonomous
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
