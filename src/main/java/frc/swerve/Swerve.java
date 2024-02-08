package frc.swerve;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Swerve extends SubsystemBase {
    private ADXRS450_Gyro gyro;

    private SwerveModule[] swerveModules;
    private SwerveDriveKinematics kinematics;

    public Swerve(SwerveModule[] swerveModules, ADXRS450_Gyro gyro) {
        this.swerveModules = swerveModules;
        double distance = SwerveConstants.DISTANCE_FROM_CENTER;

        this.kinematics = new SwerveDriveKinematics(
                new Translation2d(distance, distance),
                new Translation2d(distance, -distance),
                new Translation2d(-distance, distance),
                new Translation2d(-distance, -distance));

        this.gyro = gyro;


    }

    public void drive(double speedX, double speedY, double rot, boolean fieldRelative) {
        SwerveModuleState[] swerveModuleStates;
        if(fieldRelative) {
            swerveModuleStates = kinematics.toSwerveModuleStates(
                    ChassisSpeeds.fromFieldRelativeSpeeds(speedX, speedY, rot, Rotation2d.fromDegrees(-getAngle())));
        } else {
            swerveModuleStates = kinematics.toSwerveModuleStates(new ChassisSpeeds(speedX, speedY, rot));
        }

        SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, SwerveConstants.MAX_SPEED_METERS_PER_SECOND);

        setDesiredStates(swerveModules, swerveModuleStates);
    }

    public void basePosition() {
        SwerveModuleState[] startingPosition = new SwerveModuleState[] {
                new SwerveModuleState(0, Rotation2d.fromDegrees(0)),
                new SwerveModuleState(0, Rotation2d.fromDegrees(0)),
                new SwerveModuleState(0, Rotation2d.fromDegrees(0)),
                new SwerveModuleState(0, Rotation2d.fromDegrees(0)),
        };
        setDesiredStates(swerveModules, startingPosition);
    }

    public void resetYaw() {
        gyro.reset();
    }

    public double getAngle() {
        return gyro.getAngle();
    }

    private void setDesiredStates(SwerveModule[] swerveModules, SwerveModuleState[] swerveModuleStates) {
        for(int i = 0; i < swerveModules.length; i++) {
            swerveModules[i].setDesiredState(swerveModuleStates[i]);

        }

    }
    public void calibrateSteering() {
        for(int i = 0; i < 4; i++) {
            //swerveModules[i].calibrateSteering();
        }
    }

    public void setDesiredStates(SwerveModuleState[] swerveModulesState) {
        setDesiredStates(swerveModules, swerveModulesState);
    }

    public void resetEncoders() {
        for(int i = 0; i < 4; i++) {
            swerveModules[i].resetEncoders();
        }
    }

    public void resetAllEncoders() {
        for(int i = 0; i < 4; i++) {
            swerveModules[i].resetEncoders();
            swerveModules[i].resetCanCoder();
        }
    }

    public void testWheel(int wheelNum) {
        swerveModules[wheelNum].testWheel();
    }

    public void stopWheel(int wheelNum) {
        swerveModules[wheelNum].stopWheel();
    }

    public double getWheelAngle(int wheelNum) {
        return swerveModules[wheelNum].getAngle();
    }

    public double getDriveRotations(int wheelNum) {
        return swerveModules[wheelNum].getDriveRot();
    }

    public void stop() {
        for (int i = 0; i < 4; i++) {
            swerveModules[i].stopWheel();
        }
    }

    public double getAbsPosition(int wheelNum) {
        return swerveModules[wheelNum].getAbsAngle();
    }

    public SwerveModule getModule(int index) {
        return swerveModules[index];
    }
    
}
