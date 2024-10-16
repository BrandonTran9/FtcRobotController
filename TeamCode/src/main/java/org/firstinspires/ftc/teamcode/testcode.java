package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class testcode extends OpMode {
    DcMotor arm, lift, FR, FL, BL, BR,arm2,hang;
    @Override
    public void init() {
        arm = hardwareMap.dcMotor.get("arm");
        arm2 = hardwareMap.dcMotor.get("arm2");
        hang = hardwareMap.dcMotor.get("hang");
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

