package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Intake.Position;

public class RunIntake extends CommandBase {
    
    private final Intake intake;

    public RunIntake(Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        intake.setPosition(Position.EXTENDED);
    }
    
    @Override
    public void execute() {
        intake.setPower(Constants.IntakeConstants.INTAKE_POWER);
    }

    @Override
    public void end(boolean interrupted) {
        intake.setPosition(Position.RETRACTED);
    }
}