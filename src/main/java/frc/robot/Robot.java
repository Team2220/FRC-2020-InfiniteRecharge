package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SuppliedValueWidget;
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
    // robot.disabledInit();
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

  // TalonFX shooter = new TalonFX(7);
  // TalonFX follower = new TalonFX(8); 
  // TalonSRX ft = new TalonSRX(3);
  // TalonSRX bt = new TalonSRX(6);
  // ShuffleboardTab shooterTab = Shuffleboard.getTab("shooter");

  // NetworkTableEntry fEntry = shooterTab.add("F", 0.0692).getEntry(); // good f 0.0692
  // NetworkTableEntry pEntry = shooterTab.add("P", 0.1).getEntry(); // good p 0.1
  // NetworkTableEntry vEntry = shooterTab.add("Vel", 13490).getEntry(); // good ~13490
  // NetworkTableEntry dEntry = shooterTab.add("D", 2.5).getEntry(); // good 2.5
  // NetworkTableEntry tEntry = shooterTab.add("Tower Speed", 0.6).getEntry();

  // SuppliedValueWidget<Double> battery = shooterTab.addNumber("Battery", () -> RobotController.getBatteryVoltage());

  // double lastVel = 0;

  // SuppliedValueWidget<Double> a = shooterTab
  //     .addNumber("left falcon velocity", () -> shooter.getSelectedSensorVelocity()).withWidget(BuiltInWidgets.kGraph);
  // SuppliedValueWidget<Double> b = shooterTab
  //     .addNumber("right falcon velocity", () -> follower.getSelectedSensorVelocity()).withWidget(BuiltInWidgets.kGraph);
  // SuppliedValueWidget<Double> c = shooterTab
  //     .addNumber("difference", () -> (shooter.getSelectedSensorVelocity() - follower.getSelectedSensorVelocity()))
  //     .withWidget(BuiltInWidgets.kGraph);

  // SuppliedValueWidget<Double> d = shooterTab.addNumber("dVel", () -> {
  //   double d = shooter.getSelectedSensorVelocity() - lastVel;
  //   lastVel = shooter.getSelectedSensorVelocity();
  //   return d;
  // });

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();

    // ft.configFactoryDefault();
    // ft.setInverted(false);
    // bt.configFactoryDefault();
    // bt.setInverted(false);

    // shooter.configFactoryDefault();
    // follower.configFactoryDefault();
    // shooter.setNeutralMode(NeutralMode.Coast);
    // follower.setNeutralMode(NeutralMode.Coast);

    // follower.follow(shooter);

    // shooter.setInverted(TalonFXInvertType.Clockwise);
    // follower.setInverted(TalonFXInvertType.CounterClockwise);

    // shooter.configOpenloopRamp(0.5);
    // follower.configOpenloopRamp(0.5);

    // shooter.enableVoltageCompensation(true);
    // follower.enableVoltageCompensation(true);

    // shooter.configVoltageCompSaturation(9);
    // follower.configVoltageCompSaturation(9);
  }

  @Override
  public void testPeriodic() {
    // double v = vEntry.getDouble(0);
    // shooter.set(TalonFXControlMode.Velocity, v);
    // double f = fEntry.getDouble(0);
    // double p = pEntry.getDouble(0);
    // double d = dEntry.getDouble(0);
    // double t = tEntry.getDouble(0);
    // shooter.config_kP(0, p);
    // shooter.config_kI(0, 0);
    // shooter.config_kD(0, d);
    // shooter.config_kF(0, f);

    // ft.set(ControlMode.PercentOutput, t);
    // bt.set(ControlMode.PercentOutput, t);
  }
}
