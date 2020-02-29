package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Hopper extends SubsystemBase {
  private TalonSRX leftTalon = new TalonSRX(Constants.HopperConstants.LEFT_TALON);
  private TalonSRX rightTalon = new TalonSRX(Constants.HopperConstants.RIGHT_TALON);

  public Hopper() {

    leftTalon.configFactoryDefault();
    rightTalon.configFactoryDefault();
    leftTalon.configContinuousCurrentLimit(7);
    rightTalon.configContinuousCurrentLimit(7);
    rightTalon.follow(leftTalon);
    rightTalon.setInverted(InvertType.OpposeMaster);

  }

  @Override
  public void periodic() {

  }

  public void setPower(double demand) {

    leftTalon.set(ControlMode.PercentOutput, demand);

  }
}
