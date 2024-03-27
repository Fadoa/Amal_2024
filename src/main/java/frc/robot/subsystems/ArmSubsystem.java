package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    public  final CANSparkMax ArmMotor1;
    public final CANSparkMax ArmMotor2;

    private final CANSparkMax ClimberMotor1;
    private final CANSparkMax ClimberMotor2;

    private final RelativeEncoder encoder1;
    private final RelativeEncoder encoder2;

    
    public final double EncoderRatio = 360/6;
    public ArmSubsystem(int ArmMotor1ID, int ArmMotor2ID,double ClimberID1,double ClimberID2){
        ArmMotor1 = new CANSparkMax(9,MotorType.kBrushless);

        ArmMotor2 = new CANSparkMax(10,MotorType.kBrushless);


        encoder1 = ArmMotor1.getEncoder() ;
        encoder2 = ArmMotor2.getEncoder() ;

        encoder1.setPosition(0);
        encoder2.setPosition(0);
        ClimberMotor1 = new CANSparkMax(11, MotorType.kBrushed);
        
        
        ClimberMotor2 = new CANSparkMax(12, MotorType.kBrushed);
    ClimberMotor2.setInverted(true);
    ClimberMotor1.setInverted(true);;

    
    }
    
    public void move_arm1(double power1){

        ArmMotor1.set(power1);
        
    }

    
    public void move_arm2(double power1){

        ArmMotor2.set(power1);
        
    }
    
    public void resetEncoder(){
        encoder1.setPosition(0);
        encoder2.setPosition(0);
    }
    public void Climbfunct(double power){
        
        ClimberMotor2.set(power);
    } 
    public void fuckoff(double power){
        ClimberMotor1.set(power);
    }
    public double getEncoder1(){
        return encoder1.getPosition();
    }

    public double getEncoder2(){
        return encoder2.getPosition();

    }
    
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Arm1 encoder ticks", encoder1.getPosition());
        SmartDashboard.putNumber("arm 2 encodern ticks", encoder2.getPosition());
        SmartDashboard.putNumber("Arm angle",getEncoder1()/EncoderRatio);
        SmartDashboard.putNumber("arm 1 voltage", ArmMotor1.getBusVoltage());
        SmartDashboard.putNumber("arm 2 voltage", ArmMotor2.getBusVoltage());
        SmartDashboard.putNumber("arm 1 amperage", ArmMotor1.getOutputCurrent());
        SmartDashboard.putNumber("arm 2 amperag", ArmMotor2.getOutputCurrent());
        
    }
    

}
