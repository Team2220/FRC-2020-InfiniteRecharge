package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.shuffleboard.DemoTab;
import frc.robot.subsystems.Shooter;

public class ShootWithShuffleboard extends CommandBase {
    private Shooter shooter;
    private DemoTab demoTab;

    public ShootWithShuffleboard(DemoTab demoTab, Shooter shooter) {
        this.shooter = shooter;
        this.demoTab = demoTab;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.setFlywheelPower(demoTab.getShooterSpeedModifier());

    }

    @Override
    public void end(boolean interrupted) {
        shooter.setFlywheelPower(0);
    }
}