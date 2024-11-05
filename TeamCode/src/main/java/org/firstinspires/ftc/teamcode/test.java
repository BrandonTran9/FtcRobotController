package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class test extends OpMode {
    DcMotor FR, FL, BL, BR;

    @Override
    public void init() {
        BR = hardwareMap.dcMotor.get("BR");
        BL = hardwareMap.dcMotor.get("BL");
        FR = hardwareMap.dcMotor.get("FR");
        FL = hardwareMap.dcMotor.get("FL");
    }

    @Override
    public void loop() {
        //Base movements
        if (Math.abs(gamepad1.right_stick_y) > .2) {
            FR.setPower(gamepad1.right_stick_y * 1);
            BR.setPower(gamepad1.right_stick_y * -1);
        } else {
            BR.setPower(0);
            FR.setPower(0);
        }
        if (Math.abs(gamepad1.left_stick_y) > .2) {
            FL.setPower(gamepad1.left_stick_y * -1);
            BL.setPower(gamepad1.left_stick_y * 1);
        } else {
            FL.setPower(0);
            BL.setPower(0);
        }
        if (gamepad1.right_bumper) {
            FR.setPower(2);
            BR.setPower(2);
            BL.setPower(2);
            FL.setPower(2);
        } else {
            FR.setPower(0);
            BR.setPower(0);
            BL.setPower(0);
            FL.setPower(0);
        }
        if (gamepad1.left_bumper) {
            FR.setPower(-2);
            BR.setPower(-2);
            BL.setPower(-2);
            FL.setPower(-2);
        } else {
            FR.setPower(0);
            BR.setPower(0);
            BL.setPower(0);
            FL.setPower(0);
        }
    }
}
