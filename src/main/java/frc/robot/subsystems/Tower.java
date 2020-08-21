package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.Constants.TowerConstants;

/**
 * Tower
 */
public class Tower extends SubsystemBase {

    // Tower talons
    TalonSRX frontTower, backTower;

    private TowerState state = TowerState.IDLE;

    private static Tower instance;

    private Tower() {
        // Tower talons
        frontTower = new TalonSRX(TowerConstants.FRONT_TOWER);
        backTower = new TalonSRX(TowerConstants.BACK_TOWER);

        // Reset to factory defaults for all talons
        frontTower.configFactoryDefault();
        backTower.configFactoryDefault();

        // Set tower motor inversions
        frontTower.setInverted(TowerConstants.FRONT_TOWER_INVERT);
        backTower.setInverted(TowerConstants.BACK_TOWER_INVERT);

        // Back tower follows front tower
        backTower.follow(frontTower);
        
        // Set tower idle behavior
        frontTower.setNeutralMode(TowerConstants.TOWER_IDLE_BEHAVIOR);
        backTower.setNeutralMode(TowerConstants.TOWER_IDLE_BEHAVIOR);

        // Enable voltage compensation for all shooter motor controllers
        frontTower.enableVoltageCompensation(true);
        backTower.enableVoltageCompensation(true);

        // Set voltage saturation for all shooter motor controllers
        frontTower.configVoltageCompSaturation(ShooterConstants.VOLTAGE_SATURATION);
        backTower.configVoltageCompSaturation(ShooterConstants.VOLTAGE_SATURATION);
    }

    public static Tower getInstance() {
        if (instance == null) {
            instance = new Tower();
        }
        return instance;
    }

    public void setState(TowerState newState) {
        if (newState == state) {
            return;
        }

        switch (newState) {
        case IDLE:
            setTowerPower(0);
            break;

        case RUNNING:
            setTowerPower(TowerConstants.TOWER_POWER);
            break;
        }
    }

    /**
     * Sets the power of the tower. Uses percent output.
     * 
     * @param demand The power demand to set the tower motors to.
     */
    public void setTowerPower(double demand) {
        frontTower.set(ControlMode.PercentOutput, demand);
    }

    public enum TowerState {
        IDLE, RUNNING;
    }
}