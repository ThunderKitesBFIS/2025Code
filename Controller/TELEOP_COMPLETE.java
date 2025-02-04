package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@TeleOp(name = "TELEOP_COMPLETE")
public class TELEOP_COMPLETE extends LinearOpMode {
	// Port Cofigurations & Declearing Components & Type
	// DIRECT MOTOR PINS
	private DcMotor rightFront;
	private DcMotor rightBack;
	private DcMotor leftFront;
	private DcMotor leftBack;
	private DcMotor arm;
	private DcMotor secondaryArm;
	private DcMotor leftSlider;
	private DcMotor rightSlider;

	double speed =1;
	@Override
	public void runOpMode() {
		// Initialize hardware
		arm = hardwareMap.get(DcMotor.class, "arm");
		secondaryArm = hardwareMap.get(DcMotor.class, "secondaryArm");
		rightSlider = hardwareMap.get(DcMotor.class, "rightSlider");
		leftSlider = hardwareMap.get(DcMotor.class, "leftSlider");

		// Set motor directions if necessary
		arm.setDirection(DcMotor.Direction.FORWARD);
		secondaryArm.setDirection(DcMotor.Direction.FORWARD);
		rightSlider.setDirection(DcMotor.Direction.FORWARD);
		leftSlider.setDirection(DcMotor.Direction.REVERSE);
		
		rightSlider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		leftSlider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

		rightFront = hardwareMap.get(DcMotor.class, "rightFront");
		rightBack = hardwareMap.get(DcMotor.class,"rightBack");
		leftFront = hardwareMap.get(DcMotor.class,"leftFront");
		leftBack = hardwareMap.get(DcMotor.class,"leftBack");
		
		rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		

		rightFront.setDirection(DcMotor.Direction.FORWARD);
		rightBack.setDirection(DcMotor.Direction.REVERSE);
		leftFront.setDirection(DcMotor.Direction.REVERSE);
		leftBack.setDirection(DcMotor.Direction.REVERSE);

		waitForStart();

		while (opModeIsActive()) {
			float clawMainPower = gamepad1.left_trigger - gamepad1.right_trigger;
			float secondaryClawPower = 0;
			float sliderPower = 0;

			// Control chain motor with bumpers
			if (gamepad1.left_bumper) {
				secondaryClawPower = 0.2f;
			} else if (gamepad1.right_bumper) {
				secondaryClawPower = -0.2f;
			} else {
				secondaryClawPower = 0;
			}


			if (gamepad1.dpad_down) {
				sliderPower = -0.4f;
			} else if (gamepad1.dpad_up) {
				sliderPower = 0.8f;
			} else {
				sliderPower = 0;
			}
			
			
			double rightFrontPower = 0;
			double rightBackPower = 0;
			double leftFrontPower = 0;
			double leftBackPower = 0;
			
			rightFrontPower += -(gamepad1.left_stick_y + gamepad2.left_stick_y ) *speed;
			rightBackPower += -(gamepad1.left_stick_y+gamepad2.left_stick_y)*speed;
			leftFrontPower += -(gamepad1.left_stick_y+gamepad2.left_stick_y)*speed;
			leftBackPower += -(gamepad1.left_stick_y+gamepad2.left_stick_y)*speed;
		  
			//Side Ways Strafe
			rightFrontPower += -(gamepad1.left_stick_x+gamepad2.left_stick_x)*speed;
			rightBackPower +=  (gamepad1.left_stick_x+gamepad2.left_stick_x)*speed;
			leftFrontPower += (gamepad1.left_stick_x+gamepad2.left_stick_x)*speed;
			leftBackPower += -(gamepad1.left_stick_x+gamepad2.left_stick_x)*speed;
			
			//Rotation
			rightFrontPower += -(gamepad1.right_stick_x+gamepad2.right_stick_x)*1.5*speed;
			rightBackPower += -(gamepad1.right_stick_x+gamepad2.right_stick_x)*1.5*speed;
			leftFrontPower += (gamepad1.right_stick_x+gamepad2.right_stick_x)*1.5*speed;
			leftBackPower += (gamepad1.right_stick_x+gamepad2.right_stick_x)*1.5*speed;

			// Set motor powers
			arm.setPower(clawMainPower);
			secondaryArm.setPower(secondaryClawPower);
			
			leftSlider.setPower(sliderPower);
			rightSlider.setPower(sliderPower);

			rightFront.setPower(rightFrontPower);
			rightBack.setPower(rightBackPower);
			leftFront.setPower(leftFrontPower);
			leftBack.setPower(leftBackPower);

			// Add telemetry for debugging
			telemetry.addData("Arm Power", clawMainPower);
			telemetry.addData("Secondary Arm Power", secondaryClawPower);
			telemetry.update();
		}
	}
}

