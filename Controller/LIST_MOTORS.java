import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class ListServos extends LinearOpMode {
    @Override
    public void runOpMode() {

        waitForStart();

        telemetry.addData("All Available Engines");
        telemetry.addData("Please connect power to motor for it to show up here.");
      
        // Initialize hardware
        HardwareMap hardwareMap = hardwareMap;

        // Get all servos from the hardware map
        for (String deviceName : hardwareMap.getAll(Servo.class)) {
            Servo servo = hardwareMap.get(Servo.class, deviceName);
            telemetry.addData("Servo: ", deviceName);
        }

        telemetry.update();
    }
}
