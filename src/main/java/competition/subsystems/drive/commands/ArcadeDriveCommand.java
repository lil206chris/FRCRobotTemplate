package competition.subsystems.drive.commands;

import com.google.inject.Inject;

import competition.operator_interface.OperatorInterface;
import competition.subsystems.drive.DriveSubsystem;
import xbot.common.command.BaseCommand;

public class ArcadeDriveCommand extends BaseCommand {


    final DriveSubsystem driveSubsystem;
    final OperatorInterface oi;

    @Inject
    public ArcadeDriveCommand(OperatorInterface oi, DriveSubsystem driveSubsystem) {
        this.oi = oi;
        this.driveSubsystem = driveSubsystem;
        this.requires(this.driveSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        double leftPower;
        double rightPower;
        double x = oi.gamepad.getLeftVector().x;
        double y = oi.gamepad.getLeftVector().y;
        leftPower = (y + x)*.50;
        rightPower = (y - x)*.50;
        driveSubsystem.tankDrive(leftPower, rightPower);
    }

}
