package frc.robot.commands.multiSystem;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.commands.feeder.FeedByPower;
import frc.robot.commands.shooter.RollByPower;
import frc.robot.subSystems.Shooter;

public class Shoot extends ParallelCommandGroup{

    public Shoot(double RPM) {
        super(new RollByPower(1),new SequentialCommandGroup(
                                new WaitUntilCommand(() -> Shooter.getInstance().getVelocity() > RPM),
                                new FeedByPower(1)));
    }
    
}