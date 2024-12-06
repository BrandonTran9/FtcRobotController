package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
public class redTeleOp extends OpMode {
    DcMotor lift1, FR, FL, BL, BR,lift2;
    Servo Srotate, intake,slide1,slide2,claw,bucket;

    @Override
    public void init() {
        BR = hardwareMap.dcMotor.get("BR");
        BL = hardwareMap.dcMotor.get("BL");
        FR = hardwareMap.dcMotor.get("FR");
        FL = hardwareMap.dcMotor.get("FL");
        lift2 = hardwareMap.dcMotor.get("lift2");
        lift1 = hardwareMap.dcMotor.get("lift1");
        intake = hardwareMap.servo.get("intake");
        Srotate = hardwareMap.servo.get("Srotate");
        slide1 = hardwareMap.servo.get("slide1");
        slide2 = hardwareMap.servo.get("slide2");
        claw = hardwareMap.servo.get("claw");
        bucket = hardwareMap.servo.get("bucket");

        FR.setDirection(DcMotor.Direction.REVERSE);
        FL.setDirection(DcMotor.Direction.FORWARD);
        BR.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.FORWARD);
        lift2.setDirection(DcMotor.Direction.FORWARD);
        lift1.setDirection(DcMotor.Direction.REVERSE);

        lift1.getCurrentPosition();
        lift2.getCurrentPosition();
    }


    @Override
    public void loop() {
        //Base movements
        if (Math.abs(gamepad1.right_stick_y) > .2) {
            FR.setPower(gamepad1.right_stick_y * 1);
            BR.setPower(gamepad1.right_stick_y * 1);
        } else {
            BR.setPower(0);
            FR.setPower(0);
        }
        if (Math.abs(gamepad1.left_stick_y) > .2) {
            FL.setPower(gamepad1.left_stick_y * 1);
            BL.setPower(gamepad1.left_stick_y * 1);
        } else {
            FL.setPower(0);
            BL.setPower(0);
        }
        if (gamepad1.left_bumper) {
            FR.setPower(-1);
            BR.setPower(1);
            BL.setPower(-1);
            FL.setPower(1);
        } else {
            FR.setPower(0);
            BR.setPower(0);
            BL.setPower(0);
            FL.setPower(0);
        }
        if (gamepad1.right_bumper) {
            FR.setPower(1);
            BR.setPower(-1);
            BL.setPower(1);
            FL.setPower(-1);
        } else {
            FR.setPower(0);
            BR.setPower(0);
            BL.setPower(0);
            FL.setPower(0);
        }
        // lifts
        if (Math.abs(gamepad2.left_stick_y) > .2) {
            lift1.setPower(gamepad2.left_stick_y *1);
            lift2.setPower(gamepad2.left_stick_y *1);;
        } else {
            lift1.setPower(0);
            // lift1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lift2.setPower(0);
            //  lift2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        if (gamepad2.dpad_up) {
            slide1.setPosition(.6);// down positions
            slide2.setPosition(.3); // opposite of 1 and .1 off
            Srotate.setPosition(.975);
        }
        if (gamepad2.dpad_down) {
            slide1.setPosition(0); // up positions
            slide2.setPosition(1);
            Srotate.setPosition(.4);
        }
        if (gamepad2.a) {
            claw.setPosition(.3); // grab
        }
        if (gamepad2.x) {
            claw.setPosition(0); //open
        }
        if (gamepad2.b) {
            intake.setPosition(.75 ); //close
        }
        if (gamepad2.y) {
            intake.setPosition(.5); //open
        }
        if (gamepad2.dpad_left) {
            bucket.setPosition(1);
        } else {
            bucket.setPosition(.63);
        }
        if (gamepad2.dpad_right) {
            Srotate.setPosition(.975);
        }
    }
}