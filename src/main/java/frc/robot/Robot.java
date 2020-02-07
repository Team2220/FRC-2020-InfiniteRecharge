package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * Main robot class. Most things are handled by the Robot Container class, but
 * command schedulers and autonomous command schedulers go in the respective
 * methods.
 * 
 * @author 2220
 */
public class Robot extends TimedRobot {

  // Robot Container instance variable
  private RobotContainer robot;

  @Override
  public void robotInit() {
    robot = RobotContainer.getInstance();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
  }

  CANSparkMax leftMaster, leftSlave, rightMaster, rightSlave;
  XboxController xb = new XboxController(0);

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
    /**
     * CHECK IDs BEFORE RUNNING!!
     */
    leftMaster = new CANSparkMax(0, MotorType.kBrushless);
    leftSlave = new CANSparkMax(1, MotorType.kBrushless);
    rightMaster = new CANSparkMax(2, MotorType.kBrushless);
    rightSlave = new CANSparkMax(3, MotorType.kBrushless);

    leftMaster.setInverted(false);
    leftSlave.setInverted(false);
    rightMaster.setInverted(false);
    rightSlave.setInverted(false);

    // leftSlave.follow(leftMaster);
    // rightSlave.follow(rightMaster);
  }

  @Override
  public void testPeriodic() {
    double s = 0.95;
    leftMaster.set(s);
    // leftSlave.set(s);
    rightMaster.set(s);
    // rightSlave.set(s);

    // After directions are set properly
    // leftMaster.set(xb.getY(Hand.kLeft) * 0.95);
    // leftMaster.set(xb.getY(Hand.kRight) * 0.95);
  }
}
