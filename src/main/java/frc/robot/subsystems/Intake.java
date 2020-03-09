package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.intake.IntakeXbox;

/**
 * @author vish: strong supporter of R22_D20
 */

public class Intake extends SubsystemBase {

  public enum Position {
    RETRACTED, EXTENDED
  }

  private TalonSRX talon = new TalonSRX(Constants.IntakeConstants.TALON);
  private DoubleSolenoid solenoid = new DoubleSolenoid(Constants.IntakeConstants.SOLENOID_FORWARD,
      Constants.IntakeConstants.SOLENOID_REVERSE);

  private static Intake instance;

  public Intake() {

    setDefaultCommand(new IntakeXbox(this));
    talon.configFactoryDefault();
    talon.configContinuousCurrentLimit(15);
    talon.setInverted(InvertType.InvertMotorOutput);

  }

  @Override
  public void periodic() {

  }

  /**
     * Singleton instance getter method.
     * 
     * @return Returns the singleton object for the intake.
     */
    public static Intake getInstance() {
      if (instance == null) {
          instance = new Intake();
      }
      return instance;
  }

  public void setPower(double demand) {
    talon.set(ControlMode.PercentOutput, demand);
  }

  public void setPosition(Position deploy) {
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
