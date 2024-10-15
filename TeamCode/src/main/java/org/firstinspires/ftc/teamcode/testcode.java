package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class testcode extends OpMode {
    DcMotor arm, lift, FR, FL, BL, BR,arm2,hang;
    @Override
    public void init() {
        hanging_arm = hardwareMap.dcMotor.get("hanging_arm");
        hanging = hardwareMap.dcMotor.get("hanging");
    }

    @Override
    public void loop() {

        if (gamepad1.dpad_up) {
            hang.setPower(1);
        } else {
            hang.setPower(0);
        }

        if (gamepad1.dpad_down) {
            hang.setPower(-1);
        } else {
            hang.setPower(0);
        }
    }
}

