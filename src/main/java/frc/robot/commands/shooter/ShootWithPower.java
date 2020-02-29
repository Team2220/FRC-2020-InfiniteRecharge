package frc.robot.commands.shooter;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

/**
 * ShootWithPower
 */
public class ShootWithPower extends CommandBase {

    private final Shooter shooter;
    private final Intake intake;
    private final Hopper hopper;
    private NetworkTableEntry shotPower;

    public ShootWithPower(Shooter shooter, Intake intake, Hopper hopper) {
        this.shooter = shooter;
        this.intake = intake;
        this.hopper = hopper;
        addRequirements(shooter);
        ShuffleboardTab tab = Shuffleboard.getTab("shooter");
        shotPower = tab.add("shooter power", 0).getEntry();
    }

    @Override
    public void execute() {
        shooter.setPower(shotPower.getDouble(0));
        hopper.setPower(0.5);
        intake.setPower(0.5);
        shooter.setColumnSpeed(0.6);
    }

    @Override
    public void end(boolean interrupted) {
        // TODO Auto-generated method stub
        super.end(interrupted);
        shooter.setPower(0);
        hopper.setPower(0);
        intake.setPower(0);
        shooter.setColumnSpeed(0);
    }
}