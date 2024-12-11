package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class autoGoFoward extends LinearOpMode {
    DcMotor lift1, FR, FL, BL, BR,lift2;
    Servo Srotate, intake,slide1,slide2,claw,bucket;
    @Override
    public void runOpMode() throws InterruptedException {
        {
            FR = hardwareMap.dcMotor.get("FR");
            FL = hardwareMap.dcMotor.get("FL");
            BR = hardwareMap.dcMotor.get("BR");
            BL = hardwareMap.dcMotor.get("BL");
            /*
            lift1 = hardwareMap.dcMotor.get("lift");
            lift2 = hardwareMap.dcMotor.get("arm2");

            Srotate = hardwareMap.servo.get("Srotate");
            intake = hardwareMap.servo.get("intake");
            slide1 = hardwareMap.servo.get("slide1");
            slide2 = hardwareMap.servo.get("slide2");
            claw = hardwareMap.servo.get("claw");
            bucket = hardwareMap.servo.get("bucket");
             */


            FR.setDirection(DcMotor.Direction.FORWARD);
            FL.setDirection(DcMotor.Direction.REVERSE);
            BR.setDirection(DcMotor.Direction.FORWARD);
            BL.setDirection(DcMotor.Direction.REVERSE);
            FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            waitForStart();
            FR.setPower(0.5);
            FL.setPower(0.5);
            BR.setPower(0.5);
            BL.setPower(0.5);

            telemetry.addData("Path", "Complete");
            telemetry.update();;
            sleep(2200);
        }
    }
}
