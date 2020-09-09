package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Tower;
import frc.robot.subsystems.Shooter.ShooterDesiredState;
import frc.robot.subsystems.Shooter.ShooterSystemState;
import frc.robot.subsystems.Tower.TowerState;

/**
 * ShootAndDynamicFeed
 */
public class ShootAndDynamicFeed extends CommandBase {

    // Instance members
    private Shooter shooter;
    private Tower tower;

    public ShootAndDynamicFeed(Shooter shooter, Tower tower) {
        this.shooter = shooter;
        this.tower = tower;
        addRequirements(shooter, tower);
    }

    @Override
    public void initialize() {
        System.out.println("ShootAndDynamicFeed.initialize()");
        shooter.setState(ShooterDesiredState.SHOOT);
    }

    @Override
    public void execute() {
        if (shooter.getSystemState() == ShooterSystemState.SPINNING_READY) {
            tower.setState(TowerState.RUNNING);
        } else {
            tower.setState(TowerState.IDLE);
        }
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("ShootAndDynamicFeed.end("+ interrupted +")");
        shooter.setState(ShooterDesiredState.IDLE);
        tower.setState(TowerState.IDLE);
        

    }
}