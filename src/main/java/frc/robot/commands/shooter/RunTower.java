package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Tower;

/**
 * Very simple command to run the tower motors at a specified percent output, or
 * "power."
 */
public class RunTower extends CommandBase {

    // Instance members
    private final Tower tower;
    private final double power;

    /**
     * Command constructor.
     * 
     * @param power   The percent output to run the tower motors at.
     * @param tower The passed shooter subsytem.
     */
    public RunTower(double power, Tower tower) {
        this.power = power;
        this.tower = tower;
        // addRequirements(shooter);
    }

    /**
     * Instant command execution.
     */
    @Override
    public void initialize() {
        // Sets the tower to the specified power
        tower.setTowerPower(power);
    }

    /**
     * Runs when the command finishes.
     */
    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        // Sets the tower to stop moving
        tower.setTowerPower(0);
    }
}