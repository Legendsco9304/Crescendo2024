package frc.robot.commands.multiSystem;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.feeder.FeedByPower;
import frc.robot.commands.shooter.RollByPower;

public class Collect extends ParallelCommandGroup {

    public Collect() {
        super(new FeedByPower(-0.5), new RollByPower(-0.5));

    }
}