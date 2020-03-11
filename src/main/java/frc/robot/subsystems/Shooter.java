package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

/**
 * Shooter
 */

public class Shooter extends SubsystemBase {

    // Shooter flywheel falcons
    TalonFX leftFalcon, rightFalcon;

    // Tower talons
    TalonSRX frontTower, backTower;

    // Singleton subsystem instance
    private static Shooter instance;

    /**
     * Private Shooter subsystem. Handles initilization and configuration of all
     * related motors.
     */
    private Shooter() {

        // Shooter falcons
        leftFalcon = new TalonFX(ShooterConstants.LEFT_FALCON);
        rightFalcon = new TalonFX(ShooterConstants.RIGHT_FALCON);

        // Tower talons
        frontTower = new TalonSRX(ShooterConstants.FRONT_TOWER);
        backTower = new TalonSRX(ShooterConstants.BACK_TOWER);

        // Reset to factory defaults for all talons
        leftFalcon.configFactoryDefault();
        rightFalcon.configFactoryDefault();
        frontTower.configFactoryDefault();
        backTower.configFactoryDefault();

        // Set shooter falcon directions
        leftFalcon.setInverted(ShooterConstants.LEFT_FALCON_DIRECTION);
        rightFalcon.setInverted(ShooterConstants.RIGHT_FALCON_DIRECTION);

        // Right falcon follows left falcon
        rightFalcon.follow(leftFalcon);

        // Set tower motor inversions
        frontTower.setInverted(ShooterConstants.FRONT_TOWER_INVERT);
        backTower.setInverted(ShooterConstants.BACK_TOWER_INVERT);

        // Back tower follows front tower
        backTower.follow(frontTower);

        // Set shooter flywheel idle behavior
        leftFalcon.setNeutralMode(ShooterConstants.FLYWHEEL_IDLE_BEHAVIOR);
        rightFalcon.setNeutralMode(ShooterConstants.FLYWHEEL_IDLE_BEHAVIOR);

        // Set tower idle behavior
        frontTower.setNeutralMode(ShooterConstants.TOWER_IDLE_BEHAVIOR);
        backTower.setNeutralMode(ShooterConstants.TOWER_IDLE_BEHAVIOR);

        // Set shooter flywheel ramp rate
        leftFalcon.configOpenloopRamp(ShooterConstants.RAMP_RATE);
        rightFalcon.configOpenloopRamp(ShooterConstants.RAMP_RATE);

        // Enable voltage compensation for all shooter motor controllers
        leftFalcon.enableVoltageCompensation(true);
        rightFalcon.enableVoltageCompensation(true);
        frontTower.enableVoltageCompensation(true);
        backTower.enableVoltageCompensation(true);

        // Set voltage saturation for all shooter motor controllers
        leftFalcon.configVoltageCompSaturation(ShooterConstants.VOLTAGE_SATURATION);
        rightFalcon.configVoltageCompSaturation(ShooterConstants.VOLTAGE_SATURATION);
        frontTower.configVoltageCompSaturation(ShooterConstants.VOLTAGE_SATURATION);
        backTower.configVoltageCompSaturation(ShooterConstants.VOLTAGE_SATURATION);

        // Set PIDF values for shooter flywheel motor controllers
        double kP = ShooterConstants.kP;
        double kI = ShooterConstants.kI;
        double kD = ShooterConstants.kD;
        double kF = ShooterConstants.kF;
        leftFalcon.config_kP(0, kP);
        leftFalcon.config_kP(0, kI);
        leftFalcon.config_kP(0, kD);
        leftFalcon.config_kP(0, kF);
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
        leftFalcon.set(TalonFXControlMode.Velocity, demand);
    }

    /**
     * Sets the power of the tower. Uses percent output.
     * 
     * @param demand The power demand to set the tower motors to.
     */
    public void setTowerPower(double demand) {
        frontTower.set(ControlMode.PercentOutput, demand);
    }
}