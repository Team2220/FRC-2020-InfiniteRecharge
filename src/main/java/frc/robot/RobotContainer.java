package frc.robot;

import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Tower;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.TwilightHorse;
import frc.robot.util.xbox.XboxController;
import frc.robot.util.xbox.XboxController.Button;
import frc.robot.util.xbox.XboxController.Dpad;
import frc.robot.Constants.ShooterConstants;
import frc.robot.Constants.TowerConstants;
import frc.robot.commands.shooter.RunTower;
import frc.robot.commands.shooter.ShootAndDynamicFeed;
import frc.robot.commands.shooter.ShootInventory;
import frc.robot.commands.shooter.ShootWithVelocity;
import frc.robot.shuffleboard.DebugTab;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.Command;

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

  // Robot subsystem members
  private Climber climber;
  private TwilightHorse drivetrain;
  private Hopper hopper;
  private Intake intake;
  private Shooter shooter;
  private Tower tower;

  // Driver controllers
  private final XboxController driverController = new XboxController(0);
  private final XboxController armManagement = new XboxController(1);

  // Shuffleboard TODO causes errors
  // private final DebugTab debugTab = new DebugTab(driverController);

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

    // Initialize robot subsytems
    climber = Climber.getInstance();
    drivetrain = TwilightHorse.getInstance();
    hopper = Hopper.getInstance();
    intake = Intake.getInstance();
    shooter = Shooter.getInstance();
    tower = Tower.getInstance();

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
    // No official binds have been set yet. Keep them out of master.
    armManagement.getButton(Button.Y).whileHeld(new ShootAndDynamicFeed(shooter, tower));
    armManagement.getDpad(Dpad.UP).whileHeld(new RunTower(TowerConstants.TOWER_POWER, tower));
    armManagement.getDpad(Dpad.DOWN).whileHeld(new RunTower(-TowerConstants.TOWER_POWER, tower));
    // armManagement.getButton(Button.X).whileHeld(new ShootWithVelocity(ShooterConstants.SHOT_VELOCITY, shooter));
    // armManagement.getButton(Button.B).whenPressed(new ShootInventory(ShooterConstants.SHOT_VELOCITY, 3, shooter, hopper, tower));
  }

  /**
   * Anything that subsytems need to do when the robot becomes disabled.
   */
  public void disabledInit() {

    // Coast drivetrain motors for easier manual movement
    if (drivetrain != null) {
      drivetrain.setIdleBehavior(IdleMode.kCoast);
    }
  }
}
