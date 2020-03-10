package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
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

    public ShootInventory(double shootTime, Shooter shooter) {
        addCommands(
            new ShootWithVelocity(ShooterConstants.SHOT_VELOCITY, shooter),
            new SequentialCommandGroup(
                new WaitCommand(ShooterConstants.RAMP_RATE),
                new RunTower(ShooterConstants.TOWER_POWER, shooter),
                new WaitCommand(shootTime)),
            new RumbleForTime(RobotContainer.getDriverController(), 0.25, 0.5)); // TODO test this
    }
}