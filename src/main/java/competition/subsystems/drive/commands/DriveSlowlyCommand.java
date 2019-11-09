package competition.subsystems.drive.commands;
import com.google.inject.Inject;

import competition.subsystems.drive.DriveSubsystem;
import competition.subsystems.pose.PoseSubsystem;
import xbot.common.command.BaseCommand;
import xbot.common.injection.wpi_factories.CommonLibFactory;

public class DriveSlowlyCommand extends BaseCommand{
    DriveSubsystem drive;
    PoseSubsystem pose;

    @Inject
    public DriveSlowlyCommand(DriveSubsystem driveSubsystem, CommonLibFactory clf, PoseSubsystem pose)
    {
        this.drive = driveSubsystem;
        this.pose = pose;
        this.setTimeout(2);
        this.requires(driveSubsystem);
    }

    @Override
    public void initialize(){
        
    }

    @Override
    public void execute() {
        drive.tankDrive(.2, .2);
    }
    
    @Override
    public boolean isFinished() {
        return this.isTimedOut();
    }
}