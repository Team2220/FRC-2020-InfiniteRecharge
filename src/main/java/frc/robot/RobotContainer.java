package frc.robot;

import frc.robot.commands.drivetrain.XboxDrive;
import frc.robot.subsystems.TwilightHorse;
import frc.robot.util.XboxWrapper;

public class RobotContainer {

  // Singleton instance
  private static RobotContainer instance;

  // Drivetrain subsystem
  private TwilightHorse drivetrain;

  // Driver controllers
  private final XboxWrapper driverController = new XboxWrapper(0);
  private final XboxWrapper armManagement = new XboxWrapper(1);

  // Singleton constructor
  private RobotContainer() {
    /*
     * Checks that this class is not initialized until the robot is on, or else
     * subsystems and commands cannot be properly initialized. The following if
     * throws a runtime exception if the instance exists before the robot is turned
     * on.
     */
    if (!Robot.isReal()) {
      throw new RuntimeException("Error: Attempting to initialize robot code before the robot is on");
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

  public static XboxWrapper getDriverController() {
    return getInstance().driverController;
  }

  public static XboxWrapper getManipulatorController() {
    return getInstance().armManagement;
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
