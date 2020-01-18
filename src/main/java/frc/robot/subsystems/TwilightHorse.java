package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.commands.drivetrain.XboxDrive;

/**
 * Twilight Horse. The cool way to say drivetrain. Also operated as a singleton
 * because there can be only one drivetrain.
 * 
 * @author Reece
 */
public class TwilightHorse extends SubsystemBase {

    // Drivetrain motor controllers
    private CANSparkMax leftLeader, leftFollower, rightLeader, rightFollower;

    // navX gyro
    private AHRS navX;

    // Drive motor encoders
    private CANEncoder leftEncoder, rightEncoder;

    // Drivetrain contractor
    private DifferentialDrive drive;

    // Drivetrain odometry
    private DifferentialDriveOdometry odometry;

    // Singleton subsystem instance
    private static TwilightHorse instance;

    private TwilightHorse() {
        // Map drive motor controllers to their CAN ids
        leftLeader = neoBuilder(DrivetrainConstants.LEFT_LEADER);
        leftFollower = neoBuilder(DrivetrainConstants.LEFT_FOLLOWER);
        rightLeader = neoBuilder(DrivetrainConstants.RIGHT_LEADER);
        rightFollower = neoBuilder(DrivetrainConstants.RIGHT_FOLLOWER);

        // Initialize navX
        navX = new AHRS();

        // Initialize drivetrain odometer
        odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(navX.getAngle()));

        // Reset motors to default settings
        leftLeader.restoreFactoryDefaults();
        leftFollower.restoreFactoryDefaults();
        rightLeader.restoreFactoryDefaults();
        rightFollower.restoreFactoryDefaults();

        // Initialize the drive motor encoders
        leftEncoder = leftLeader.getEncoder();
        rightEncoder = rightLeader.getEncoder();

        // Set drivetrain idle mode
        setIdleBehavior(DrivetrainConstants.idleBehavior);

        // Set drivetrain ramp rate
        setOpenLoopRampRate(DrivetrainConstants.RAMP_RATE);

        // Follow leader motors
        leftFollower.follow(leftLeader);
        rightFollower.follow(rightLeader);

        // Initialize drivetrain contractor
        drive = new DifferentialDrive(leftLeader, rightLeader);

        // Set default command to XboxDrive
        setDefaultCommand(new XboxDrive(this)); // TODO make sure this doesn't break anything
    }

    /**
     * Periodic method for the drivetrain subsystem.
     */
    @Override
    public void periodic() {
        // Update odometry
        double leftMeters = leftEncoder.getPosition() * DrivetrainConstants.ENC_COUNTS_PER_METER;
        double rightMeters = rightEncoder.getPosition() * DrivetrainConstants.ENC_COUNTS_PER_METER;
        odometry.update(Rotation2d.fromDegrees(navX.getAngle()), leftMeters, rightMeters);

        // Keep drivetrain in default idle mode
        setIdleBehavior(DrivetrainConstants.idleBehavior);
    }

    /**
     * Singleton instance getter method.
     * 
     * @return Returns the singleton object for the drivetrain.
     */
    public static TwilightHorse getInstance() {
        if (instance == null) {
            instance = new TwilightHorse();
        }
        return instance;
    }

    /**
     * Left encoder position getter.
     * 
     * @return The position of the left encoder
     */
    public double getLeftEncoderPosition() {
        return leftEncoder.getPosition();
    }

    /**
     * Right encoder position getter.
     * 
     * @return The position of the left encoder
     */
    public double getRightEncoderPosition() {
        return rightEncoder.getPosition();
    }

    /**
     * Left encoder velocity getter.
     * 
     * @return The velocity of the left encoder.
     */
    public double getLeftEncoderVelocity() {
        return leftEncoder.getVelocity();
    }

    /**
     * Right encoder velocity getter.
     * 
     * @return The velocity of the right encoder.
     */
    public double getRightEncoderVelocity() {
        return rightEncoder.getVelocity();
    }

    /**
     * Runs curvature drive on this drivetrain
     * 
     * @param power The forward and backward power input.
     * @param spin  The rotation power input.
     */
    public void drive(double power, double spin) {
        drive.curvatureDrive(power, spin, true);
    }

    /**
     * Sets the idle behavior of all the drive motors.
     * 
     * @param idleBehavior The idle behavior to set to.
     */
    public void setIdleBehavior(IdleMode idleBehavior) {
        leftLeader.setIdleMode(idleBehavior);
        leftFollower.setIdleMode(idleBehavior);
        rightLeader.setIdleMode(idleBehavior);
        rightFollower.setIdleMode(idleBehavior);
    }

    /**
     * Sets the time it takes for the drive motors to go from 0%-100% velocity
     * during open-loop control.
     * 
     * @param seconds The amount of seconds to fully accelerate from rest.
     */
    public void setOpenLoopRampRate(double seconds) {
        leftLeader.setOpenLoopRampRate(seconds);
        leftFollower.setOpenLoopRampRate(seconds);
        rightLeader.setOpenLoopRampRate(seconds);
        rightFollower.setOpenLoopRampRate(seconds);
    }

    /**
     * I'm lazy and don't want to type an enum 4 times so I made this method to do
     * it for me. :)
     * 
     * @param channel CAN channel that the spark max uses.
     * @return Constructs a spark max object, and lets me embrace my ADHD by not
     *         typing the same thing 4 times.
     */
    private CANSparkMax neoBuilder(int channel) {
        return new CANSparkMax(channel, MotorType.kBrushless);
    }
}
