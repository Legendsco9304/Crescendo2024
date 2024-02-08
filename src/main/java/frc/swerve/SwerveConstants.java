package frc.swerve;

public class SwerveConstants {
      // PID
      public final static double kPdrive = 1e-6;
      public final static double kIdrive = 0;
      public static final double kDdrive = 0;
      public static final double kFdrive = 0.006;
      public static final double iZoneDrive = 300;

      public final static double kPsteer = 0.3;
      public final static double kIsteer = 0;
      public static final double kDsteer = 0;
      public static final double kFsteer = 0;
      public static final double iZoneSteer = 0;

      // RATIOS
      public final static double STEERING_RATIO = 1 / 12.8;
      public final static double DRIVE_RATIO = 1 / 6.75;

      // DISTANCES
      public static final double WHEEL_RADIUS = 0.5005;
      public static final double DISTANCE_FROM_CENTER = 0.37;

      // SPEED
      public static final double MAX_SPEED_METERS_PER_SECOND = 4.4196;

      // ZERO ANGLES
      public static final double FL_ZERO_ANGLE = 272.725;
      public static final double FR_ZERO_ANGLE = 240.117;
      public static final double RL_ZERO_ANGLE = 215.332;
      public static final double RR_ZERO_ANGLE = 202.324;

}
