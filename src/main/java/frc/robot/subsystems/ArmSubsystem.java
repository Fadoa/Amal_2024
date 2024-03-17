package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    private final VictorSPX ArmMotor1;
    private final VictorSPX ArmMotor2;

    private final CANSparkMax ClimberMotor1;
    private final CANSparkMax ClimberMotor2;
    
    public ArmSubsystem(int ArmMotor1ID, int ArmMotor2ID,double ClimberID1,double ClimberID2){
        ArmMotor1 = new VictorSPX(11);

        ArmMotor2 = new VictorSPX(12);

        ClimberMotor1 = new CANSparkMax(9, MotorType.kBrushed);

        ClimberMotor2 = new CANSparkMax(10, MotorType.kBrushed);
    }
    
    public void move_arm(double power){
        ArmMotor1.set(VictorSPXControlMode.PercentOutput, power);
        ArmMotor2.set(VictorSPXControlMode.PercentOutput, power);
    }
    public void Climbfunct(double power){
        ClimberMotor1.set(power);
        ClimberMotor2.set(power);
    } 

    

}
