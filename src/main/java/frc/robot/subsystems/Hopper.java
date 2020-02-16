package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Hopper extends SubsystemBase {
  private TalonSRX leftTalon = new TalonSRX(Constants.Hopper.LEFT_TALON);
  private TalonSRX rightTalon = new TalonSRX(Constants.Hopper.RIGHT_TALON);

  public Hopper() {

    rightTalon.follow(leftTalon);

  }

  @Override
  public void periodic() {

  }

  public void set(double demand) {

    leftTalon.set(ControlMode.PercentOutput, demand);

  }
}
