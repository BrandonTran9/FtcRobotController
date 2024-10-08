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
        if (gamepad1.left_bumper) {
            FR.setPower(-1);
            BR.setPower(1);
            BL.setPower(1);
            FL.setPower(-1);
        } else {
            FR.setPower(0);
            BR.setPower(0);
            BL.setPower(0);
            FL.setPower(0);
        }
        //Arm and lift
        if (Math.abs(gamepad2.right_stick_y) > .2) {
            arm.setPower(1);
        } else {
            arm.setPower(0);
        }
        if (Math.abs(gamepad2.right_stick_x) > .2) {
            lift.setPower(1) ;
        }else {
            lift.setPower(0);
        }
        //Hanging
        if  (Math.abs(gamepad2.left_stick_y) > .2) {
            hang.setPower(1);
            }else {
            hang.setPower(0);
        }
        //intakes
        if (gamepad2.dpad_up) {
            SR.setPower(1);
            SL.setPower(1);
        }else {
            SR.setPower(0);
            SL.setPower(0);
        }
        if (gamepad2.dpad_up) {
            SR.setPower(-1);
            SL.setPower(-1);
        }else {
            SR.setPower(0);
            SL.setPower(0);
        }
        //intake rotater
        if (gamepad2.dpad_left) {
            Srotate.setPower(1);
        } else {
            Srotate.setPower(0);
        }
        if (gamepad2.dpad_right) {
            Srotate.setPower(-1);
        } else {
            Srotate.setPower(0);
        }
    }
}
