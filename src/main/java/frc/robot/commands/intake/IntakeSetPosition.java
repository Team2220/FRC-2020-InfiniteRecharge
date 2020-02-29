package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Intake;

/**
 * @author vish: strong supporter of R22_D20
 */

public class IntakeSetPosition extends InstantCommand {

    private final Intake.Position position;
    private final Intake intake;

    public IntakeSetPosition(Intake.Position position, Intake intake) {
        this.position = position;
        this.intake = intake;
        addRequirements();
    }

    @Override
    public void execute() {
        intake.setPosition(position);
        /*
         * XboxController xb = RobotContainer.getManipulatorController(); double
         * intakePower = xb.getY(Hand.kRight); intake.setSpeed(intakePower);
         */
    }
}