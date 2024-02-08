package frc.robot;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.feeder.FeedByPower;
import frc.robot.commands.multiSystem.Collect;
import frc.robot.commands.multiSystem.Shoot;
import frc.robot.commands.shooter.RollByPower;
import frc.robot.subSystems.Shooter;

public class IO {

    private static IO instance;
    private final CommandXboxController yaelController;

    private IO() {
        yaelController = new CommandXboxController(0);
        
        yaelController.x().whileTrue(new Shoot(2000));
        yaelController.y().whileTrue(new Collect());

    }

    public static IO getInstance() {
        if (instance == null) {
            instance = new IO();
        }
        return instance;
    }

}
