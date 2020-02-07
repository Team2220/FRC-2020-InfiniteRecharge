package frc.robot.commands.intake;

import frc.robot.util.xbox.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake;

/** 
* @author vish: strong supporter of R22_D20
*/

public class IntakeXbox extends CommandBase {

    private final Intake intake;
    private final XboxController xb;

    public IntakeXbox(Intake intake) {
        xb = RobotContainer.getManipulatorController();
        this.intake = intake;
        addRequirements(intake);
    }
    
    @Override
    public void execute() {
        double intakePower = xb.getY(Hand.kRight);
        intake.setSpeed(intakePower);
    }
}
