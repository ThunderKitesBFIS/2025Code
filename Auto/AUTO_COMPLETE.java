// This might not work

package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

private DcMotor rightFront;
private DcMotor rightBack;
private DcMotor leftFront;
private DcMotor leftBack;

rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
rightBack = hardwareMap.get(DcMotorEx.class,"rightBack");
leftFront = hardwareMap.get(DcMotorEx.class,"leftFront");
leftBack = hardwareMap.get(DcMotorEx.class,"leftBack");

public void startAll() {
  
  if (opModeIsActive()) {
    rightFront.setPower(1);
    leftFront.setPower(-1);
    rightBack.setPower(1);
    leftBack.setPower(-1);
  }
}

public void backAll() {
  
  if (opModeIsActive()) {
    rightFront.setPower(-1);
    leftFront.setPower(1);
    rightBack.setPower(-1);
    leftBack.setPower(1);
  }
}


public void startLeft() {
  
  if (opModeIsActive()) {
    rightFront.setPower(1);
  }
}

public void startRight() {
  
  if (opModeIsActive()) {
    leftFront.setPower(1);
  }
}

public void stopAll() {
  
  if (opModeIsActive()) {
    rightFront.setPower(0);
    leftFront.setPower(0);
    rightBack.setPower(0);
    leftBack.setPower(0);
  }
}

@TeleOp(name = "AUTO_COMPLETE")
public class AUTO_COMPLETE extends LinearOpMode {

  startAll();
  
}
