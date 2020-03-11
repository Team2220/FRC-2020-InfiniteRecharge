package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;

/**
 * Don't touch this class. Code should occur in the Robot class or elsewhere.
 * This class's purpose is to run the Robot class and set everything up for us.
 * 
 * @author FIRST
 */
public final class Main {

  private Main() {
  }

  public static void main(String... args) {
    RobotBase.startRobot(Robot::new);
  }
}
