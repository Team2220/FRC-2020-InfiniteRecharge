package frc.robot.util;

import java.util.HashMap;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

/**
 * A wrapper class for the standard Xbox controller class that increases ease of
 * access.
 * 
 * @author Reece
 */
public class XboxWrapper {

    // Xbox controller object
    private final XboxController xb;

    // Notifier to stop controller rumbling
    private final Notifier stopRumble = new Notifier(() -> rumble(0));

    // Joystick deadzone variable
    private HashMap<Hand, Double> joystickDeadzones = new HashMap<>();

    // Trigger deadzone variable
    private double triggerDeadzone = 0.2;

    /**
     * Constructs an Xbox wrapper object.
     * 
     * @param port The port of Xbox controller on the driver station.
     */
    public XboxWrapper(int port) {
        xb = new XboxController(port);
        joystickDeadzones.put(Hand.kLeft, 0.05);
        joystickDeadzones.put(Hand.kRight, 0.05);
    }

    /**
     * Returns the unwrapped Xbox controller object.
     * 
     * @return The unwrapped Xbox controller object.
     */
    public XboxController getControllerObject() {
        return xb;
    }

    /**
     * Returns a joystick button off of the specified button.
     * 
     * @param button Which button to use.
     * @return A joystick button with specified button.
     */
    public JoystickButton getButton(Button button) {
        return new JoystickButton(xb, button.value);
    }

    public TriggerButton getTriggerButton(Hand hand) {
        return new TriggerButton(hand);
    }

    /**
     * Returns a POV button off of the specified d-pad angle.
     * 
     * @param dpad Which d-pad direction to use.
     * @return Returns a POV button with specified d-pad direction.
     */
    public POVButton getDpad(Dpad dpad) {
        return new POVButton(xb, dpad.angle);
    }

    /**
     * Gets the X axis value of the stick on the specified hand side.
     * 
     * @param hand Which hand side.
     * @return Returns the X axis value from the specified side.
     */
    public double getX(Hand hand) {
        double deadzone = joystickDeadzones.get(hand);
        double x = xb.getX(hand);
        return Math.abs(x) > deadzone ? x : 0;
    }

    /**
     * Gets the Y axis value of the stick on the specified hand side.
     * 
     * @param hand Which hand side.
     * @return Returns the Y axis value from the specified side.
     */
    public double getY(Hand hand) {
        double deadzone = joystickDeadzones.get(hand);
        double y = xb.getY(hand);
        return Math.abs(y) > deadzone ? y : 0;
    }

    /**
     * Gets the value of the trigger on the specified hand side.
     * 
     * @param hand Which hand side.
     * @return Returns the trigger value from the specified side.
     */
    public double getTrigger(Hand hand) {
        double trigger = xb.getTriggerAxis(hand);
        return trigger > triggerDeadzone ? trigger : 0;
    }

    /**
     * Gets the deadzone value of joystick on the specified hand side.
     * 
     * @param hand Which hand side.
     * @return Returns the joystick deadzone value on the specified side.
     */
    public double getDeadzone(Hand hand) {
        return joystickDeadzones.get(hand);
    }

    /**
     * Sets the deadzone for the joysticks, or the minimum counted input.
     * 
     * @param hand     Which hand side to set the deadzone of.
     * @param deadzone The new deadzone for the joysticks.
     * @return The old deadzone.
     */
    public double setJoystickDeadzone(Hand hand, double deadzone) {
        double oldDeadzone = joystickDeadzones.get(hand);
        double newDeadzone;
        if (deadzone < 0) {
            newDeadzone = 0;
        } else if (deadzone > 1) {
            newDeadzone = 1;
        } else {
            newDeadzone = deadzone;
        }
        joystickDeadzones.put(hand, newDeadzone);
        return oldDeadzone;
    }

    /**
     * Sets the deadzone for the triggers, or the minimum counted input.
     * 
     * @param deadzone The new deadzone for the triggers.
     * @return The old deadzone.
     */
    public double setTriggerDeadzone(double deadzone) {
        double oldDeadzone = triggerDeadzone;
        if (deadzone < 0) {
            triggerDeadzone = 0;
        } else if (deadzone > 1) {
            triggerDeadzone = 1;
        } else {
            triggerDeadzone = deadzone;
        }
        return oldDeadzone;
    }

    private void rumble(double intensity) {
        xb.setRumble(RumbleType.kLeftRumble, intensity);
        xb.setRumble(RumbleType.kRightRumble, intensity);
    }

    public void rumbleFor(double millis, double intensity) {
        rumble(intensity);
        stopRumble.startSingle(millis * 1000);
    }

    /**
     * Toggle buttons on Xbox controller and their raw values.
     */
    public enum Button {
        A(1), B(2), X(3), Y(4), LEFT_BUMPER(5), RIGHT_BUMPER(6), BACK(7), START(8), LEFT_STICK(9), RIGHT_STICK(10);

        private final int value;

        Button(int value) {
            this.value = value;
        }
    }

    /**
     * D-pad positions on Xbox controller and their angle values.
     */
    public enum Dpad {
        UP(0), UP_RIGHT(45), RIGHT(90), DOWN_RIGHT(135), DOWN(180), DOWN_LEFT(225), LEFT(270), UP_LEFT(315);

        private final int angle;

        Dpad(int angle) {
            this.angle = angle;
        }
    }

    /**
     * A subclass of Button for the triggers on an xbox controller. Acts like any
     * other button; it is activated when the trigger is pressed past the threshold
     * of its deadzone.
     */
    public class TriggerButton extends edu.wpi.first.wpilibj2.command.button.Button {

        /* INSTANCE VARIABLES */

        // Hand side to get trigger value from
        private final Hand hand;

        /* CONSTRUCTOR */

        /**
         * Create a new trigger button with given xbox controller and hand side.
         * 
         * @param hand Hand side to get trigger value from.
         */
        private TriggerButton(Hand hand) {
            this.hand = hand;
        }

        /* IMPLEMENTED METHODS */

        /**
         * Implemented get method. Returns true if the trigger axis is greater than the
         * deadzone constant.
         */
        @Override
        public boolean get() {
            return xb.getTriggerAxis(hand) > triggerDeadzone;
        }
    }
}