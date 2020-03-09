package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
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
  // Test
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
    robot.disabledInit();
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

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }
}
