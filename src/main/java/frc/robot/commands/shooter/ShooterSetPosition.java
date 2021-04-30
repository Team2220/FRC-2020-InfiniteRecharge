package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Shooter;
public class ShooterSetPosition extends InstantCommand{
    private final Shooter.HoodPosition position;
    private final Shooter shooter;

    public ShooterSetPosition(Shooter.HoodPosition position, Shooter shooter) {
        this.position = position;
        this.shooter = shooter;
        addRequirements();
    }

    @Override
    public void execute() {
        shooter.setHoodPosition(position);
     }
}