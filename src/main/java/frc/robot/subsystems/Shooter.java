package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

/**
 * Shooter
 */

public class Shooter extends SubsystemBase {

    // Shooter flywheel falcons
    TalonFX leftFalcon, rightFalcon;

    // Flywheel target velocity instance variable
    private double flywheelTargetVelocity = 0;

    // Subsystem system state
    private ShooterSystemState sState = ShooterSystemState.STILL;

    // Subsystem desired state
    private ShooterDesiredState dState = ShooterDesiredState.IDLE;

    // Constant for acceptable difference in actual and target velocity
    private static final int ACCEPTABLE_VELOCITY_DIFFERENCE = 20;

    // Singleton subsystem instance
    private static Shooter instance;

    ShuffleboardTab t = Shuffleboard.getTab("shooter");
    Object o = t.addNumber("velocity", () -> (getFlywheelVelocity()));
    Object d = t.addBoolean("dude", () -> flywheelAtTargetVelocity());

    /**
     * Private Shooter subsystem. Handles initilization and configuration of all
     * related motors.
     */
    private Shooter() {
        // Shooter falcons
        leftFalcon = new TalonFX(ShooterConstants.LEFT_FALCON);
        rightFalcon = new TalonFX(ShooterConstants.RIGHT_FALCON);

        // Reset to factory defaults for all talons
        leftFalcon.configFactoryDefault();
        rightFalcon.configFactoryDefault();

        // Set shooter falcon directions
        leftFalcon.setInverted(ShooterConstants.LEFT_FALCON_DIRECTION);
        rightFalcon.setInverted(ShooterConstants.RIGHT_FALCON_DIRECTION);

        // Right falcon follows left falcon
        rightFalcon.follow(leftFalcon);

        // Set shooter flywheel idle behavior
        leftFalcon.setNeutralMode(ShooterConstants.FLYWHEEL_IDLE_BEHAVIOR);
        rightFalcon.setNeutralMode(ShooterConstants.FLYWHEEL_IDLE_BEHAVIOR);

        // Set shooter flywheel ramp rate
        leftFalcon.configOpenloopRamp(ShooterConstants.RAMP_RATE);
        rightFalcon.configOpenloopRamp(ShooterConstants.RAMP_RATE);

        // Enable voltage compensation for all shooter motor controllers
        leftFalcon.enableVoltageCompensation(true);
        rightFalcon.enableVoltageCompensation(true);

        // Set voltage saturation for all shooter motor controllers
        leftFalcon.configVoltageCompSaturation(ShooterConstants.VOLTAGE_SATURATION);
        rightFalcon.configVoltageCompSaturation(ShooterConstants.VOLTAGE_SATURATION);
    }

    @Override
    public void periodic() {
        // Set PIDF values for shooter because apparently this only works in periodic
        leftFalcon.config_kP(0, 0.1);
        leftFalcon.config_kI(0, 0);
        leftFalcon.config_kD(0, 2.5);
        leftFalcon.config_kF(0, 0.0703); //0.0692
        // double kP = ShooterConstants.kP;
        // double kI = ShooterConstants.kI;
        // double kD = ShooterConstants.kD;
        // double kF = ShooterConstants.kF;
        // leftFalcon.config_kP(0, kP);
        // leftFalcon.config_kP(0, kI);
        // leftFalcon.config_kP(0, kD);
        // leftFalcon.config_kP(0, kF);

        // Subsystem state machine switch
        switch (sState) {
        case STILL:
            break;

        case SPINNING_UNREADY:
            if (flywheelAtTargetVelocity()) {
                transitionSystemState(ShooterSystemState.SPINNING_READY);
            }
            break;

        case SPINNING_READY:
            if (!flywheelAtTargetVelocity()) {
                transitionSystemState(ShooterSystemState.SPINNING_UNREADY);
            }
            break;
        }
    }

    /**
     * Singleton instance getter method.
     * 
     * @return Returns the singleton object for the shooter.
     */
    public static Shooter getInstance() {
        if (instance == null) {
            instance = new Shooter();
        }
        return instance;
    }

    private void transitionSystemState(ShooterSystemState newState) {
        if (sState == newState) {
            return;
        }

        switch (newState) {
        case STILL:
            setFlywheelPower(0);
            break;

        case SPINNING_UNREADY:
            setFlywheelVelocity(ShooterConstants.SHOT_VELOCITY);
            break;

        case SPINNING_READY:
            break;
        }
        sState = newState;
    }

    public void setState(ShooterDesiredState newState) {
        if (dState == newState) {
            return;
        }

        switch (newState) {
        case IDLE:
            transitionSystemState(ShooterSystemState.STILL);
            break;

        case SHOOT:
            transitionSystemState(ShooterSystemState.SPINNING_UNREADY);
            break;
        }
    }

    public ShooterSystemState getSystemState() {
        return sState;
    }

    public ShooterDesiredState getDesiredState() {
        return dState;
    }

    /**
     * Getter for the flywheel velocity.
     * 
     * @return The flywheel's velocity in native sensor units per 100ms.
     */
    public int getFlywheelVelocity() {
        return leftFalcon.getSelectedSensorVelocity();
    }

    /**
     * Sets the shooter flywheel power. Uses percent output.
     * 
     * @param demand The power demand to set the flywheel motors to.
     */
    public void setFlywheelPower(double demand) {
        leftFalcon.set(TalonFXControlMode.PercentOutput, demand);
    }

    /**
     * Sets the shooter flywheel velocity. Uses velocity PID.
     * 
     * @param demand The velocity demand to set the flywheel motors to.
     */
    public void setFlywheelVelocity(double demand) {
        flywheelTargetVelocity = demand;
        leftFalcon.set(TalonFXControlMode.Velocity, demand);
    }

    public boolean flywheelAtTargetVelocity() {
        double difference = getFlywheelVelocity() - flywheelTargetVelocity;
        return difference >= 0 && difference < ACCEPTABLE_VELOCITY_DIFFERENCE;
    }

    public enum ShooterSystemState {
        STILL, SPINNING_UNREADY, SPINNING_READY
    }

    public enum ShooterDesiredState {
        IDLE, SHOOT
    }
}