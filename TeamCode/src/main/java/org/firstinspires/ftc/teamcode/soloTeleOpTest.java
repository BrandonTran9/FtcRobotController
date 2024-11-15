package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class soloTeleOpTest extends OpMode {
    CRServo SL, SR, Srotate, Srotate2;
    @Override
    public void init() {
        SL = hardwareMap.crservo.get("SL");
        SR = hardwareMap.crservo.get("SR");
        Srotate = hardwareMap.crservo.get("Srotate");
        Srotate2 = hardwareMap.crservo.get("Srotate2");
    }


    @Override
    public void loop() {
        //Base movements
        if (gamepad2.right_bumper) {
            Srotate.setPower(1);
            Srotate2.setPower(-1);
        } else {
            Srotate.setPower(0);
            Srotate2.setPower(0);
        }
        if (gamepad2.left_bumper) {
            Srotate.setPower(-1);
            Srotate2.setPower(1);
        } else {
            Srotate.setPower(0);
            Srotate2.setPower(0);
        }
    }
}
