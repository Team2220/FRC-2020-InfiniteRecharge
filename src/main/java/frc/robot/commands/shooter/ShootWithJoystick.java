package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

/**
 * ShootWithJoystick
 */
public class ShootWithJoystick extends CommandBase {

    private final Shooter shooter;
    private final XboxController xb = new XboxController(0);

    public ShootWithJoystick(Shooter shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }
    
    @Override
    public void execute() {
        double shooterPower = xb.getY(Hand.kLeft);
        shooter.setSpeed(shooterPower);
    }
}