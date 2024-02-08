package frc.robot;

public class RobotMap {

  public static final int XBOX = 0;

  public static final int PIGEON = 9;

  public static final int FL_DRIVE = 13;
  public static final int FL_STEER = 14;
  public static final int FL_CAN_CODER = 5;

  public static final int FR_DRIVE = 9;
  public static final int FR_STEER = 8;
  public static final int FR_CAN_CODER = 4;

  public static final int RL_DRIVE = 17;
  public static final int RL_STEER = 16;
  public static final int RL_CAN_CODER = 6;

  public static final int RR_DRIVE = 2;
  public static final int RR_STEER = 3;
  public static final int RR_CAN_CODER = 3;

  public static class Intake {
    public static class ID {
      public static final int rightCollectSlave = 13;
      public static final int leftCollectMaster = 14;
    }

    public static class PID {
      public static final double p = 0.0015;
      public static final double i = 0;
      public static final double d = 0;
    }

  }

  public static class Feeder {
    public static final int motorID = 0;

  }

}
