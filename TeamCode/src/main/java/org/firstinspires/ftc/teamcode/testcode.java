package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class testcode extends OpMode {
    DcMotor hanging_arm, hanging;

    @Override
    public void init() {
        hanging_arm = hardwareMap.dcMotor.get("hanging_arm");
        hanging = hardwareMap.dcMotor.get("hanging");
    }

    @Override
    public void loop() {

        if (gamepad1.dpad_up) {
            hanging_arm.setPower(1);
        } else {
            hanging_arm.setPower(0);
        }

        if (gamepad1.dpad_down) {
            hanging.setPower(-1);
        } else {
            hanging.setPower(0);
        }
    }
}

