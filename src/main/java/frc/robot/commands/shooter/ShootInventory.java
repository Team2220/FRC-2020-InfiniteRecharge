package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.RobotContainer;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Shooter;
import frc.robot.util.xbox.RumbleForTime;

/**
 * The shoot inventory command takes in a double for amount of time to shoot for
 * and the passed Shooter subsystem. This command group spins up the shooter
 * flywheel for the ramp rate time then feeds balls through the tower into the
 * flywheel for the specified duration.
 */
public class ShootInventory extends ParallelCommandGroup {

    // Minimum difference in goal velocity and actual velocity
    private static final int MIN_DIFF = 10; // TODO test this constant

    /**
     * Command constructor.
     * 
     * @param velocity  The velocity to spin the shooter flywheel at.
     * @param shootTime The amount of time to shoot for once spun up.
     * @param shooter   The passed shooter subsystem.
     */
    public ShootInventory(double velocity, double shootTime, Shooter shooter) {
        // Add all of the commands to the command group
        addCommands(
            new ShootWithVelocity(velocity, shooter),
            new SequentialCommandGroup(
                new WaitUntilCommand(() -> {
                    return (shooter.getFlywheelVelocity() - velocity) < MIN_DIFF;
                }),
                new RunTower(ShooterConstants.TOWER_POWER, shooter),
                new WaitCommand(shootTime)),
            new RumbleForTime(RobotContainer.getDriverController(), 0.25, 0.5)); // TODO check to see if this is reasonable rumble
    }
}