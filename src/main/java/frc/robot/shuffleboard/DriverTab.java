package frc.robot.shuffleboard;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class DriverTab {
    private ShuffleboardTab tab = Shuffleboard.getTab("Driver");

    public DriverTab(){
           
        MjpegServer server = CameraServer.getInstance().addServer("USB server 0");
        UsbCamera camera = new UsbCamera("USB Camera 0", 0);
        CameraServer.getInstance().addCamera(camera);
        server.setSource(camera);
        server.getProperty("compression").set(10);
        server.getProperty("default_compression").set(10);
        server.getProperty("width").set(320);
        server.getProperty("height").set(240);
        camera.setResolution(320, 240);
        server.getProperty("fps").set(22);
        camera.setFPS(22);
        tab.add(camera).withSize(7, 7).withPosition(0, 0);
    
    }
}
