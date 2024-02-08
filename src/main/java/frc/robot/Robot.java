// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.swerve.SwerveConstants;
import frc.swerve.Swerve;
import frc.swerve.XboxDriveCommand;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  private Swerve swerve;
  private XboxController xbox;

  private final double DEAD_ZONE = 0.05;
  private final double RAD_TO_DEGREES = 6.28;
  private final static double SPEED_LIMIT = 1;


  @Override
  public void robotInit() {
    CommandScheduler.getInstance().enable();
    swerve = SystemFactory.createSwerve();
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
   
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
 double speedX = deadZone(applyConstraint(xbox.getLeftX(),
                -SPEED_LIMIT, SPEED_LIMIT)) * SwerveConstants.MAX_SPEED_METERS_PER_SECOND / 2;
        double speedY = deadZone(applyConstraint(xbox.getLeftY(),
                -SPEED_LIMIT, SPEED_LIMIT)) * SwerveConstants.MAX_SPEED_METERS_PER_SECOND / 2;
        double rot = -deadZone(applyConstraint(xbox.getRightX(),
                -SPEED_LIMIT, SPEED_LIMIT)) * RAD_TO_DEGREES;
        SmartDashboard.putNumber("Speed x", speedY);
        SmartDashboard.putNumber("Speed y", speedX);
        SmartDashboard.putNumber("rot", rot);



        swerve.drive(speedY, speedX, rot, false);  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}

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
