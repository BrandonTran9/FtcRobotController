package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Auto extends LinearOpMode {
    DcMotor FR;
    DcMotor FL;
    DcMotor BR;
    DcMotor BL;
    @Override
    public void runOpMode() throws InterruptedException {
        {
            FR =hardwareMap.dcMotor.get("FR");
            FL =hardwareMap.dcMotor.get("FL");
            BR =hardwareMap.dcMotor.get("BR");
            BL =hardwareMap.dcMotor.get("BL");
            waitForStart();
            FR.setPower(-0.35);
            FL.setPower(-0.35);
            BR.setPower(0.35);
            BL.setPower(0.35);
            sleep(1500);


        }
    }
}
