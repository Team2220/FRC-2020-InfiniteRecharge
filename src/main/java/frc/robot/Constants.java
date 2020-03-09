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

    public final class ClimberConstants {

        // Climber motor controller IDs
        public static final int RIGHT_FALCON = 9;
        public static final int LEFT_FALCON = 10;
    }

    /**
     * Constants related to the drivetrain.
     */
    public static final class DrivetrainConstants {

        // Drive motor controller CAN ids TODO need to be actually mapped
        public static final int LEFT_LEADER = 1;
        public static final int LEFT_FOLLOWER = 3;
        public static final int RIGHT_LEADER = 4;
        public static final int RIGHT_FOLLOWER = 2;

        public static final boolean LEFT_LEADER_INVERT = true;
        public static final boolean LEFT_FOLLOWER_INVERT = false;
        public static final boolean RIGHT_LEADER_INVERT = true;
        public static final boolean RIGHT_FOLLOWER_INVERT = false;

        // Drivetrain idle behavior while driving
        public static final IdleMode IDLE_BEHAVIOR = IdleMode.kCoast; // TODO see if brake or coast is better for driving

        // Drivetrain open loop ramp rate in seconds
        public static final double RAMP_RATE = 1; // TODO much testing needs to be done here

        // NEOs have 1 encoder count per revolution
        // Multiplied by 6 inch wheels
        // Multiplied by PI for diameter
        // Multiplied by 0.0254 for meters

        public static final double ENC_COUNTS_PER_METER = 6 * Math.PI * 0.0254;

        // Drivetrain PIDF values TODO need to actually be characterized
        public static double kP = 0;
        public static double kI = 0;
        public static double kD = 0;
        public static double kF = 0;
    }

    public final class HopperConstants {

        // Hopper motor controller IDs
        public static final int RIGHT_TALON = 4;
        public static final int LEFT_TALON = 2;
    }

    public final class IntakeConstants {

        // Intake motor controller ID
        public static final int TALON = 5;

        // Intake deploy solenoid IDs
        public static final int SOLENOID_FORWARD = 6;
        public static final int SOLENOID_REVERSE = 7;
    }

    public static final class ShooterConstants {

        // Shooter flywheel motor controller IDs
        public static final int LEFT_FALCON = 7;
        public static final int RIGHT_FALCON = 8;

        // Tower motor controller IDs
        public static final int FRONT_TOWER = 3;
        public static final int BACK_TOWER = 6;

        // Shooter flywheel ramp rate
        public static final double RAMP_RATE = 0.75; // TODO test lowest acceptable ramp rate is for shooter

        // Shooter flywheel idle behavior
        public static final NeutralMode IDLE_BEHAVIOR = NeutralMode.Coast;

        // Shooter flywheel PIDF contants
        public static final double kP = 0;
        public static final double kI = 0;
        public static final double kD = 0;
        public static final double kF = 0;
    }
}
