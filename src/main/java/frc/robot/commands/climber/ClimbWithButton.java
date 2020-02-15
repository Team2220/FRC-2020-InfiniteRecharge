package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

/**
 * @author vish: strong supporter of R22_D20
 */

public class ClimbWithButton extends CommandBase {

    public enum Position {
        RETRACTED, EXTENDED
    }

    private final Position position;
    private final Climber climber;

    public ClimbWithButton(Position position ,Climber climber) {

        this.climber = climber;
        this.position = position;
        addRequirements(climber);

    }

    @Override
    public void initialize() {

        switch (position) {
        case EXTENDED:
            climber.set(0.5);
            break;
        case RETRACTED:
            climber.set(-0.5);
            break;

        }

    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        climber.set(0);
    }

}
