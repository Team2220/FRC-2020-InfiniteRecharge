package frc.robot;

import com.revrobotics.CANSparkMax.IdleMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;

/**
 * Centralized location for all constants. Following new WPI convention of
 * making inner static classes for each subsystem. CAN IDs should go inside
 * their respective inner class.
 * 
 * link to the robot design sheet; the electrical tab 
 * https://docs.google.com/spreadsheets/d/1z7qXRHAooeRAFEQf8ARzbfuT_bp6NRnh-9rDxeA0Qa4/edit#gid=15585419
 *
 * @author 2220
 */


public final class Constants {

    public static final class ClimberConstants {

        // Climber motor controller IDs
        public static final int RIGHT_FALCON = 10;
        public static final int LEFT_FALCON = 9;
        // Climber save
        public static final NeutralMode IDLE_BEHAVIOR = NeutralMode.Brake;
    }

    /**
     * Constants associated with the drivetrain subsytem.
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
        public static final IdleMode IDLE_BEHAVIOR = IdleMode.kBrake; // TODO see if brake or coast is better for
                                                                      // driving

        // Drivetrain open loop ramp rate in seconds
        public static final double RAMP_RATE = 0.25; // TODO much testing needs to be done here

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


    /**
     * Constants associated with the Hopper subsytem.
     */
    // public final class HopperConstants {

    //     // Hopper motor controller IDs
    //     public static final int RIGHT_TALON = 4;
    //     public static final int LEFT_TALON = 2;
    // }
    public static final class HopperConstants {

        public static final int LEFT_HOPPER = 2;
        public static final int RIGHT_HOPPER = 4;
        public static final int FRONT_COLUMN = 3;
        public static final int BACK_COLUMN = 6;

        public static final double RAMP_RATE = 0.1;

        public static final NeutralMode IDLE_BEHAVIOR = NeutralMode.Brake;
    }
    /**
     * Constants associated with the Intake subsytem.
     */
    public final class IntakeConstants {

        // Intake motor controller ID
        public static final int TALON = 5;
        // Intake deploy solenoid IDs
        public static final int SOLENOID_FORWARD = 6;
        public static final int SOLENOID_REVERSE = 7;
        public static final double INTAKE_POWER = 0.4; 
    }
   
    

    

    /**
     * Constants associated with the Shooter subsystem.
     */
    public static final class ShooterConstants {

        // Shooter flywheel motor controller IDs
        public static final int LEFT_FALCON = 7;
        public static final int RIGHT_FALCON = 8;

        // Shooter flywheel motor directions
        public static final TalonFXInvertType LEFT_FALCON_DIRECTION = TalonFXInvertType.Clockwise;
        public static final TalonFXInvertType RIGHT_FALCON_DIRECTION = TalonFXInvertType.CounterClockwise;

        // Shooter flywheel idle behavior
        public static final NeutralMode FLYWHEEL_IDLE_BEHAVIOR = NeutralMode.Coast;

        // Shooter voltage compensation saturation value
        public static final double VOLTAGE_SATURATION = 9;

        // Shooter flywheel ramp rate
        public static final double RAMP_RATE = 0.5; // TODO test lowest acceptable ramp rate is for shooter

        // Shooter flywheel PIDF contants
        public static final double kP = 0.1;
        public static final double kI = 0;
        public static final double kD = 2.5;
        public static final double kF = 0.0692;//0.0692;

        // Shooter flywheel shot velocity
        public static final int SHOT_VELOCITY = 13490 - 535;
    }

    /**
     * Constants associated with the Tower subsystem.
     */
    public static final class TowerConstants {

        // Tower motor controller IDs
        public static final int FRONT_TOWER = 3;
        public static final int BACK_TOWER = 6;

        // Tower motor inversions
        public static final boolean FRONT_TOWER_INVERT = false;
        public static final boolean BACK_TOWER_INVERT = false;

        // Tower idle behavior
        public static final NeutralMode TOWER_IDLE_BEHAVIOR = NeutralMode.Brake;

        // Tower motor power
        public static final double TOWER_POWER = 0.6; // TODO test
    }

}
