package frc.robot.util;

import edu.wpi.first.wpilibj.DigitalInput;


/**
 * @author vish: strong supporter of R22_D20
 */

public class PhotoelectricSensor {

    private DigitalInput digitalInput;
    private boolean inverted;
    public PhotoelectricSensor (int port, boolean inverted) {

        digitalInput = new DigitalInput (port);
        this.inverted = inverted;
        

    }

    public PhotoelectricSensor (int port) {

        this (port, false);

    }

    public boolean get () {

        if (inverted) {

            return ! digitalInput.get();

        }

        else {

            return digitalInput.get();

        }

    }

}