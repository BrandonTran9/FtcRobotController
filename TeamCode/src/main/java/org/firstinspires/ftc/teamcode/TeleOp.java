package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class teleop extends OpMode {
    DcMotor arm, hang, lift, FR, FL, BL, BR;
    CRServo SL, SR, Srotate;

    @Override
    public void init() {
        BR = hardwareMap.dcMotor.get("BR");
        BL = hardwareMap.dcMotor.get("BL");
        FR = hardwareMap.dcMotor.get("FR");
        FL = hardwareMap.dcMotor.get("FL");
        arm = hardwareMap.dcMotor.get("arm");
        hang = hardwareMap.dcMotor.get("hang");
        lift = hardwareMap.dcMotor.get("lift");
        SL = hardwareMap.crservo.get("SL");
        SR = hardwareMap.crservo.get("SR");
        Srotate = hardwareMap.crservo.get("Srotate");
    }

    @Override
    public void loop() {
        if (Math.abs(gamepad1.right_stick_y) > .2) {
            FR.setPower(gamepad1.right_stick_y * 1);
            BR.setPower(gamepad1.right_stick_y * 1);
        } else {
            BR.setPower(0);
            FR.setPower(0);
        }
        if (Math.abs(gamepad1.left_stick_y) > .2) {
            FL.setPower(gamepad1.left_stick_y * 1);
            BR.setPower(gamepad1.left_stick_y * 1);
        } else {
            BL.setPower(0);
            FL.setPower(0);
        }
        if (gamepad1.right_bumper) {
            FR.setPower(1);
            BR.setPower(-1);
            BL.setPower(-1);
            FL.setPower(1);
        } else {
            FR.setPower(0);
            BR.setPower(0);
            BL.setPower(0);
            FL.setPower(0);
        }
        if (Math.abs(gamepad2.right_stick_y) > .2) {
            arm.setPower(1);
        } else {
            arm.setPower(0);
        }
    }
}
