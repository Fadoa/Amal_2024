package frc.robot.subsystems.IntakeSubsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
    
    private final CANSparkMax ShooterMotor1;
    private final CANSparkMax ShooterMotor2;

    public ShooterSubsystem(){
    ShooterMotor1 = new CANSparkMax(14, MotorType.kBrushless);
    ShooterMotor2 = new CANSparkMax(13, MotorType.kBrushless);
    }

    public void shoot(double power){
        ShooterMotor1.set(power);
        ShooterMotor2.set(-power);
    }
}
