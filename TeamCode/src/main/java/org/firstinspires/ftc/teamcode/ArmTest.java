package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class ArmTest extends LinearOpMode {
    private DcMotor arm = null;
    private DcMotor arm2 = null;
    private final ElapsedTime runtime = new ElapsedTime();
    static final double     ARM_SPEED             = 0.5;
    @Override
    public void runOpMode() throws InterruptedException {
        arm = hardwareMap.get(DcMotor.class, "arm");
        arm2 = hardwareMap.get(DcMotor.class, "arm2");

        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        arm.setDirection(DcMotor.Direction.FORWARD);
        arm2.setDirection(DcMotor.Direction.REVERSE);

        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Starting at",  "%7d :%7d",
                arm.getCurrentPosition(),
                arm2.getCurrentPosition());
        telemetry.update();
        waitForStart();

        encoderarm(ARM_SPEED, 500,500,5.0);

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);  // pause to display final telemetry message.
    }
    public void encoderarm(double speed, double fowardInches, double reverseInches, double timeoutS) {
        int newarmTarget;
        int newarm2Target;

        if (opModeIsActive()) {
            newarmTarget = arm.getCurrentPosition() + (int)(fowardInches);
            newarm2Target = arm2.getCurrentPosition() + (int)(reverseInches);
            arm.setTargetPosition(newarmTarget);
            arm2.setTargetPosition(newarm2Target);

            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();
            arm.setPower(Math.abs(speed));
            arm2.setPower(Math.abs(speed));

            while (opModeIsActive() && (runtime.seconds() < timeoutS) && (arm.isBusy() && arm2.isBusy())) {
                telemetry.addData("Running to",  " %7d :%7d", newarmTarget, newarm2Target);
                telemetry.addData("Currently at",  " at %7d :%7d", newarmTarget, newarm2Target,
                        arm.getCurrentPosition(), arm2.getCurrentPosition());
                telemetry.update();
            }

            arm.setPower(0);
            arm2.setPower(0);

            arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            arm2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(250);
        }
    }
}
