package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

/**
 * VelocityShot
 */
public class VelocityShot extends CommandBase {

    private final Shooter shooter;
    private final int velocity;

    public VelocityShot(int velocity, Shooter shooter) {

        addRequirements(shooter);
        this.shooter = shooter;
        this.velocity = velocity;
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