package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Hopper extends SubsystemBase {
  private TalonSRX talon = new TalonSRX(Constants.Hopper.TALON);
  
  public Hopper() {
    
  }

  @Override
  public void periodic() {

  }

  public void set(double demand) {
      talon.set(ControlMode.PercentOutput, demand);
  }
}
