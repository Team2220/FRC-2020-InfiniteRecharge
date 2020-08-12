package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Hopper extends SubsystemBase {

  private TalonSRX leftTalon = new TalonSRX(Constants.HopperConstants.LEFT_TALON);
  private TalonSRX rightTalon = new TalonSRX(Constants.HopperConstants.RIGHT_TALON);

  private static Hopper instance;

  private Hopper() {

    leftTalon.configFactoryDefault();
    rightTalon.configFactoryDefault();
    leftTalon.configContinuousCurrentLimit(7);
    rightTalon.configContinuousCurrentLimit(7);
    leftTalon.setInverted(false);
    rightTalon.setInverted(true);

  }

  @Override
  public void periodic() {

  }

  /**
   * Singleton instance getter method.
   * 
   * @return Returns the singleton object for the hopper.
   */
  public static Hopper getInstance() {
    if (instance == null) {
      instance = new Hopper();
    }
    return instance;
  }

  public void setPower(double demand) {
    leftTalon.set(ControlMode.PercentOutput, demand * 0.9);
    rightTalon.set(ControlMode.PercentOutput, demand * 1.1);
  }
}
