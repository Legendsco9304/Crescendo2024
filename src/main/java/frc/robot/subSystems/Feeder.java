package frc.robot.subSystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Feeder extends SubsystemBase{

    private static Feeder instance = null; 
 
    private CANSparkMax motor;

    private Feeder() {
        motor = new CANSparkMax(RobotMap.Feeder.motorID, MotorType.kBrushless);
        motor.setIdleMode(IdleMode.kBrake);
        motor.setInverted(false);
    }

    public static Feeder getInstance() {
        if(instance == null) {
            instance = new Feeder();
        }
        return instance;
    }

    public void setPower(double percent) {
        motor.set(percent);
    }
    
}

