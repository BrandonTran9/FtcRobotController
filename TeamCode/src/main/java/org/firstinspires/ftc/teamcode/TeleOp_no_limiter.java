package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class TeleOp_no_limiter extends OpMode {
    /*static final double     COUNTS_PER_MOTOR_REV    = 435 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1 ;     // No External Gearing.
    static final double     WHEEL_DIAMETER_INCHES   = 3.8825 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);*/

    DcMotor arm, lift, FR, FL, BL, BR,arm2,hang;
    CRServo SL, SR, Srotate, Srotate2;

    @Override
    public void init() {
        BR = hardwareMap.dcMotor.get("BR");
        BL = hardwareMap.dcMotor.get("BL");
        FR = hardwareMap.dcMotor.get("FR");
        FL = hardwareMap.dcMotor.get("FL");
        arm = hardwareMap.dcMotor.get("arm");
        arm2 = hardwareMap.dcMotor.get("arm2");
        lift = hardwareMap.dcMotor.get("lift");
        hang = hardwareMap.dcMotor.get("hang");
        SL = hardwareMap.crservo.get("SL");
        SR = hardwareMap.crservo.get("SR");
        Srotate = hardwareMap.crservo.get("Srotate");
        Srotate2 = hardwareMap.crservo.get("Srotate2");

        /*FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);*/
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hang.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        FR.setDirection(DcMotor.Direction.FORWARD);
        FL.setDirection(DcMotor.Direction.FORWARD);
        BR.setDirection(DcMotor.Direction.FORWARD);
        BL.setDirection(DcMotor.Direction.FORWARD);
        arm.setDirection(DcMotor.Direction.FORWARD);
        arm2.setDirection(DcMotor.Direction.FORWARD);
        lift.setDirection(DcMotor.Direction.FORWARD);
        hang.setDirection(DcMotor.Direction.FORWARD);
        SL.setDirection(DcMotor.Direction.FORWARD);
        SR.setDirection(DcMotor.Direction.FORWARD);
        Srotate.setDirection(DcMotor.Direction.FORWARD);
        Srotate2.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void loop() {
        double speed = gamepad2.right_trigger - gamepad2.left_trigger;
        SR.setPower(speed);
        SL.setPower(speed);
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
            FR.setPower(1);
            BR.setPower(1);
            BL.setPower(1);
            FL.setPower(1);
        } else {
            FR.setPower(0);
            BR.setPower(0);
            BL.setPower(0);
            FL.setPower(0);
        }
        if (gamepad1.left_bumper) {
            FR.setPower(-1);
            BR.setPower(-1);
            BL.setPower(-1);
            FL.setPower(-1);
        } else {
            FR.setPower(0);
            BR.setPower(0);
            BL.setPower(0);
            FL.setPower(0);
        }
        //Arm
        if (Math.abs(gamepad2.right_stick_y) > .2) {
            arm.setPower(gamepad2.right_stick_y * -0.3);
            arm2.setPower(gamepad2.right_stick_y * 0.3);
        } else {
            /*arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            arm2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);*/
            arm.setPower(0);
            arm2.setPower(0);
        }
        if (gamepad2.y) {
            lift.setPower(1);
        } else if (gamepad2.a){
            lift.setPower(-1);
        }
        else {
            lift.setPower(0);
        }
        // Send telemetry message to indicate arm position
        telemetry.addData("Arm position at",  "%7d :%7d",
                arm.getCurrentPosition(),
                arm2.getCurrentPosition());
        telemetry.addData("Lift position at", "%7d",
                lift.getCurrentPosition());
        telemetry.update();
        //hang
        if (gamepad1.dpad_up) {
            hang.setPower(-1);
        }else {
            hang.setPower(0);
        }
        if (gamepad1.dpad_down) {
            hang.setPower(1);
        }else {
            hang.setPower(0);
        }
        //intakes
        if (Math.abs(gamepad2.right_trigger) > .2) {
            SR.setDirection(DcMotorSimple.Direction.FORWARD);
            SL.setDirection(DcMotorSimple.Direction.REVERSE);
        }else {
            SR.setPower(0);
            SL.setPower(0);
        }
        if (Math.abs(gamepad2.left_trigger) > 2) {
            SR.setDirection(DcMotorSimple.Direction.REVERSE);
            SL.setDirection(DcMotorSimple.Direction.FORWARD);
        }else {
            SR.setPower(0);
            SL.setPower(0);
        }
        //intake rotater
        if (gamepad2.left_bumper) {
            Srotate.setPower(1);
            Srotate2.setPower(-1);
        } else {;
            Srotate.setPower(0);
            Srotate2.setPower(0);
        }
        if (gamepad2.right_bumper) {
            Srotate.setPower(-1);
            Srotate2.setPower(1);
        } else {
            Srotate.setPower(0);
            Srotate2.setPower(0);
        }
    }
}