package frc.robot.shuffleboard;

import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class DemoTab {
    private final ShuffleboardTab tab = Shuffleboard.getTab("Demo");
    private NetworkTableEntry speedModifierEntry;
    private DifferentialDrive drive;

    public DemoTab() {
        speedModifierEntry = tab.add("Drive train speed modifier", 1.0 )
        .withSize(2, 2)
        .withPosition(0, 0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("Min", 0, "Max", 1))
        .getEntry();

    }

    public double getSpeedModifier(){
        return speedModifierEntry.getDouble(1);
    }

    public void setDifferentialDrive(DifferentialDrive drive) {
        this.drive = drive;
    }
}