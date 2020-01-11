package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.TwilightHorse;

/**
 * XboxDrive
 */
public class XboxDrive extends CommandBase {

    private final TwilightHorse drivetrain;

    public XboxDrive(TwilightHorse drivetrain) {
        this.drivetrain = drivetrain;
    }

    @Override
    public void execute() {
        double power = RobotContainer.getDriverController().getY(Hand.kLeft);
        double spin = RobotContainer.getDriverController().getX(Hand.kRight);
        drivetrain.drive(power, spin);
    }

}