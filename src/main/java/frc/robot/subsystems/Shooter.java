package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.shooter.ShootWithJoystick;

/**
 * Shooter
 */

public class Shooter extends SubsystemBase {

    private static double kS = -0.027;
    private static double kV = 0.3;
    private static double kA = 0.0639;
    private static double velocitySetPoint = 2;
    // public static double kVelocity = velocitySetPoint * 2048 * (16.0 / 24);
    private static SimpleMotorFeedforward fCalc = new SimpleMotorFeedforward(kS, kV, kA);
    private static double P = 0.000545;
    private static double I = 0;
    private static double D = 0;
    private static double F = fCalc.calculate(velocitySetPoint);
    // private static final int MAX_VEL = 10500;
    // private static final int MAX_ACCEL = 350;

    TalonFX leftFalcon, rightFalcon;
    TalonSRX frontColumnTalonSRX, backColumnTalonSRX;

    ShuffleboardTab shooterTab = Shuffleboard.getTab("shooter");

    public Shooter() {

        leftFalcon = new TalonFX(ShooterConstants.LEFT_FALCON);
        rightFalcon = new TalonFX(ShooterConstants.RIGHT_FALCON);
        frontColumnTalonSRX = new TalonSRX(ShooterConstants.FRONT_COLUMN);
        backColumnTalonSRX = new TalonSRX(ShooterConstants.BACK_COLUMN);

        leftFalcon.configFactoryDefault();
        rightFalcon.configFactoryDefault();
        frontColumnTalonSRX.configFactoryDefault();
        backColumnTalonSRX.configFactoryDefault();

        frontColumnTalonSRX.configContinuousCurrentLimit(15);
        backColumnTalonSRX.configContinuousCurrentLimit(15);

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

        shooterTab.addNumber("left falcon velocity", () -> leftFalcon.getSelectedSensorVelocity());
        shooterTab.addNumber("right falcon velocity", () -> rightFalcon.getSelectedSensorVelocity());
        shooterTab.addNumber("left falcon position", () -> leftFalcon.getSelectedSensorPosition());
        shooterTab.addNumber("right falcon position", () -> rightFalcon.getSelectedSensorPosition());
        shooterTab.addNumber("velocity difference", () -> (rightFalcon.getSelectedSensorVelocity() - leftFalcon.getSelectedSensorVelocity()));
        shooterTab.addNumber("left falcon real rps", () -> (leftFalcon.getSelectedSensorVelocity() * 10 / 2048));

        leftFalcon.config_kP(0, P);
        leftFalcon.config_kI(0, I);
        leftFalcon.config_kD(0, D);
        leftFalcon.config_kF(0, 0);
        // leftFalcon.configMotionAcceleration(MAX_ACCEL);
        // leftFalcon.configMotionCruiseVelocity(MAX_VEL);

        leftFalcon.enableVoltageCompensation(true);
        rightFalcon.enableVoltageCompensation(true);
        leftFalcon.configVoltageCompSaturation(9);
        rightFalcon.configVoltageCompSaturation(9);

        setDefaultCommand(new ShootWithJoystick(this));
    }

    // private double nativeUnitsToFlyWheelRPS(double nativeUnits) {
    //     double value = nativeUnits / 2048; // divide by encoder counts per rotation to get rotations/100ms
    //     value = value * 10; // multiply by 10 to 
    // }

    public void setPower(double speed) {
        if (Math.abs(speed) > 0.9) {
            speed = Math.signum(speed) * 0.9;
        }
        leftFalcon.set(TalonFXControlMode.PercentOutput, speed);
    }

    public void setVelocity(double velocity) {
        // double velocity = rps * 2048 * (16.0 / 24);
        leftFalcon.set(TalonFXControlMode.Velocity, velocity);
    }

    public void setColumnSpeed(double speed) {
        frontColumnTalonSRX.set(ControlMode.PercentOutput, speed);
    }
}