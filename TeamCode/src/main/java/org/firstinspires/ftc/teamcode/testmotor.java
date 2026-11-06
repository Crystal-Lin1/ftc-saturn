package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Four Motor Tester", group = "Test")
public class testmotor extends LinearOpMode {

    // Configure the hardware map names for motors on ports 0 through 3
    private static final String[] MOTOR_NAMES = {"motor0", "motor1", "motor2", "motor3"};
    private DcMotor[] motors = new DcMotor[4];

    @Override
    public void runOpMode() {
        // Initialize all 4 motors
        for (int i = 0; i < motors.length; i++) {
            try {
                motors[i] = hardwareMap.get(DcMotor.class, MOTOR_NAMES[i]);
                motors[i].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                telemetry.addData("Status", "Initialized: " + MOTOR_NAMES[i]);
            } catch (Exception e) {
                telemetry.addData("Status", "Error: '" + MOTOR_NAMES[i] + "' not found!");
            }
        }

        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            double power = 0.0;
            String stateStr = "Stopped";

            // Determine target power based on button inputs
            if (gamepad1.a) {
                power = 0.5;
                stateStr = "Running Forward (0.5)";
            } else if (gamepad1.b) {
                power = -0.5;
                stateStr = "Running Reverse (-0.5)";
            }

            // Apply power and display telemetry for all initialized motors
            telemetry.addData("Global State", stateStr);
            for (int i = 0; i < motors.length; i++) {
                if (motors[i] != null) {
                    motors[i].setPower(power);
                    telemetry.addData(MOTOR_NAMES[i] + " Position", motors[i].getCurrentPosition());
                }
            }

            telemetry.update();
        }
    }
}