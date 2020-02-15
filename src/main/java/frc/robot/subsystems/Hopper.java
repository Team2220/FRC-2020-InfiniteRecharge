package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.HopperConstants;

public class Hopper extends SubsystemBase {

  private TalonSRX leftHopper, rightHopper;
  private TalonSRX frontTower, backTower;

  public Hopper() {

    leftHopper = new TalonSRX(HopperConstants.LEFT_HOPPER);
    rightHopper = new TalonSRX(HopperConstants.RIGHT_HOPPER);
    frontTower = new TalonSRX(HopperConstants.FRONT_COLUMN);
    backTower = new TalonSRX(HopperConstants.BACK_COLUMN);

    leftHopper.configFactoryDefault();
    rightHopper.configFactoryDefault();
    frontTower.configFactoryDefault();
    backTower.configFactoryDefault();

    rightHopper.follow(leftHopper);
    backTower.follow(frontTower);

    leftHopper.setInverted(InvertType.None);
    rightHopper.setInverted(InvertType.OpposeMaster);

    frontTower.setInverted(InvertType.InvertMotorOutput);
    backTower.setInverted(InvertType.InvertMotorOutput);

    leftHopper.configOpenloopRamp(HopperConstants.RAMP_RATE);
    rightHopper.configOpenloopRamp(HopperConstants.RAMP_RATE);
    frontTower.configOpenloopRamp(HopperConstants.RAMP_RATE);
    backTower.configOpenloopRamp(HopperConstants.RAMP_RATE);

    leftHopper.setNeutralMode(HopperConstants.IDLE_BEHAVIOR);
    rightHopper.setNeutralMode(HopperConstants.IDLE_BEHAVIOR);
    frontTower.setNeutralMode(HopperConstants.IDLE_BEHAVIOR);
    backTower.setNeutralMode(HopperConstants.IDLE_BEHAVIOR);
  }

  @Override
  public void periodic() {

  }

  public void setHopper(double demand) {

    leftHopper.set(ControlMode.PercentOutput, demand);
  }

  public void setTower(double demand) {

    frontTower.set(ControlMode.PercentOutput, demand);
  }
}
