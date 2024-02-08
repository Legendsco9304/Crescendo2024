package frc.robot.subSystems;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Shooter extends SubsystemBase {

    private static Shooter instance = null;

    private CANSparkMax rightCollect;

    private CANSparkMax leftCollect;

    // PID of intake angle
    private PIDController VelocityPIDController = new PIDController(
            RobotMap.Intake.PID.p,
            RobotMap.Intake.PID.i,
            RobotMap.Intake.PID.d);

    private Shooter() {
        rightCollect = new CANSparkMax(RobotMap.Intake.ID.rightCollectSlave, MotorType.kBrushless);
        leftCollect = new CANSparkMax(RobotMap.Intake.ID.leftCollectMaster, MotorType.kBrushless);
        // -------------------------------------------------------
        rightCollect.setIdleMode(IdleMode.kCoast);
        leftCollect.setIdleMode(IdleMode.kCoast);
        // ---------------------------------------------------------
        rightCollect.setInverted(true);
        leftCollect.setInverted(false);
        // ---------------------------------------------------------
    }

    public void setPower(double percent) {
        leftCollect.set(percent);
        rightCollect.set(percent);
    }

    public double getVelocity(){
        return rightCollect.getEncoder().getVelocity();
    }

    public static Shooter getInstance() {
        if (instance == null) {
            instance = new Shooter();
        }
        return instance;
    }
}