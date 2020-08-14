package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

/**
 * @author vish: strong supporter of R22_D20
 */

public class Climber extends SubsystemBase {

    private TalonFX rightTalon = new TalonFX(ClimberConstants.RIGHT_FALCON);
    private TalonFX leftTalon = new TalonFX(ClimberConstants.LEFT_FALCON);

    public Climber() {
        rightTalon.follow(leftTalon);
    }

    public void set(double speed) {
        leftTalon.set(ControlMode.PercentOutput, speed);     
    }
}