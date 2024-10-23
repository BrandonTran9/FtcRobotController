package org.firstinspires.ftc.teamcode;

import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AutoTest", group="Robot")
public class AutoTest extends LinearOpMode {

    /* Declare OpMode members. */
    private DcMotor FR = null;
    private DcMotor FL = null;
    private DcMotor BL = null;
    private DcMotor BR = null;
    private DcMotor arm = null;
    private DcMotor arm2 = null;
    private DcMotor lift = null;
    private final ElapsedTime runtime = new ElapsedTime();

    // Calculate the COUNTS_PER_INCH for your specific drive train.
    // Go to your motor vendor website to determine your motor's COUNTS_PER_MOTOR_REV
    // For external drive gearing, set DRIVE_GEAR_REDUCTION as needed.
    // For example, use a value of 2.0 for a 12-tooth spur gear driving a 24-tooth spur gear.
    // This is gearing DOWN for less speed and more torque.
    // For gearing UP, use a gear ratio less than 1.0. Note this will affect the direction of wheel rotation.
    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 0.5 ;     // No External Gearing.
    static final double     WHEEL_DIAMETER_INCHES   = 7.55906 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.5;
    static final double     ARM_SPEED             = 0.5;
    static final double     TURN_SPEED              = 0.5;

    @Override
    public void runOpMode() {

        // Initialize the drive system variables.
        FR = hardwareMap.get(DcMotor.class, "FR");
        FL = hardwareMap.get(DcMotor.class, "FL");
        BR = hardwareMap.get(DcMotor.class, "BR");
        BL = hardwareMap.get(DcMotor.class, "BL");
        arm = hardwareMap.get(DcMotor.class, "arm");
        arm2 = hardwareMap.get(DcMotor.class, "arm2");
        lift = hardwareMap.get(DcMotor.class, "lift");

        //TEST
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // When run, this OpMode should start both motors driving forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        FR.setDirection(DcMotor.Direction.REVERSE);
        FL.setDirection(DcMotor.Direction.REVERSE);
        BR.setDirection(DcMotor.Direction.FORWARD);
        BL.setDirection(DcMotor.Direction.FORWARD);
        arm.setDirection(DcMotor.Direction.FORWARD);
        arm2.setDirection(DcMotor.Direction.FORWARD);
        lift.setDirection(DcMotor.Direction.FORWARD);

        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Starting at",  "%7d :%7d :%7d :%7d",
                FR.getCurrentPosition(),
                FL.getCurrentPosition(),
                BR.getCurrentPosition(),
                BL.getCurrentPosition(),
                arm.getCurrentPosition(),
                arm2.getCurrentPosition(),
                lift.getCurrentPosition());
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)
        encoderDrive(DRIVE_SPEED,  -72,  72, 5.0);  // S1: Forward 12 Inches with 5 Sec timeout
        encoderDrive(DRIVE_SPEED,  -33,  -33, 5.0);
        encoderDrive(DRIVE_SPEED,  -20,  20, 5.0);
        encoderarm(ARM_SPEED, 50,50,5.0);
        // TEMPORARY COMMENT:encoderStrafe(DRIVE_SPEED, 20, 20, 5.0);  // S2: Strafe Left 12 Inches with 4 Sec timeout
        // TEMPORARY COMMENT:encoderDrive(DRIVE_SPEED, 5, 5, 5.0); // S3: Reverse 10 Inches with 5 Sec timeout


        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);  // pause to display final telemetry message.
    }

    /*
     *  Method to perform a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */
    public void encoderDrive(double speed, double leftInches, double rightInches, double timeoutS) {
        int newFRTarget;
        int newFLTarget;
        int newBRTarget;
        int newBLTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {
            // Determine new target position, and pass to motor controller
            newFRTarget = FR.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newFLTarget = FL.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newBRTarget = BR.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newBLTarget = BL.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            FR.setTargetPosition(newFRTarget);
            FL.setTargetPosition(newFLTarget);
            BR.setTargetPosition(newBRTarget);
            BL.setTargetPosition(newBLTarget);
            arm.setTargetPosition(newarmTarget);
            arm2.setTargetPosition(newarm2Target);
            lift.setTargetPosition(newliftTarget);

            // Turn On RUN_TO_POSITION
            FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            FL.setPower(Math.abs(speed));
            FR.setPower(Math.abs(speed));
            BL.setPower(Math.abs(speed));
            BR.setPower(Math.abs(speed));
            arm.setPower(Math.abs(speed));
            arm2.setPower(Math.abs(speed));
            lift.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (FL.isBusy() && BL.isBusy() && BR.isBusy() && FR.isBusy() && arm.isBusy() && arm2.isBusy() && lift.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Running to",  " %7d :%7d :%7d :%7d", newFLTarget,  newFRTarget, newBLTarget, newBRTarget);
                telemetry.addData("Currently at",  " at %7d :%7d :%7d :%7d", newFLTarget, newFRTarget, newBLTarget, newBRTarget,
                        FL.getCurrentPosition(), FR.getCurrentPosition(), BL.getCurrentPosition(), BR.getCurrentPosition(), arm.getCurrentPosition(), arm2.getCurrentPosition(), lift.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            FL.setPower(0);
            FR.setPower(0);
            BL.setPower(0);
            BR.setPower(0);
            arm.setPower(0);
            arm2.setPower(0);
            lift.setPower(0);

            // Turn off RUN_TO_POSITION
            FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            arm2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(250);   // optional pause after each move.
        }
    }
    //Arm encoders
    public void encoderarm(double speed, double fowardInches, double reverseInches, double timeoutS) {
        int newarmTarget;
        int newarm2Target;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {
            // Determine new target position, and pass to motor controller
            newarmTarget = arm.getCurrentPosition() + (int)(fowardInches);
            newarm2Target = arm2.getCurrentPosition() + (int)(reverseInches);
            arm.setTargetPosition(newarmTarget);
            arm2.setTargetPosition(newarm2Target);

            // Turn On RUN_TO_POSITION
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            arm.setPower(Math.abs(speed));
            arm2.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (arm.isBusy() && arm2.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Running to",  " %7d :%7d :%7d :%7d", newarmTarget, newarm2Target);
                telemetry.addData("Currently at",  " at %7d :%7d :%7d :%7d", newarmTarget, newarm2Target,
                        arm.getCurrentPosition(), arm2.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            arm.setPower(0);
            arm2.setPower(0);

            // Turn off RUN_TO_POSITION
            arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            arm2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(250);   // optional pause after each move.
        }
    }
    //strafing encoders
    public void encoderStrafe(double speed, double leftInches, double rightInches, double timeoutS){

        int newFRTarget;
        int newFLTarget;
        int newBRTarget;
        int newBLTarget;

        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            // For strafing one side's wheels 'attract' each other while the other side 'repels' each other
            //The positive value will make the robot go to the left with current arrangement
            newFRTarget = FR.getCurrentPosition() - (int)(rightInches * COUNTS_PER_INCH);
            newFLTarget = FL.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newBRTarget = BR.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newBLTarget = BL.getCurrentPosition() - (int)(leftInches * COUNTS_PER_INCH);
            FR.setTargetPosition(newFRTarget);
            FL.setTargetPosition(newFLTarget);
            BR.setTargetPosition(newBRTarget);
            BL.setTargetPosition(newBLTarget);

            // Turn On RUN_TO_POSITION
            FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            FL.setPower(Math.abs(speed));
            FR.setPower(Math.abs(speed));
            BL.setPower(Math.abs(speed));
            BR.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (FL.isBusy() && BL.isBusy() && BR.isBusy() && FR.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Running to",  " %7d :%7d :%7d :%7d", newFLTarget,  newFRTarget, newBLTarget, newBRTarget);
                telemetry.addData("Currently at",  " at %7d :%7d :%7d :%7d", newFLTarget, newFRTarget, newBLTarget, newBRTarget,
                        FL.getCurrentPosition(), FR.getCurrentPosition(), BL.getCurrentPosition(), BR.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            FL.setPower(0);
            FR.setPower(0);
            BL.setPower(0);
            BR.setPower(0);

            // Turn off RUN_TO_POSITION
            FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(250);   // optional pause after each move.
        }
    }

    //private boolean opModeIsActive() {
    //}
}