package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    private final VictorSPX ArmMotor1;
    private final VictorSPX ArmMotor2;

    private final CANSparkMax ClimberMotor1;
    private final CANSparkMax ClimberMotor2;

    private final Encoder encoder;

    private final double EncoderRatio =360/7 ;
    public ArmSubsystem(int ArmMotor1ID, int ArmMotor2ID,double ClimberID1,double ClimberID2){
        ArmMotor1 = new VictorSPX(9);

        ArmMotor2 = new VictorSPX(10);

        ClimberMotor1 = new CANSparkMax(11, MotorType.kBrushed);

        ClimberMotor2 = new CANSparkMax(12, MotorType.kBrushed);
    
        encoder = new Encoder(0,1);
    }
    
    public void move_arm(double power1,double power2){
        ArmMotor1.set(VictorSPXControlMode.PercentOutput, power1);
        ArmMotor2.set(VictorSPXControlMode.PercentOutput, power2);
    }
    public void Climbfunct(double power){
        ClimberMotor1.set(power);
        ClimberMotor2.set(power);
    } 

    

}
