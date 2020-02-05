
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
private TalonSRX talon = new TalonSRX(Constants.Intake.TALON);
private DoubleSolenoid solenoid = new DoubleSolenoid(Constants.Intake.SOLENOID_FORWARD, Constants.Intake.SOLENOID_REVERSE);
  public Intake() {

  }

  @Override
  public void periodic() {
    
  }

public void set(double demand) {
  talon.set(ControlMode.PercentOutput, demand);
}


}
