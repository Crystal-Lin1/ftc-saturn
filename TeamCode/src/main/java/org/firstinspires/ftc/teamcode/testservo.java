package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Six Servo Tester", group = "Test")
public class testservo extends LinearOpMode {

    // Configure the hardware map names for your servos
    private static final String[] SERVO_NAMES = {"servo0", "servo1", "servo2", "servo3", "servo4", "servo5"};
    private Servo[] servos = new Servo[6];

    @Override
    public void runOpMode() {
        // Initialize all 6 servos
        for (int i = 0; i < servos.length; i++) {
            try {
                servos[i] = hardwareMap.get(Servo.class, SERVO_NAMES[i]);
                telemetry.addData("Status", "Initialized: " + SERVO_NAMES[i]);
            } catch (Exception e) {
                telemetry.addData("Status", "Error: '" + SERVO_NAMES[i] + "' not found!");
            }
        }

        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            double targetPosition = 0.5; // Default middle position
            String stateStr = "Centered (0.5)";

            // Press and hold A to send all servos to maximum position (1.0)
            // Press and hold B to send all servos to minimum position (0.0)
            if (gamepad1.a) {
                targetPosition = 1.0;
                stateStr = "Max Position (1.0)";
            } else if (gamepad1.b) {
                targetPosition = 0.0;
                stateStr = "Min Position (0.0)";
            }

            // Apply position and display telemetry for all initialized servos
            telemetry.addData("Global State", stateStr);
            for (int i = 0; i < servos.length; i++) {
                if (servos[i] != null) {
                    servos[i].setPosition(targetPosition);
                    telemetry.addData(SERVO_NAMES[i] + " Position", servos[i].getPosition());
                }
            }

            telemetry.update();
        }
    }
}