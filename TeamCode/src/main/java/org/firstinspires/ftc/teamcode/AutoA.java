package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class AutoA extends LinearOpMode {
    DcMotor FR, FL, BL, BR, lift, arm, arm2, hang;
    CRServo SL, SR, Srotate;
    @Override
    public void runOpMode() throws InterruptedException {
        {
            FR = hardwareMap.dcMotor.get("FR");
            FL = hardwareMap.dcMotor.get("FL");
            BR = hardwareMap.dcMotor.get("BR");
            BL = hardwareMap.dcMotor.get("BL");
            lift = hardwareMap.dcMotor.get("lift");
            arm = hardwareMap.dcMotor.get("arm");
            arm2 = hardwareMap.dcMotor.get("arm2");
            hang = hardwareMap.dcMotor.get("hang");

            SL = hardwareMap.crservo.get("SL");
            SR = hardwareMap.crservo.get("SR");
            Srotate = hardwareMap.crservo.get("Srotate");

            FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            arm2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            hang.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            waitForStart();
            FR.setPower(-0.35);
            FL.setPower(-0.35);
            BR.setPower(0.35);
            BL.setPower(0.35);

            sleep(30000);
        }
    }
}
