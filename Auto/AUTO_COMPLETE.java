package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Movement {

  private DcMotor rightFront;
  private DcMotor rightBack;
  private DcMotor leftFront;
  private DcMotor leftBack;
  private Servo claw;

  public Movement(HardwareMap hardwareMap) {
    
    rightFront = hardwareMap.get(DcMotor.class, "rightFront");
    rightBack = hardwareMap.get(DcMotor.class, "rightBack");
    leftFront = hardwareMap.get(DcMotor.class, "leftFront");
    leftBack = hardwareMap.get(DcMotor.class, "leftBack");
    claw = hardwareMap.get(Servo.class, "claw");

        // Optional: Set motor directions if needed
    rightFront.setDirection(DcMotor.Direction.FORWARD);
    rightBack.setDirection(DcMotor.Direction.FORWARD);
    leftFront.setDirection(DcMotor.Direction.REVERSE);
    leftBack.setDirection(DcMotor.Direction.REVERSE);
    claw.setDirection(DcMotor.Direction.FORWARD);
  }

  public void startAll() {
    rightFront.setPower(1);
    leftFront.setPower(-1);
    rightBack.setPower(1);
    leftBack.setPower(-1);
  }

  public void startClaw() {
    claw.setPosition(claw.Position+0.1);
  }

  public void backClaw() {
    claw.setPosition(claw.Position-0.1);
  }
  
  public void backAll() {
    
    rightFront.setPower(-1);
    leftFront.setPower(1);
    rightBack.setPower(-1);
    leftBack.setPower(1);
  }
  
  
  public void startLeft() {

    rightFront.setPower(1);
  }
  
  public void startRight() {
    
    leftFront.setPower(1);
  }
  
  public void stopAll() {
  
    rightFront.setPower(0);
    leftFront.setPower(0);
    rightBack.setPower(0);
    leftBack.setPower(0);
    
  }
  
  
  public void startFront() {
    
    rightFront.setPower(1);
    leftFront.setPower(-1);
  }


  public void startBack() {
    
    rightBack.setPower(1);
    leftBack.setPower(-1);
  }

}
