package frc.robot.shuffleboard;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.util.xbox.XboxController;

public class DebugTab {

    private ShuffleboardTab tab = Shuffleboard.getTab("Debug");

    public DebugTab(XboxController driverController){

        tab.addNumber("L Joystick Value", () -> driverController.getY(Hand.kLeft)).withPosition(3, 1);
        tab.addNumber("R Joystick Value", () -> driverController.getX(Hand.kRight)).withPosition(3, 2);
    }

}