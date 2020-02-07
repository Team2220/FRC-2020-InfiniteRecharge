package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/** 
* @author vish O_O: strong supporter of R22_D20
*/

public class Intake extends SubsystemBase {

enum DeployState {
  RETRACTED,
  EXTENDED
}

private TalonSRX talon = new TalonSRX(Constants.Intake.TALON);
private DoubleSolenoid solenoid = new DoubleSolenoid(Constants.Intake.SOLENOID_FORWARD, Constants.Intake.SOLENOID_REVERSE);
  public Intake() {

  }

  @Override
  public void periodic() {
    
  }

public void setSpeed(double demand) {
  talon.set(ControlMode.PercentOutput, demand);
}

public void setPosition(DeployState deploy) {
  switch (deploy) {
    case EXTENDED: 
    solenoid.set(Value.kForward);
    break;
    case RETRACTED:
    solenoid.set(Value.kReverse);
    break;
  }
}

}
