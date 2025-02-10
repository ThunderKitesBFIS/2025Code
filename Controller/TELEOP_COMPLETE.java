
package org.firstinspires.ftc.teamcode;

// Import necessary FTC libraries
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

// Define the TeleOp mode with a specific name
@TeleOp(name = "TELEOP_COMPLETE")
public class TELEOP_COMPLETE extends LinearOpMode {
    // Declare motor and servo variables
    private DcMotor rightFront;
    private DcMotor rightBack;
    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor arm;
    private DcMotor secondaryArm;
    private DcMotor leftSlider;
    private DcMotor rightSlider;
    private Servo clawServo;
    private Servo bucket;
    private Servo clawClaw;

    // Define speed variable
    double speed = 1;

    @Override
    public void runOpMode() {
        // Initialize motors and servos from hardware map
        arm = hardwareMap.get(DcMotor.class, "arm");
        secondaryArm = hardwareMap.get(DcMotor.class, "secondaryArm");
        rightSlider = hardwareMap.get(DcMotor.class, "rightSlider");
        leftSlider = hardwareMap.get(DcMotor.class, "leftSlider");
        clawServo = hardwareMap.get(Servo.class, "clawClaw");
        bucket = hardwareMap.get(Servo.class, "bucket");

        // Set motor directions
        arm.setDirection(DcMotor.Direction.FORWARD);
        rightSlider.setDirection(DcMotor.Direction.FORWARD);
        leftSlider.setDirection(DcMotor.Direction.REVERSE);

        // Set zero power behavior to brake to prevent unwanted motion
        rightSlider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftSlider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Initialize drive motors
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");

        // Set zero power behavior for drive motors
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Set motor directions for drivetrain
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the start command from the driver
        waitForStart();

        while (opModeIsActive()) {
            // Initialize control variables
            float clawMainPower = gamepad1.left_trigger - gamepad1.right_trigger;
            float sliderPower = 0;
            float secondaryArmPosition = 0;
            double clawSpeed = 0.05;
            double clawServoPosition = 0;
            double bucketposition = 0;

            // Control secondary arm position with buttons
            if (gamepad1.x) {
                secondaryArmPosition += clawSpeed;
            } else if (gamepad1.b) {
                secondaryArmPosition -= clawSpeed;
            }
            // Ensure the position stays within valid range
            secondaryArmPosition = Math.max(0, Math.min(1, secondaryArmPosition));

            // Control claw servo position with D-pad
            if (gamepad1.dpad_left) {
                clawServoPosition = 0.2;
            } else if (gamepad1.dpad_right) {
                clawServoPosition = 0.8;
            }

            // Control slider movement with D-pad
            if (gamepad1.dpad_down) {
                sliderPower = -0.4f;
            } else if (gamepad1.dpad_up) {
                sliderPower = 0.8f;
            } else {
                sliderPower = 0;
            }

            // Adjust robot speed with gamepad buttons
            if (gamepad1.y || gamepad2.y) {
                speed = 0.25;
            } else if (gamepad1.a || gamepad2.a) {
                speed = 1;
            }

            // Control bucket servo with Bumpers
            if (gamepad1.left_bumper) {
                bucketposition = 0.2;
            } else if (gamepad1.right_bumper) {
                bucketposition = 0.8;
            }

            // Initialize drivetrain power variables
            double rightFrontPower = 0;
            double rightBackPower = 0;
            double leftFrontPower = 0;
            double leftBackPower = 0;

            // Forward and backward movement
            rightFrontPower += -(gamepad1.left_stick_y + gamepad2.left_stick_y) * speed;
            rightBackPower += -(gamepad1.left_stick_y + gamepad2.left_stick_y) * speed;
            leftFrontPower += -(gamepad1.left_stick_y + gamepad2.left_stick_y) * speed;
            leftBackPower += -(gamepad1.left_stick_y + gamepad2.left_stick_y) * speed;

            // Strafe movement (sideways motion)
            rightFrontPower += -(gamepad1.left_stick_x + gamepad2.left_stick_x) * speed;
            rightBackPower += (gamepad1.left_stick_x + gamepad2.left_stick_x) * speed;
            leftFrontPower += (gamepad1.left_stick_x + gamepad2.left_stick_x) * speed;
            leftBackPower += -(gamepad1.left_stick_x + gamepad2.left_stick_x) * speed;

            // Rotation (turning)
            rightFrontPower += -(gamepad1.right_stick_x + gamepad2.right_stick_x) * 1.5 * speed;
            rightBackPower += -(gamepad1.right_stick_x + gamepad2.right_stick_x) * 1.5 * speed;
            leftFrontPower += (gamepad1.right_stick_x + gamepad2.right_stick_x) * 1.5 * speed;
            leftBackPower += (gamepad1.right_stick_x + gamepad2.right_stick_x) * 1.5 * speed;

            // Set motor and servo powers
            arm.setPower(clawMainPower);
            secondaryArm.setPower(secondaryArmPosition);
            clawServo.setPosition(clawServoPosition);
            bucket.setPosition(bucketposition);
            leftSlider.setPower(sliderPower);
            rightSlider.setPower(sliderPower);
            rightFront.setPower(rightFrontPower);
            rightBack.setPower(rightBackPower);
            leftFront.setPower(leftFrontPower);
            leftBack.setPower(leftBackPower);

            // Display telemetry data for debugging
            telemetry.addData("Drive Speed", speed);
            telemetry.addData("Left Stick Y", gamepad1.left_stick_y);
            telemetry.addData("Right Stick X", gamepad1.right_stick_x);
            telemetry.addData("Claw Servo Position", clawServoPosition);
            telemetry.addData("Arm Power", clawMainPower);
            telemetry.addData("Secondary Arm Power", secondaryArmPosition);
            telemetry.update();

            // Prevent CPU overuse
            idle();
        }
    }
}
