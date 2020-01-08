package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.CANMap;

/**
 * Twilight Horse. The cool way to say drivetrain. Also operated as a singleton
 * because there can be only one drivetrain.
 */
public class TwilightHorse extends SubsystemBase {

    // Drivetrain motor controllers
    private CANSparkMax leftLeader, leftFollower, rightLeader, rightFollower;

    // Drive motor encoders
    private CANEncoder leftEncoder, rightEncoder;

    // Drivetrain contractor
    private DifferentialDrive drive;

    // Drivetrain idle behavior
    IdleMode idleBehavior = IdleMode.kBrake; // TODO see if brake or coast is better for driving

    private static TwilightHorse instance;

    private TwilightHorse() {
        // Map drive motor controllers to their CAN ids
        leftLeader = neoBuilder(CANMap.LEFT_LEADER);
        leftFollower = neoBuilder(CANMap.LEFT_FOLLOWER);
        rightLeader = neoBuilder(CANMap.RIGHT_LEADER);
        rightFollower = neoBuilder(CANMap.RIGHT_FOLLOWER);

        // Initialize the drive motor encoders
        leftEncoder = leftLeader.getEncoder();
        rightEncoder = rightLeader.getEncoder();

        // Change drive motor idle mode
        setIdleBehavior(idleBehavior);

        // Follow leader motors
        leftFollower.follow(leftLeader);
        rightFollower.follow(rightLeader);

        // Initialize drivetrain contractor
        drive = new DifferentialDrive(leftLeader, rightLeader);
    }

    public static TwilightHorse getInstance() {
        if (instance == null) {
            instance = new TwilightHorse();
        }
        return instance;
    }

    public void drive(double power, double spin) {
        drive.curvatureDrive(power, spin, true);
    }

    public void setIdleBehavior(IdleMode idleBehavior) {
        leftLeader.setIdleMode(idleBehavior);
        leftFollower.setIdleMode(idleBehavior);
        rightLeader.setIdleMode(idleBehavior);
        rightFollower.setIdleMode(idleBehavior);
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
