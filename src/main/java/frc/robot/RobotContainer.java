package frc.robot;

import com.revrobotics.CANSparkMax.IdleMode;

import frc.robot.subsystems.TwilightHorse;
import frc.robot.util.xbox.XboxController;

/**
 * Robot Container is a singleton class where all the subsystems are
 * centralized. This class also handles controller binding; a replacement for
 * OI.
 * 
 * @author 2220
 */
public class RobotContainer {

  // Singleton instance
  private static RobotContainer instance;

  // Drivetrain subsystem
  private TwilightHorse drivetrain;

  // Driver controllers
  private final XboxController driverController = new XboxController(0);
  private final XboxController armManagement = new XboxController(1);

  // Singleton constructor
  private RobotContainer() {
    /**
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
   * Singleton instance getter method.
   * 
   * @return Returns the singleton object for the Robot Container.
   */
  public static RobotContainer getInstance() {
    if (instance == null) {
      instance = new RobotContainer();
    }
    return instance;
  }

  /**
   * Getter for the driver controller.
   * 
   * @return The driver controller.
   */
  public static XboxController getDriverController() {
    return getInstance().driverController;
  }

  /**
   * Getter for the manipulator controller. "Arm management."
   * 
   * @return The manipulator controller.
   */
  public static XboxController getManipulatorController() {
    return getInstance().armManagement;
  }

  /**
   * Nest all control binds inside this method. Takes place of previous year's OI
   * class.
   */
  private void setBinds() {
  }

  /**
   * Anything that subsytems need to do when the robot becomes disabled.
   */
  public void disabledInit() {

    // Coast drivetrain motors for easier manual movement
    drivetrain.setIdleBehavior(IdleMode.kCoast);
  }
}
