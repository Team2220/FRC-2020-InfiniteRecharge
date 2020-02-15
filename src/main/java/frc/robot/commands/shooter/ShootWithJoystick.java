package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Shooter;
import frc.robot.util.xbox.XboxController;

/**
 * ShootWithJoystick
 */

public class ShootWithJoystick extends CommandBase {

    private final Shooter shooter;

    public ShootWithJoystick(Shooter shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        XboxController xb = RobotContainer.getManipulatorController();
        double shooterPower = xb.getY(Hand.kLeft);
        shooter.setPower(shooterPower);
        // shooter.setColumnSpeed(xb.getTrigger(Hand.kLeft));
    }
}