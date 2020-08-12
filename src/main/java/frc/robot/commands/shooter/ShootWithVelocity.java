package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Shooter;

/**
 * Very simple command to spin the flywheel at a specified velocity.
 */
public class ShootWithVelocity extends CommandBase {

    // Instance members
    private final Shooter shooter;
    private final double velocity;

    /**
     * Command constructor.
     * 
     * @param velocity The velocity to spin the flywheel at.
     * @param shooter  The passed shooter subsytem
     */
    public ShootWithVelocity(double velocity, Shooter shooter) {
        this.velocity = velocity;
        this.shooter = shooter;
        // addRequirements(shooter);
    }

    /**
     * Instant command execution
     */
    @Override
    public void initialize() {
        shooter.setFlywheelVelocity(velocity);
    }

    /**
     * Runs when the command is finished.
     */
    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        // Set the shooter subsystem to stop the flywheel
        shooter.setFlywheelPower(0);
    }
}