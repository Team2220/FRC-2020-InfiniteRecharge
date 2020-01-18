package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.TwilightHorse;
import frc.robot.util.XboxWrapper;

/**
 * Default command for drivetrain. Allows control of the drivetrain with an xbox
 * controller.
 */
public class XboxDrive extends CommandBase {

    // Drivetrain subsystem
    private final TwilightHorse drivetrain;

    // Xbox Controller
    private final XboxWrapper xb;

    /**
     * Constructor for XboxDrive command.
     * 
     * @param drivetrain Drivetrain subsystem.
     */
    public XboxDrive(TwilightHorse drivetrain) {
        // Initialize instance variables
        this.drivetrain = drivetrain;
        xb = RobotContainer.getDriverController();
    }

    /**
     * Command's periodic function. Handles robot movement with driver input.
     */
    @Override
    public void execute() {
        // Forward and reverse power
        double power = mutateJoystick(xb.getY(Hand.kLeft), Hand.kLeft);
        // Turning power
        double spin = mutateJoystick(xb.getX(Hand.kRight), Hand.kRight);
        // Input movement powers into drivetrain controller
        drivetrain.drive(power, spin);
    }

    /**
     * Mutates the output of the joystick to increase driver precision. See this
     * link for function. {@link https://www.desmos.com/calculator/kdwjrhnic7}
     * 
     * @param joyOutput The output of the joystick.
     * @param hand      Which hand side joystick it is.
     * @return The mutated joystick output.
     */
    private double mutateJoystick(double joyOutput, Hand hand) {
        // If input is already 0 don't run function
        if (joyOutput == 0) {
            return 0;
        }
        final double deadzone = xb.getDeadzone(hand);
        // Exponent to curve joystick value TODO test out what curve (if any) is best
        final double exp = 1 + deadzone;
        // Sort of the slope, used several times in function to limit and stretch
        final double slope = 1. / (1 - deadzone);
        // Sign of input
        final double sign = Math.signum(joyOutput);
        // Actual function to mutate values
        double value = Math.abs(slope * joyOutput - sign * (slope - 1));
        value = Math.pow(value, exp);
        value = sign * value;
        return value;
    }
}