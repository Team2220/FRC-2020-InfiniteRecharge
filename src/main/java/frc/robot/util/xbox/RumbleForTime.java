package frc.robot.util.xbox;

import edu.wpi.first.wpilibj2.command.WaitCommand;

/**
 * Command to rumble an xbox controller for a certain amount of time and given
 * intensity. Should be added to command groups to handle rumbling during
 * important robot actions.
 * 
 * @author Reece
 */
public class RumbleForTime extends WaitCommand {

    // Xbox controller
    private final XboxController xb;

    // Intensity at which to rumble
    private final double intensity;

    /**
     * Constructor for the Rumble For Time command.
     * 
     * @param xb        The xbox controller to rumble.
     * @param seconds   How long to rumble for.
     * @param intensity The intensity at which to rumble.
     */
    public RumbleForTime(XboxController xb, double seconds, double intensity) {
        // Satisfy super constructor
        super(seconds);

        // Add the xbox controller as a subsystem requirement for this command
        addRequirements(xb);

        // Initialize instance variables
        this.xb = xb;
        this.intensity = intensity;
    }

    /**
     * Start the controller rumble.
     */
    @Override
    public void initialize() {
        super.initialize();
        xb.rumble(intensity);
    }

    /**
     * End the controller rumble.
     */
    @Override
    public void end(boolean interrupted) {
        xb.rumble(0);
        super.end(interrupted);
    }
}
