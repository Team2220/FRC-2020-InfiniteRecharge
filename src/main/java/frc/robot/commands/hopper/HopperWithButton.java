package frc.robot.commands.hopper;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hopper;

/**
 * HopperWithJoystick
 */
public class HopperWithButton extends CommandBase {

    private final Hopper hopper;

    public HopperWithButton(Hopper hopper) {
        this.hopper = hopper;
        addRequirements(hopper);
    }

    @Override
    public void initialize() {
        super.initialize();
        hopper.set(0.5);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        hopper.set(0);
    }

}