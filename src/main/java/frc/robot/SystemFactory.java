package frc.robot;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import frc.swerve.SwerveConstants;
import frc.swerve.Swerve;
import frc.swerve.SwerveModule;

public class SystemFactory {
    public SystemFactory() {

    }

    public static Swerve createSwerve() {

        /*
         * CANSparkMax driveLeft = new CANSparkMax(RobotMap.FL_DRIVE,
         * CANSparkMaxLowLevel.MotorType.kBrushless);
         * driveLeft.setInverted(true);
         * 
         * CANSparkMax steerLeft = new CANSparkMax(RobotMap.FL_STEER,
         * CANSparkMaxLowLevel.MotorType.kBrushless);
         * //steerLeft.setInverted(true);
         * 
         * SwerveModule leftFrontModule = new SwerveModule(driveLeft, steerLeft, new
         * CANCoder(RobotMap.FL_CAN_CODER));
         * 
         * 
         */

        /*
         * CANSparkMax FLDrive = new CANSparkMax(RobotMap.FL_DRIVE,
         * CANSparkMaxLowLevel.MotorType.kBrushless);
         * CANSparkMax FLSteer = new CANSparkMax(RobotMap.FL_STEER,
         * CANSparkMaxLowLevel.MotorType.kBrushless);
         * 
         * CANCoder FLCanCoder = new CANCoder(RobotMap.FL_CAN_CODER);
         * SwerveModule frontLeftModule = new SwerveModule(FLDrive, FLSteer, FLCanCoder,
         * Constants.FL_ZERO_ANGLE);
         * 
         * 
         * 
         */

        SwerveModule[] swerveModules = new SwerveModule[] {
                createInvertedModule(RobotMap.FL_DRIVE, RobotMap.FL_STEER, RobotMap.FL_CAN_CODER,
                        SwerveConstants.FL_ZERO_ANGLE),
                createInvertedModule(RobotMap.FR_DRIVE, RobotMap.FR_STEER, RobotMap.FR_CAN_CODER,
                        SwerveConstants.FR_ZERO_ANGLE),
                createInvertedModule(RobotMap.RL_DRIVE, RobotMap.RL_STEER, RobotMap.RL_CAN_CODER,
                        SwerveConstants.RL_ZERO_ANGLE),
                createSwerveModule(RobotMap.RR_DRIVE, RobotMap.RR_STEER, RobotMap.RR_CAN_CODER,
                        SwerveConstants.RR_ZERO_ANGLE),
        };

        ADXRS450_Gyro gyro = new ADXRS450_Gyro();
        return new Swerve(swerveModules, gyro);
    }

    private static SwerveModule createSwerveModule(int drive, int steer, int encoder, double zeroAngle) {
        return new SwerveModule(
                new CANSparkMax(drive, MotorType.kBrushless),
                new CANSparkFlex(steer, MotorType.kBrushless),
                new CANcoder(encoder),
                zeroAngle);
    }

    private static SwerveModule createInvertedModule(int drive, int steer, int encoder, double zeroAngle) {
        CANSparkMax spark = new CANSparkMax(drive, MotorType.kBrushless);
        spark.setInverted(true);

        return new SwerveModule(
                spark,
                new CANSparkFlex(steer, MotorType.kBrushless),
                new CANcoder(encoder),
                zeroAngle);
    }
}
