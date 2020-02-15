package frc.robot.commands.hopper;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hopper;

/**
 * HopperWithJoystick
 */
public class HopperWithButton extends CommandBase {

    private final Hopper hopper;

    private static final double HOPPER_POWER = 0.5;

    public HopperWithButton(Hopper hopper) {

        this.hopper = hopper;
        addRequirements(hopper);
    }

    @Override
    public void initialize() {
        super.initialize();
        hopper.set(HOPPER_POWER);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        hopper.set(0);
    }

}