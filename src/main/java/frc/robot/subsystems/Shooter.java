package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
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
    private static double velocitySetPoint = 8;
    private static SimpleMotorFeedforward fCalc = new SimpleMotorFeedforward(kS, kV, kA);
    private static double P = 0.000545;
    private static double I = 0;
    private static double D = 0;
    private static double F = fCalc.calculate(velocitySetPoint);
    // private static final int MAX_VEL = 10500;
    // private static final int MAX_ACCEL = 350;

    private static Shooter instance;

    TalonFX leftFalcon, rightFalcon;
    TalonSRX frontColumnTalonSRX, backColumnTalonSRX;

    ShuffleboardTab shooterTab = Shuffleboard.getTab("shooter");

    private Shooter() {

        leftFalcon = new TalonFX(ShooterConstants.LEFT_FALCON);
        rightFalcon = new TalonFX(ShooterConstants.RIGHT_FALCON);
        frontColumnTalonSRX = new TalonSRX(ShooterConstants.FRONT_TOWER);
        backColumnTalonSRX = new TalonSRX(ShooterConstants.BACK_TOWER);

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

        leftFalcon.config_kP(0, P);
        leftFalcon.config_kI(0, I);
        leftFalcon.config_kD(0, D);
        leftFalcon.config_kF(0, F);
        // leftFalcon.configMotionAcceleration(MAX_ACCEL);
        // leftFalcon.configMotionCruiseVelocity(MAX_VEL);

        leftFalcon.enableVoltageCompensation(true);
        rightFalcon.enableVoltageCompensation(true);
        leftFalcon.configVoltageCompSaturation(9);
        rightFalcon.configVoltageCompSaturation(9);

        setDefaultCommand(new ShootWithJoystick(this));
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

    public void setPower(double speed) {
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