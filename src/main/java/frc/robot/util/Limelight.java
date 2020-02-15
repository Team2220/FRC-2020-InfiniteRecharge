package frc.robot.util;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Limelight (vision processer or high FOV driver camera) subsystem. Contains
 * methods necessary to access data from and control the Limelight camera. This
 * class is designed to be used in conjunction with other subsystems and/or
 * commands to achieve automation.
 * 
 * Below the official Limelight documentation is linked. This class was built
 * around the documentation, and they are referenced often.
 * 
 * @author Reece
 * @see {@link http://docs.limelightvision.io/en/latest/}
 */
public class Limelight extends SubsystemBase {

    // Limelight's network tables.
    private final NetworkTable limelight;

    /**
     * Constructs and initializes a Limelight subsystem.
     * 
     * @param networkTableName The Limelight's network table name. This is set in
     *                         the Limelight configuration web page. This allows for
     *                         multiple Limelights per robot.
     */
    public Limelight(String networkTableName) {
        // Initialize limelight instance variable described above.
        limelight = NetworkTableInstance.getDefault().getTable(networkTableName);

        // Configure Limelight States to default when subsystem is initialized.
        setLEDMode(LEDMode.USE_PIPELINE);
        setCameraMode(CameraMode.VISION_PROCESSING);
        setStreamMode(StreamMode.STANDARD);
    }

    /* Private Methods */

    /**
     * Method that simplifies accessing Limelight network table entries.
     * 
     * @param entryName The string key of the network table entry.
     * @return Double value of the network table entry.
     */
    private double getEntryValue(String entryName) {
        return limelight.getEntry(entryName).getDouble(0);
    }

    /**
     * Method that makes setting Limelight network table entries easier.
     * 
     * @param entryName The string key of the network table entry.
     * @param value     The number value to set the network table entry to.
     */
    private void setEntry(String entryName, int value) {
        limelight.getEntry(entryName).setNumber(value);
    }

    /* Getter Methods */

    /**
     * Method to check if the Limelight can see the target.
     * 
     * @return True if target is visible, false if not visible.
     */
    public boolean canSeeTarget() {
        // "tv" means target visible. In documentation 0 means the target is not
        // visible, and 1 means the target is visible.
        return getEntryValue("tv") == 1;
    }

    /**
     * Method to check the horizontal distance from the target.
     * 
     * @return Horizontal angular distance to target. Range: (-27, 27) degrees.
     */
    public double getXOffset() {
        // "tx" means target x-coordinate. In documentation the double returned is the
        // horizontal angular distance the camera's center is from the target's center.
        return getEntryValue("tx");
    }

    /**
     * Method to check the vertical distance from the target.
     * 
     * @return Vertical angular distance to target. Range: (-20.5, 20.5) degrees.
     */
    public double getYOffset() {
        // "ty" means target y-coordinate. In documentation the double returned is the
        // vertical angular distance the camera's center is from the target's center.
        return getEntryValue("ty");
    }

    /**
     * Method to check the amount of the camera's image that the target takes up, or
     * its "size."
     * 
     * @return Percent of the Limelight's picture that is taken up by the target.
     *         Range: (0, 100) percent.
     */
    public double getTargetSize() {
        // "ta" means target area. In documentation the double returned is the percent
        // of the Limelight's picture that is taken up by the target.
        return getEntryValue("ta");
    }

    /**
     * Method to check the rotation of the target, or its "skew."
     * 
     * @return Degrees that the target is rotated along the XY plane. Range: (-90,
     *         0) degrees.
     */
    public double getTargetSkew() {
        // "ts" means target skew. In documentation the double returned is the amount of
        // degrees that the target is rotated along the XY plane. The number range seems
        // innacurate so TODO more research needs to be done about its range.
        return getEntryValue("ts");
    }

    /* Setter Methods */

    /**
     * Method to set the Limelight's operation mode.
     * 
     * @param cameraMode CameraMode enum, either vision processing or driver camera.
     */
    public void setCameraMode(CameraMode cameraMode) {
        setEntry("camMode", cameraMode.val);
    }

    /**
     * Method to set the Limelight's LED mode.
     * 
     * @param ledMode LEDMode enum, options listed below, described in
     *                documentation.
     */
    public void setLEDMode(LEDMode ledMode) {
        setEntry("ledMode", ledMode.val);
    }

    /**
     * Method to set the Limelight's streaming mode.
     * 
     * @param streamMode StreamMode enum, options listed below, described in
     *                   documentation.
     */
    public void setStreamMode(StreamMode streamMode) {
        setEntry("stream", streamMode.val);
    }

    /**
     * Method to set the Limelight's current target pipeline.
     * 
     * @param pipeline The int ID of the pipeline specified in the Limelight's web
     *                 configuration page.
     */
    public void setPipeline(int pipeline) {
        setEntry("pipeline", pipeline);
    }

    /* Other Methods */

    /**
     * Method to take a snapshot of the Limelight's current image, target, and data.
     * There is more information about snapshots in documentation.
     */
    public void takeSnapshot() {
        setEntry("snapshot", 1);
    }

    /**
     * Method to set the default command of this Limelight subsytem.
     */
    public void setDefaultCommand(Command command) {
        super.setDefaultCommand(command);
    }

    /* Limelight State Enums */

    /**
     * Limelight LED modes.
     */
    public enum LEDMode {
        USE_PIPELINE(0), OFF(1), BLINK(2), ON(3);

        private final int val;

        LEDMode(int val) {
            this.val = val;
        }
    }

    /**
     * Limelight operation modes, or "camera modes."
     */
    public enum CameraMode {
        VISION_PROCESSING(0), DRIVER_CAMERA(1);

        private final int val;

        CameraMode(int val) {
            this.val = val;
        }
    }

    /**
     * Limelight streaming modes, PiP stands for Picture in Picture. For more
     * information about PiP, check the documentation.
     */
    public enum StreamMode {
        STANDARD(0), PIP_MAIN(1), PIP_SECONDARY(2);

        private final int val;

        StreamMode(int val) {
            this.val = val;
        }
    }
}