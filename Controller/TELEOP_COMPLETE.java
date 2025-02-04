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
    private DcMotor chain;

    double speed =1;
    @Override
    public void runOpMode() {
        // Initialize hardware
        arm = hardwareMap.get(DcMotor.class, "arm");
        chain = hardwareMap.get(DcMotor.class, "chain");

        // Set motor directions if necessary
        arm.setDirection(DcMotor.Direction.FORWARD);
        chain.setDirection(DcMotor.Direction.FORWARD);

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
            float clawChainPower = 0;

            // Control chain motor with bumpers
            if (gamepad1.left_bumper) {
                clawChainPower = 0.2f;
            } else if (gamepad1.right_bumper) {
                clawChainPower = -0.2f;
            } else {
                clawChainPower = 0;
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
            leftBackPower += (gamepad1.right_stick_x+gamepad2.right_stick_x)*1*speed;

            // Set motor powers
            arm.setPower(clawMainPower);
            chain.setPower(clawChainPower);

            rightFront.setPower(rightFrontPower);
            rightBack.setPower(rightBackPower);
            leftFront.setPower(leftFrontPower);
            leftBack.setPower(leftBackPower);

            // Add telemetry for debugging
            telemetry.addData("Arm Power", clawMainPower);
            telemetry.addData("Chain Power", clawChainPower);
            telemetry.update();
        }
    }
}

    