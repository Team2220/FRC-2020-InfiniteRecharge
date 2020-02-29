package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

/**
 * ShootWithVelocity
 */
public class ShootWithVelocity extends CommandBase {

    private final Shooter shooter;
    private final double velocity;

    public ShootWithVelocity(double velocity, Shooter shooter) {
        this.velocity = velocity;
        this.shooter = shooter;
        addRequirements(shooter);
    }   

    @Override
    public void initialize() {
        shooter.setVelocity(velocity);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        shooter.setPower(0);
    }
}