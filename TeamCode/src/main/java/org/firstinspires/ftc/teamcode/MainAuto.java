package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class MainAuto extends LinearOpMode {
    DcMotor fr;
    DcMotor fl;
    DcMotor br;
    DcMotor bl;
    @Override
    public void runOpMode() throws InterruptedException {
        {
            fr=hardwareMap.dcMotor.get("fr");
            fl=hardwareMap.dcMotor.get("fl");
            br =hardwareMap.dcMotor.get("rf");
            bl=hardwareMap.dcMotor.get("bl");
            waitForStart();
            fr.setPower(-0.35);
            fl.setPower(-0.35);
            br.setPower(0.35);
            bl.setPower(0.35);
            sleep(1500);


        }
    }
}
