package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Shooter;

/**
 * StopShooter
 */
public class StopShooter extends InstantCommand {

    private final Shooter shooter;

    public StopShooter(Shooter shooter) {
        this.shooter = shooter;
    }

    @Override
    public void execute() {
        shooter.setFlywheelPower(0);
    }
}