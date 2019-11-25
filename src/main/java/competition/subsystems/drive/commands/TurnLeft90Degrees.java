package competition.subsystems.drive.commands;
import com.google.inject.Inject;

import competition.subsystems.drive.DriveSubsystem;
import competition.subsystems.pose.PoseSubsystem;
import xbot.common.command.BaseCommand;
import xbot.common.injection.wpi_factories.CommonLibFactory;
import xbot.common.math.PIDFactory;
import xbot.common.math.PIDManager;
import xbot.common.subsystems.drive.control_logic.HeadingModule;

public class TurnLeft90Degrees extends BaseCommand{
    DriveSubsystem drive;
    double goal;
    HeadingModule headingModule;
    PoseSubsystem pose;
    PIDManager pid;
    @Inject
    public TurnLeft90Degrees(DriveSubsystem driveSubsystem, CommonLibFactory clf, PIDFactory pf, PoseSubsystem pose)
    {
        this.drive = driveSubsystem;
        this.pose = pose;

        pid = pf.createPIDManager("Rotate");
        headingModule = clf.createHeadingModule(pid);

        pid.setEnableErrorThreshold(true);
        pid.setErrorThreshold(3);
        pid.setEnableDerivativeThreshold(true);
        pid.setDerivativeThreshold(.1);

        this.requires(this.drive);
        
        pid.setP(.05);
        pid.setD(.2);
    }
    @Override
public void initialize(){
        goal = pose.getCurrentHeading().getValue() + 90;

    }

    @Override
    public void execute() {
        double power = headingModule.calculateHeadingPower(goal);
        power *= .5;
        drive.tankDrive(-power, power);
    }
    public boolean isFinished()
    {
        return pid.isOnTarget();
    }

}