package frc.robot;

import com.revrobotics.CANSparkMax.IdleMode;

/**
 * Centralized location for all constants. Following new WPI convention of
 * making inner static classes for each subsystem. CAN IDs should go inside
 * their respective inner class.
 * 
 * @author 2220
 */
public final class Constants {

    /**
     * Constants related to the drivetrain.
     */
    public static final class DrivetrainConstants {

        // Drive motor controller CAN ids TODO need to be actually mapped
        public static final int LEFT_LEADER = 1;
        public static final int LEFT_FOLLOWER = 2;
        public static final int RIGHT_LEADER = 3;
        public static final int RIGHT_FOLLOWER = 4;

        // Drivetrain idle behavior while driving
        public static final IdleMode idleBehavior = IdleMode.kBrake; // TODO see if brake or coast is better for driving

        // Drivetrain open loop ramp rate in seconds
        public static final double RAMP_RATE = 1; // TODO much testing needs to be done here

        // NEOs have 1 encoder count per revolution
        // Multiplied by 6 inch wheels
        // Multiplied by PI for diameter
        // Multiplied by 0.0254 for meters
        public static final double ENC_COUNTS_PER_METER = 6 * Math.PI * 0.0254;
    }
}
