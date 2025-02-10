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
private Servo claw;
//private DcMotor arm;
//private Servo secondaryArm;
//private DcMotor leftSlider;
//private DcMotor rightSlider;
//private Servo clawServo;
//private Servo bucket;

// Define speed variable
double speed = 0.65;

double clawPower = 0;
  
@Override
public void runOpMode() {
// Initialize motors and servos from hardware map
//arm = hardwareMap.get(DcMotor.class, "arm");
//secondaryArm = hardwareMap.get(Servo.class, "secondaryArm");
//rightSlider = hardwareMap.get(DcMotor.class, "rightSlider");
//leftSlider = hardwareMap.get(DcMotor.class, "leftSlider");
//(clawServo = hardwareMap.get(Servo.class, "clawClaw");
// = hardwareMap.get(servo.class, "bucket");

// Set motor directions
//arm.setDirection(DcMotor.Direction.FORWARD);
//rightSlider.setDirection(DcMotor.Direction.FORWARD);
//leftSlider.setDirection(DcMotor.Direction.REVERSE);


// Set zero power behavior to brake to prevent unwanted motion
//rightSlider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//leftSlider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
  
// Initialize drive motors
rightFront = hardwareMap.get(DcMotor.class, "rightFront");
rightBack = hardwareMap.get(DcMotor.class, "rightBack");
leftFront = hardwareMap.get(DcMotor.class, "leftFront");
leftBack = hardwareMap.get(DcMotor.class, "leftBack");
claw = hardwareMap.get(Servo.class, "claw");

// Set zero power behavior for drive motors
rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

// Set motor directions for drivetrain
rightFront.setDirection(DcMotor.Direction.FORWARD);
rightBack.setDirection(DcMotor.Direction.FORWARD);
leftFront.setDirection(DcMotor.Direction.FORWARD);
leftBack.setDirection(DcMotor.Direction.FORWARD);

// Wait for the start command from the driver
waitForStart();

while (opModeIsActive()) {
// Initialize control variables
//float clawMainPower = gamepad1.left_trigger - gamepad1.right_trigger;
//float sliderPower = 0;
//float secondaryArmPosition = 0;
//double clawSpeed = 0.05;
//double clawServoPosition = 0;
//double bucketposition = 0;

// Initialize drivetrain power variables
double rightFrontPower = 0;
double rightBackPower = 0;
double leftFrontPower = 0;
double leftBackPower = 0;
// Forward and backward movement

rightFrontPower += (gamepad1.left_stick_y + gamepad2.left_stick_y) * speed;
rightBackPower += (gamepad1.left_stick_y + gamepad2.left_stick_y) * speed;
leftFrontPower += (gamepad1.left_stick_y + gamepad2.left_stick_y) * speed;
leftBackPower += (gamepad1.left_stick_y + gamepad2.left_stick_y) * speed;

// Strafe movement (sideways motion)
rightFrontPower += (gamepad1.left_stick_x + gamepad2.left_stick_x) * speed;
rightBackPower -= (gamepad1.left_stick_x + gamepad2.left_stick_x) * speed;
leftFrontPower -= (gamepad1.left_stick_x + gamepad2.left_stick_x) * speed;
leftBackPower += (gamepad1.left_stick_x + gamepad2.left_stick_x) * speed;

if (gamepad.x) {
  claw.setPosition(1);
}

else if (not gamepad.x) {
  claw.setPosition(0);
}

// Rotation (turning)
rightFrontPower += (gamepad1.right_stick_x + gamepad2.right_stick_x) * 1.5 * speed;
rightBackPower += (gamepad1.right_stick_x + gamepad2.right_stick_x) * 1.5 * speed;
leftFrontPower -= (gamepad1.right_stick_x + gamepad2.right_stick_x) * 1.5 * speed;
leftBackPower -= (gamepad1.right_stick_x + gamepad2.right_stick_x) * 1.5 * speed;

rightFront.setPower(rightFrontPower);
rightBack.setPower(rightBackPower);
leftFront.setPower(leftFrontPower);
leftBack.setPower(leftBackPower);
// Prevent CPU overuse

--
Naoki Hashimoto
BFIS Class of 2025 
HS Student Council President 2024-25
MS / HS Math Club President 2023-25
HS Robotics Club - Thunder Kites: Chief Engineer
MS / HS Science & Engineering Club President 2023-25

Can Ulas Unal
Developer
2025
MS/HS Robotics Memeber
