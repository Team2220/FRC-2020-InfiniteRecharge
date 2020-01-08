package frc.robot;

import frc.robot.commands.drivetrain.XboxDrive;
import frc.robot.subsystems.TwilightHorse;

public class RobotContainer {

  // Singleton instance
  private static RobotContainer instance;

  // Drivetrain subsystem
  private TwilightHorse drivetrain;

  // Singleton constructor
  private RobotContainer() {
    /*
     * Checks that this class is not initialized until the robot is on, or else
     * subsystems and commands cannot be properly initialized. The following if
     * throws a runtime exception if the instance exists before the robot is turned
     * on.
     */
    if (!Robot.isReal()) {
      throw new RuntimeException("The 'setBinds' method has been called before the robot was started!");
    }

    // Initialize drivetrain subsystem
    drivetrain = TwilightHorse.getInstance();

    // Must happen last in constructor
    setBinds();
  }

  /**
   * Singleton class instance accessor.
   * 
   * @return Returns the singleton object for the Robot Container class.
   */
  public static RobotContainer getInstance() {
    if (instance == null) {
      instance = new RobotContainer();
    }
    return instance;
  }

  /**
   * Nest all control binds inside this method. Takes place of 2019's OI class.
   */
  private void setBinds() {
    // Set up drivetrain default command
    XboxDrive xboxDrive = new XboxDrive(drivetrain);
    drivetrain.setDefaultCommand(xboxDrive);
  }
}
