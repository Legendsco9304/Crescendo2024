package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subSystems.Shooter;

public class RollByPower extends Command {

    private double power;

    public RollByPower(double power) {
        this.power = power;
        addRequirements(Shooter.getInstance());
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
