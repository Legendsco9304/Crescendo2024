package frc.robot.commands.feeder;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subSystems.Feeder;
import frc.robot.subSystems.Shooter;

public class FeedByPower extends Command {

    private double power;

    public FeedByPower(double power) {
        this.power = power;
        addRequirements(Feeder.getInstance());
    }

    @Override
    public void initialize() {
        Shooter.getInstance().setPower(power);
    }

    @Override
    public void end(boolean interrupted) {
        Shooter.getInstance().setPower(0);

    }
}
