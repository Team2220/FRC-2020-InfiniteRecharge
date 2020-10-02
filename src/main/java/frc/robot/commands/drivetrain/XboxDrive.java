package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.util.xbox.XboxController;

/**
 * Default command for drivetrain. Allows control of the drivetrain with an xbox
 * controller.
 * 
 * @author Reece
 */
public class XboxDrive extends CommandBase {

    // Drivetrain subsystem
    private final DriveTrain drivetrain;

    /**
     * Constructor for XboxDrive command.
     * 
     * @param drivetrain Drivetrain subsystem.
     */
    public XboxDrive(DriveTrain drivetrain) {
        // Add drivetrain as a subsystem requirement
        addRequirements(drivetrain);

        // Initialize instance variables
        this.drivetrain = drivetrain;
    }

    /**
     * Runs on initialization of the command.
     */
    @Override
    public void initialize() {
        // Ensures that drivetrain is in default idle behavior
        drivetrain.setIdleBehavior(DrivetrainConstants.IDLE_BEHAVIOR);
    }

    /**
     * Command's periodic function. Handles robot movement with driver input.
     */
    @Override
    public void execute() {
        XboxController driverController = RobotContainer.getDriverController();
        // Forward and reverse power
        double rawPower = driverController.getY(Hand.kLeft);
        double power = mutateJoystick(rawPower, Hand.kLeft);

        // Turning power
        double rawSpin = driverController.getX(Hand.kRight) * -1;
        double spin = mutateJoystick(rawSpin, Hand.kRight);

        // Input movement powers into drivetrain controller
        drivetrain.drive(power, spin);
    }

    /**
     * XboxDrive command never finishes; it is only interruptable.
     */
    @Override
    public boolean isFinished() {
        return false;
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

        // Get controller deadzone
        XboxController driverController = RobotContainer.getDriverController();
        final double deadzone = driverController.getDeadzone(hand);

        // Exponent to curve joystick value TODO test out what curve (if any) is best
        final double exp = 1; // 1 + deadzone

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
