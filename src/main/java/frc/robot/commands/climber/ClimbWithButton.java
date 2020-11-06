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

    public enum Side {
        LEFT, RIGHT
    }

    private final Position position;
    private final Climber climber;
    private final Side side;

    public ClimbWithButton(Position position, Side side, Climber climber) {

        this.climber = climber;
        this.position = position;
        this.side = side;
        //addRequirements(climber);

    }

    @Override
    public void initialize() {

        switch (position) {
        case EXTENDED:
            switch (side) {

            case LEFT:
                climber.setLeft(0.5);
                break;

            case RIGHT:
                climber.setRight(0.5);
                break;
            }
            break;
        case RETRACTED:
            switch (side) {

            case LEFT:
                climber.setLeft(-0.5);
                break;

            case RIGHT:
                climber.setRight(-0.5);
                break;
            }
            break;

        }

    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        switch (side) {

        case LEFT:
            climber.setLeft(0);
            break;

        case RIGHT:
            climber.setRight(0);
            break;
        }
    }

}
