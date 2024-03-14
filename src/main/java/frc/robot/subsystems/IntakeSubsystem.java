package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    private final CANSparkMax IntakeMotor ;
    private final CANSparkMax ShooterMotor_1 ;
    private final CANSparkMax ShooterMotor_2 ;
    public IntakeSubsystem(double intakeID, double shooter1ID, double shooter2ID){
        IntakeMotor = new CANSparkMax(15, MotorType.kBrushless);

        ShooterMotor_1 = new CANSparkMax(14, MotorType.kBrushless);

        ShooterMotor_2 = new CANSparkMax(13, MotorType.kBrushless);
    }

    public void intake(double power){

        IntakeMotor.set(power);
    }

    public void shoot(double power){
    
        ShooterMotor_1.set(power);;
        ShooterMotor_2.set(-power);    
        
    }


}
