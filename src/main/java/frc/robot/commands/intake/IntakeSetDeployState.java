package frc.robot.commands.intake;

import frc.robot.util.xbox.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake;

/** 
* @author vish: strong supporter of R22_D20
*/

public class IntakeSetDeployState extends CommandBase {

    private final Intake.DeployState deploy;
    private final Intake intake;

    public IntakeSetDeployState(Intake.DeployState deploy, Intake intake) {
        this.deploy = deploy;
        this.intake = intake;
        addRequirements();
    }
    
    @Override
    public void execute() {
        intake.setPosition(deploy);
        /*
        XboxController xb = RobotContainer.getManipulatorController();
        double intakePower = xb.getY(Hand.kRight);
        intake.setSpeed(intakePower);
        */
    }
}