package frc.robot;

import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.TwilightHorse;
import frc.robot.subsystems.Intake.Position;
import frc.robot.util.xbox.XboxController;
import frc.robot.util.xbox.XboxController.Button;
import frc.robot.commands.intake.IntakeSetPosition;
import frc.robot.commands.intake.RunIntake;
import frc.robot.commands.hopper.HopperWithButton;

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

  // Mechanism subsystems
  private final Intake intake;
  private final Shooter shooter;
  private final Hopper hopper;
  private final Climber climber;

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

    shooter = new Shooter();
    intake = new Intake();
    hopper = new Hopper();
    climber = new Climber();

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

    // Extend and retract intake manually
    armManagement.getButton(Button.RIGHT_BUMPER).whenPressed(new IntakeSetPosition(Position.EXTENDED, intake));
    armManagement.getButton(Button.LEFT_BUMPER).whenPressed(new IntakeSetPosition(Position.RETRACTED, intake));

    // Run intake and hopper automatically
    armManagement.getButton(Button.A).whileHeld(new RunIntake(intake));
    armManagement.getButton(Button.A).whileHeld(new HopperWithButton(hopper));

    // Run tower
    armManagement.getButton(Button.X).whileHeld(new RunTower(hopper));
  }
}
