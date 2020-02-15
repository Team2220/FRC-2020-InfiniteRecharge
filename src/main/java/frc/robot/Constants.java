package frc.robot;

import com.revrobotics.CANSparkMax.IdleMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

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
        public static final int LEFT_LEADER = 4;
        public static final int LEFT_FOLLOWER = 1;
        public static final int RIGHT_LEADER = 2;
        public static final int RIGHT_FOLLOWER = 3;

        // Drivetrain idle behavior while driving
        public static final IdleMode IDLE_BEHAVIOR = IdleMode.kBrake; // TODO see if brake or coast is better for
                                                                      // driving

        // Drivetrain open loop ramp rate in seconds
        public static final double RAMP_RATE = 0.25; // TODO much testing needs to be done here

        // NEOs have 1 encoder count per revolution
        // Multiplied by 6 inch wheels
        // Multiplied by PI for diameter
        // Multiplied by 0.0254 for meters

        public static final double ENC_COUNTS_PER_METER = 6 * Math.PI * 0.0254;
    }

    public static final class HopperConstants {

        public static final int LEFT_HOPPER = 64;
        public static final int RIGHT_HOPPER = 65;
        public static final int FRONT_COLUMN = 3;
        public static final int BACK_COLUMN = 4;

        public static final double RAMP_RATE = 0.1;

        public static final NeutralMode IDLE_BEHAVIOR = NeutralMode.Brake;

    }

    public static final class ShooterConstants {

        public static final int LEFT_FALCON = 1;
        public static final int RIGHT_FALCON = 2;

        public static final double RAMP_RATE = 0.75;

        public static final NeutralMode IDLE_BEHAVIOR = NeutralMode.Coast;
    }

    public static final class ClimberConstants {

        public static final int RIGHT_FALCON = 6;
        public static final int LEFT_FALCON = 7;

    }

    public static final class IntakeConstants {

        public static final int TALON = 25;
        public static final int SOLENOID_FORWARD = 2;
        public static final int SOLENOID_REVERSE = 1;
    }
}
