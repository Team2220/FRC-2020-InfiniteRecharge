package frc.robot.commands.transfer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Transfer;

/**
 * TransferWithJoystick
 */
public class TransferWithButton extends CommandBase {

    private final Transfer transfer;

    public TransferWithButton(Transfer transfer) {

        this.transfer = transfer;
        addRequirements(transfer);
    }

    @Override
    public void initialize() {
        super.initialize();
        transfer.set(0.5);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        transfer.set(0);
    }

}