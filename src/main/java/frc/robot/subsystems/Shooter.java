package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.shooter.ShootWithJoystick;

/**
 * Shooter
 */

public class Shooter extends SubsystemBase {

    private TalonFX leftFalcon, rightFalcon;

    ShuffleboardTab shooterTab = Shuffleboard.getTab("shooter");

    public Shooter() {

        leftFalcon = new TalonFX(ShooterConstants.LEFT_FALCON);
        rightFalcon = new TalonFX(ShooterConstants.RIGHT_FALCON);

        leftFalcon.configFactoryDefault();
        rightFalcon.configFactoryDefault();

        rightFalcon.follow(leftFalcon);

        leftFalcon.setInverted(TalonFXInvertType.Clockwise);
        rightFalcon.setInverted(TalonFXInvertType.CounterClockwise);

        leftFalcon.configOpenloopRamp(ShooterConstants.RAMP_RATE);
        rightFalcon.configOpenloopRamp(ShooterConstants.RAMP_RATE);

        leftFalcon.setNeutralMode(ShooterConstants.IDLE_BEHAVIOR);
        rightFalcon.setNeutralMode(ShooterConstants.IDLE_BEHAVIOR);

        shooterTab.addNumber("left falcon velocity", () -> leftFalcon.getSelectedSensorVelocity());
        shooterTab.addNumber("right falcon velocity", () -> rightFalcon.getSelectedSensorVelocity());

        setDefaultCommand(new ShootWithJoystick(this));
    }

    public void setPower(double demand) {
        if (Math.abs(demand) > 0.95) {
            demand = Math.signum(demand) * 0.95;
        }
        leftFalcon.set(TalonFXControlMode.PercentOutput, demand);

    }

    public void setVelocity(int demand) {
        leftFalcon.set(TalonFXControlMode.Velocity, demand);
    }
}