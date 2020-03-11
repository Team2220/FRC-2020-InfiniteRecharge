package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

/**
 * Very simple command to run the tower motors at a specified percent output, or
 * "power."
 */
public class RunTower extends CommandBase {

    // Instance members
    private final Shooter shooter;
    private final double power;

    /**
     * Command constructor.
     * 
     * @param power   The percent output to run the tower motors at.
     * @param shooter The passed shooter subsytem.
     */
    public RunTower(double power, Shooter shooter) {
        this.power = power;
        this.shooter = shooter;
        addRequirements(shooter);
    }

    /**
     * Runs on command initialization.
     */
    @Override
    public void initialize() {
        // Sets the tower to the specified power
        shooter.setTowerPower(power);
    }

    /**
     * Runs when the command finishes.
     */
    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        // Sets the tower to stop moving
        shooter.setTowerPower(0);
    }
}