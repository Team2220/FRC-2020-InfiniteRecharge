package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class RunShooter extends CommandBase{
    private Shooter shooter;
    private double speed;

    public RunShooter(double speed, Shooter shooter) {
        this.shooter = shooter;
        this.speed = speed;
    }

    @Override
    public void initialize() {
        shooter.setFlywheelPower(speed);

    }

    @Override
    public void end(boolean interrupted) {
        shooter.setFlywheelPower(0);
    }
}