package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.Rev2mDistanceSensor.Port;
import com.revrobotics.Rev2mDistanceSensor.RangeProfile;
import com.revrobotics.Rev2mDistanceSensor.Unit;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.commands.drivetrain.XboxDrive;
import frc.robot.shuffleboard.DemoTab;

/**
 * Twilight Horse. The cool way to say drivetrain. Also operated as a singleton
 * because there can be only one drivetrain.
 * 
 * @author Reece
 */
public class DriveTrain extends SubsystemBase {

    // Drivetrain motor controllers
    private final CANSparkMax leftLeader, leftFollower, rightLeader, rightFollower;

    // navX gyro
    private final AHRS navX;

    // Distance sensor
    private Rev2mDistanceSensor distanceSensor;

    // Drive motor encoders
    private final CANEncoder leftEncoder, rightEncoder;

    // Drivetrain contractor
    private final DifferentialDrive drive;

    // Drivetrain odometry
    private final DifferentialDriveOdometry odometry;
    
    //Demo Stuff
    private final DemoTab demoTab;


    public DriveTrain(DemoTab demoTab) {
        // Map drive motor controllers to their CAN ids
        leftLeader = neoBuilder(DrivetrainConstants.LEFT_LEADER);
        leftFollower = neoBuilder(DrivetrainConstants.LEFT_FOLLOWER);
        rightLeader = neoBuilder(DrivetrainConstants.RIGHT_LEADER);
        rightFollower = neoBuilder(DrivetrainConstants.RIGHT_FOLLOWER);

        // Initialize navX
        navX = new AHRS();

        // Initialize distance sensor
        // distanceSensor = new Rev2mDistanceSensor(Port.kOnboard, Unit.kInches,
        // RangeProfile.kDefault);
        // distanceSensor.setAutomaticMode(true);

        // Initialize drivetrain odometer
        odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(navX.getAngle()));

        // Reset motors to default settings
        leftLeader.restoreFactoryDefaults();
        leftFollower.restoreFactoryDefaults();
        rightLeader.restoreFactoryDefaults();
        rightFollower.restoreFactoryDefaults();

        // Current limit drive motors at 40 amps
        leftLeader.setSmartCurrentLimit(40);
        leftFollower.setSmartCurrentLimit(40);
        rightLeader.setSmartCurrentLimit(40);
        rightFollower.setSmartCurrentLimit(40);

        // Limits current to 40 Amps to prevent motor burnout
        leftLeader.setSmartCurrentLimit(40);
        leftFollower.setSmartCurrentLimit(40);
        rightLeader.setSmartCurrentLimit(40);
        rightFollower.setSmartCurrentLimit(40);

        // Initialize the drive motor encoders
        leftEncoder = leftLeader.getEncoder();
        rightEncoder = rightLeader.getEncoder();

        // Set drivetrain idle mode
        setIdleBehavior(DrivetrainConstants.IDLE_BEHAVIOR);

        // Set drivetrain ramp rate
        setOpenLoopRampRate(DrivetrainConstants.RAMP_RATE);

        // Set drivetrain inversion
        leftLeader.setInverted(DrivetrainConstants.LEFT_LEADER_INVERT);
        leftFollower.setInverted(DrivetrainConstants.LEFT_FOLLOWER_INVERT); // TODO verify
        rightLeader.setInverted(DrivetrainConstants.RIGHT_LEADER_INVERT);
        rightFollower.setInverted(DrivetrainConstants.RIGHT_FOLLOWER_INVERT); // TODO verify

        // Follow leader motors
        leftFollower.follow(leftLeader);
        rightFollower.follow(rightLeader);

        // Initialize drivetrain contractor
        drive = new DifferentialDrive(leftLeader, rightLeader);

        this.demoTab = demoTab;

        // Set default command to XboxDrive
        setDefaultCommand(new XboxDrive(this));
    }

    /**
     * Periodic method for the drivetrain subsystem.
     */
    @Override
    public void periodic() {
        // Update odometry
        final double leftMeters = leftEncoder.getPosition() * DrivetrainConstants.ENC_COUNTS_PER_METER;
        final double rightMeters = rightEncoder.getPosition() * DrivetrainConstants.ENC_COUNTS_PER_METER;
        odometry.update(Rotation2d.fromDegrees(navX.getAngle()), leftMeters, rightMeters);

        // Keep drivetrain in default idle mode
        setIdleBehavior(DrivetrainConstants.IDLE_BEHAVIOR);
    }

    /**
     * Uses distance sensor to find distance in inches.
     * 
     * @return The distance measured by the distance sensor. If distance not valid,
     *         returns -1.
     */
    public double getRange() {
        return distanceSensor.getRange();
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
    public void drive(final double power, final double spin) {
        double modifier = demoTab.getSpeedModifier();

        drive.curvatureDrive(power*modifier, spin*modifier, true);
    }

    /**
     * Sets the idle behavior of all the drive motors.
     * 
     * @param idleBehavior The idle behavior to set to.
     */
    public void setIdleBehavior(final IdleMode idleBehavior) {
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
    public void setOpenLoopRampRate(final double seconds) {
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
    private CANSparkMax neoBuilder(final int channel) {
        return new CANSparkMax(channel, MotorType.kBrushless);
    }
}
