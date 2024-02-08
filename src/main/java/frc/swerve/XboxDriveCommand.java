package frc.swerve;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class XboxDriveCommand extends Command{
    
    private final static double SPEED_LIMIT = 1;
    private final static double DEAD_ZONE = 0.05;
    private final static double RAD_TO_DEGREES = 6.28;

    private Swerve swerve;
    private XboxController xbox;
    private boolean fieldRelative;

    public XboxDriveCommand(Swerve swerve, XboxController xbox, boolean fieldRelative) {
        this.swerve = swerve;
        this.xbox = xbox;
        this.fieldRelative = fieldRelative;

        addRequirements(swerve);
    }

    @Override
    public void initialize() {
        swerve.resetYaw();
        swerve.resetEncoders();
    }


    @Override
    public void execute() {

        double speedX = deadZone(applyConstraint(xbox.getLeftX(),
                -SPEED_LIMIT, SPEED_LIMIT)) * SwerveConstants.MAX_SPEED_METERS_PER_SECOND / 2;
        double speedY = deadZone(applyConstraint(xbox.getLeftY(),
                -SPEED_LIMIT, SPEED_LIMIT)) * SwerveConstants.MAX_SPEED_METERS_PER_SECOND / 2;
        double rot = -deadZone(applyConstraint(xbox.getRightX(),
                -SPEED_LIMIT, SPEED_LIMIT)) * RAD_TO_DEGREES;
        SmartDashboard.putNumber("Speed x", speedY);
        SmartDashboard.putNumber("Speed y", speedX);
        SmartDashboard.putNumber("rot", rot);



        swerve.drive(speedY, speedX, rot, fieldRelative);


    }

    @Override
    public void end(boolean wasInterrupted) {
        swerve.stop();
    }

    private double deadZone(double speed) {
        if((speed > -DEAD_ZONE) && (speed < DEAD_ZONE)) {
            return 0;
        } else {
            return speed;
        }
    }

    private static double applyConstraint(double value, double minValue, double maxValue) {
        if (value < minValue) {
            return minValue;
        } else if (value > maxValue) {
            return maxValue;
        } else {
            return value;
        }
    }
}
