package frc.swerve;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwerveModule {
    
    private CANSparkMax driveSpark;
    private SparkPIDController drivePID;
    private RelativeEncoder driveEncoder;
    private CANSparkFlex steeringSpark;
    private SparkPIDController steeringPID;
    private RelativeEncoder steeringEncoder;
    private double zeroAngle;
    private double displayShit1 = 0;
    private double displayShit2 = 0;
    private double displayShit3 = 0;
    

    private CANcoder absEncoder;

    public double getDisplayShit3() {
        return displayShit3;
    }
    public double getDisplayShit1() { return displayShit1; }

    public SwerveModule(CANSparkMax driveSpark, CANSparkFlex steeringSpark, CANcoder absEncoder, double zeroAngle) {
        this.driveSpark = driveSpark;
        this.steeringSpark = steeringSpark;
        this.absEncoder = absEncoder;

        drivePID = driveSpark.getPIDController();
        steeringPID = steeringSpark.getPIDController();

        driveEncoder = driveSpark.getEncoder();
        steeringEncoder = steeringSpark.getEncoder();

        this.zeroAngle = zeroAngle;

        drivePID.setP(SwerveConstants.kPdrive);
        drivePID.setI(SwerveConstants.kIdrive);
        drivePID.setD(SwerveConstants.kDdrive);
        drivePID.setFF(SwerveConstants.kFdrive);
        drivePID.setIZone(SwerveConstants.iZoneDrive);
        drivePID.setOutputRange(-1, 1);

        steeringPID.setP(SwerveConstants.kPsteer);
        steeringPID.setI(SwerveConstants.kIsteer);
        steeringPID.setD(SwerveConstants.kDsteer);
        steeringPID.setFF(SwerveConstants.kFsteer);
        steeringPID.setIZone(SwerveConstants.iZoneSteer);
        steeringPID.setOutputRange(-1, 1);

      //  steeringEncoder.setPosition(getAngle() / 360 * Constants.STEERING_RATIO);

        calibrateSteering(getAbsPos() - zeroAngle);
        driveEncoder.setPosition(0);
    }

    public void calibrateSteering(double angle) {
        steeringEncoder.setPosition(angle / 360 / SwerveConstants.STEERING_RATIO);

    }


    public void setDesiredState(SwerveModuleState desiredState) {
        SwerveModuleState state = optimize(desiredState, Rotation2d.fromDegrees(getAngleSteering()));
        double steerSetpoint = state.angle.getDegrees() / 360 / SwerveConstants.STEERING_RATIO;

        double driveSetpoint = metersPerSecondToRPM(state.speedMetersPerSecond);
        displayShit1 = driveSetpoint;
        steeringPID.setReference(steerSetpoint, CANSparkMax.ControlType.kPosition);
        if (state.speedMetersPerSecond == 0) {
            driveSpark.set(0);
        } else {
            drivePID.setReference(driveSetpoint, CANSparkMax.ControlType.kVelocity);
        }
    }

    public SwerveModuleState optimize(SwerveModuleState desiredState, Rotation2d currentAngle) {
        double targetAngle = placeInAppropriate0To360Scope(currentAngle.getDegrees(), desiredState.angle.getDegrees());
        double targetSpeed = desiredState.speedMetersPerSecond;
        double delta = targetAngle - currentAngle.getDegrees();
        if (Math.abs(delta) > 90){
            targetSpeed = -targetSpeed;
            targetAngle = delta > 90 ? (targetAngle -= 180) : (targetAngle += 180);
        }
        return new SwerveModuleState(targetSpeed, Rotation2d.fromDegrees(targetAngle));
    }

    private  double placeInAppropriate0To360Scope(double scopeReference, double newAngle) {
        double lowerBound;
        double upperBound;
        double lowerOffset = scopeReference % 360;
        if (lowerOffset >= 0) {
            lowerBound = scopeReference - lowerOffset;
            upperBound = scopeReference + (360 - lowerOffset);
        } else {
            upperBound = scopeReference - lowerOffset;
            lowerBound = scopeReference - (360 + lowerOffset);
        }
        while (newAngle < lowerBound) {
            newAngle += 360;
        }
        while (newAngle > upperBound) {
            newAngle -= 360;
        }
        if (newAngle - scopeReference > 180) {
            newAngle -= 360;
        } else if (newAngle - scopeReference < -180) {
            newAngle += 360;
        }
        return newAngle;
    }

    public double metersPerSecondToRPM(double velocity) {
        return ((velocity * 60) / (SwerveConstants.WHEEL_RADIUS * Math.PI * 2)) / SwerveConstants.DRIVE_RATIO;
    }

    public double getAngleSteering() {
        return getSteeringPos() * 360;
    }

    public double getSteeringPos() {
        return steeringEncoder.getPosition() * SwerveConstants.STEERING_RATIO;
    }

    public double getAngle() {
        
       
        return getSteeringPos() * 360;
    }

    public double getAbsAngle() {
        return absEncoder.getPosition().getValue();
    }

    public double getDriveRot() {
        return driveEncoder.getPosition() * SwerveConstants.DRIVE_RATIO;
    }

    public double getSteeringVelocity() {
        return steeringEncoder.getVelocity();
    }

    public double getDriveVelocity() {
        return driveEncoder.getVelocity();
    }

    public double getAbsPos() {
        return absEncoder.getAbsolutePosition().getValue();
    }

    public void resetEncoders() {
      //  steeringEncoder.setPosition(0);
        driveEncoder.setPosition(0);
    }

    public void testWheel() {
        driveSpark.set(0.2);
    }

    public void stopWheel() {
        driveSpark.stopMotor();
    }

    public void displayShit() {
        SmartDashboard.putNumber("pre", displayShit1);
        SmartDashboard.putNumber("post", displayShit2);
    }

    public double getDriveRPM() {
        return driveEncoder.getVelocity();
    }

    public void resetCanCoder() {
        absEncoder.setPosition(0);

    }
    
}
