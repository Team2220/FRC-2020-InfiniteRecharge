package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

/**
 * RunTower
 */
public class RunTower extends CommandBase {

    private final Shooter shooter;
    private final double power;

    public RunTower(double power, Shooter shooter) {
        this.power = power;
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.setTowerPower(power);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        shooter.setTowerPower(0);
    }
}