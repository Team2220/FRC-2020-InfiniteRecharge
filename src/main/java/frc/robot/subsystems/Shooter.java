package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.shooter.ShootWithJoystick;

/**
 * Shooter
 */

public class Shooter extends SubsystemBase {

    private static double P = 0;
    private static double I = 0;
    private static double D = 0;
    private static double F = 0;
    private static final int MAX_VEL = 750;
    private static final int MAX_ACCEL = 350;

    TalonFX leftFalcon, rightFalcon;
    TalonSRX frontColumnTalonSRX, backColumnTalonSRX;

    ShuffleboardTab shooterTab = Shuffleboard.getTab("shooter");

    public Shooter() {

        leftFalcon = new TalonFX(ShooterConstants.LEFT_FALCON);
        rightFalcon = new TalonFX(ShooterConstants.RIGHT_FALCON);
        frontColumnTalonSRX = new TalonSRX(ShooterConstants.FRONT_COLUMN);
        backColumnTalonSRX = new TalonSRX(ShooterConstants.BACK_COLUMN);

        // leftFalcon.configFactoryDefault();
        // rightFalcon.configFactoryDefault();
        // frontColumnTalonSRX.configFactoryDefault();
        // backColumnTalonSRX.configFactoryDefault();

        rightFalcon.follow(leftFalcon);
        backColumnTalonSRX.follow(frontColumnTalonSRX);

        leftFalcon.setInverted(TalonFXInvertType.Clockwise);
        rightFalcon.setInverted(TalonFXInvertType.CounterClockwise);
        frontColumnTalonSRX.setInverted(InvertType.None);
        backColumnTalonSRX.setInverted(InvertType.None);

        leftFalcon.configOpenloopRamp(ShooterConstants.RAMP_RATE);
        rightFalcon.configOpenloopRamp(ShooterConstants.RAMP_RATE);

        leftFalcon.setNeutralMode(ShooterConstants.IDLE_BEHAVIOR);
        rightFalcon.setNeutralMode(ShooterConstants.IDLE_BEHAVIOR);
        frontColumnTalonSRX.setNeutralMode(ShooterConstants.IDLE_BEHAVIOR);
        backColumnTalonSRX.setNeutralMode(ShooterConstants.IDLE_BEHAVIOR);

        // shooterTab.addNumber("left falcon velocity", () -> leftFalcon.getSelectedSensorVelocity());
        // shooterTab.addNumber("right falcon velocity", () -> rightFalcon.getSelectedSensorVelocity());

        leftFalcon.config_kP(0, P);
        leftFalcon.config_kI(0, I);
        leftFalcon.config_kD(0, D);
        leftFalcon.config_kF(0, F);
        leftFalcon.configMotionAcceleration(MAX_ACCEL);
        leftFalcon.configMotionCruiseVelocity(MAX_VEL);

        setDefaultCommand(new ShootWithJoystick(this));
    }

    public void setSpeed(double speed) {
        if (Math.abs(speed) > 0.9) {
            speed = Math.signum(speed) * 0.9;
        }
        leftFalcon.set(TalonFXControlMode.PercentOutput, speed);

    }

    public void setVelocity(double velocity) {

        leftFalcon.set(TalonFXControlMode.Velocity, velocity);

    }

    public void setColumnSpeed(double speed) {

        frontColumnTalonSRX.set(ControlMode.PercentOutput, speed);
    }
}