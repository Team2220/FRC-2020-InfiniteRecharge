package frc.robot;

import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Tower;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake.Position;
import frc.robot.subsystems.Shooter.HoodPosition;
import frc.robot.util.xbox.XboxController;
import frc.robot.util.xbox.XboxController.Button;
import frc.robot.util.xbox.XboxController.Dpad;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.climber.ClimbWithButton;
import frc.robot.commands.climber.ClimbWithButton.Side;
import frc.robot.commands.hopper.RunHopper;
import com.revrobotics.CANSparkMax.IdleMode;
import frc.robot.commands.intake.IntakeSetPosition;
import frc.robot.commands.intake.RunIntake;
import frc.robot.commands.shooter.RunShooter;
import frc.robot.commands.shooter.RunTower;
import frc.robot.commands.shooter.ShootAndDynamicFeed;
import frc.robot.commands.shooter.ShootWithVelocity;
import frc.robot.commands.shooter.ShooterSetPosition;
import frc.robot.shuffleboard.DebugTab;
import frc.robot.shuffleboard.DemoTab;
import frc.robot.shuffleboard.DriverTab;

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
  private  final Climber climber;
  private  final DriveTrain drivetrain;
  private final Hopper hopper;
  private final Intake intake;
  private final Shooter shooter;
  private final Tower tower;



  // Driver controllers
  private final XboxController driverController = new XboxController(0);
  private final XboxController manipulatorController = new XboxController(1);

  // Shuffleboard TODO causes errors
  private final DebugTab debugTab = new DebugTab(driverController);
  private final DemoTab demoTab = new DemoTab();
  private final DriverTab driverTab = new DriverTab();


  // Singleton constructor
  private RobotContainer() {
    
    // Initialize robot subsytems
    drivetrain = new DriveTrain(demoTab);
    demoTab.setDifferentialDrive(drivetrain.getDifferentialDrive());

    shooter = new Shooter();
    intake = new Intake();
    hopper = new Hopper();
    climber = new Climber();
    tower = new Tower();

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
    return getInstance().manipulatorController;
  }

  /**
   * Nest all control binds inside this method. Takes place of previous year's OI
   * class.
   */
  private void setBinds() {
    // No official binds have been set yet. Keep them out of master.
    manipulatorController.getButton(Button.A).whileHeld(new RunHopper(hopper));
    manipulatorController.getButton(Button.A).whileHeld(new RunIntake(intake));
    manipulatorController.getButton(Button.Y).whileHeld(new ShootAndDynamicFeed(shooter, tower));
    manipulatorController.getDpad(Dpad.UP).whileHeld(new RunTower(Constants.TowerConstants.TOWER_POWER, tower));
    manipulatorController.getDpad(Dpad.DOWN).whileHeld(new RunTower(-Constants.TowerConstants.TOWER_POWER, tower));
    manipulatorController.getButton(Button.B).whileHeld(new ShootWithVelocity(ShooterConstants.SHOT_VELOCITY, shooter));
    // armManagement.getButton(Button.B).whenPressed(new ShootInventory(ShooterConstants.SHOT_VELOCITY, 3, shooter, hopper, tower));
    manipulatorController.getButton(Button.RIGHT_BUMPER).whenPressed(new IntakeSetPosition(Position.EXTENDED, intake));
    manipulatorController.getButton(Button.LEFT_BUMPER).whenPressed(new IntakeSetPosition(Position.RETRACTED, intake));
    manipulatorController.getButton(Button.X).whileHeld(new RunShooter(1, shooter));
    driverController.getButton(Button.X).whileHeld(new ClimbWithButton(ClimbWithButton.Position.EXTENDED,Side.LEFT, climber));
    driverController.getButton(Button.Y).whileHeld(new ClimbWithButton(ClimbWithButton.Position.RETRACTED,Side.LEFT, climber));
    driverController.getButton(Button.A).whileHeld(new ClimbWithButton(ClimbWithButton.Position.EXTENDED,Side.RIGHT, climber));
    driverController.getButton(Button.B).whileHeld(new ClimbWithButton(ClimbWithButton.Position.RETRACTED,Side.RIGHT, climber));
    manipulatorController.getDpad(Dpad.LEFT).whileHeld(new ShooterSetPosition(HoodPosition.RETRACTED, shooter));
    manipulatorController.getDpad(Dpad.RIGHT).whileHeld(new ShooterSetPosition(HoodPosition.EXTENDED, shooter));
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
