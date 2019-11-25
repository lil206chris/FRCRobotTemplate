package competition.subsystems.drive.commands;
import com.google.inject.Inject;

import competition.subsystems.drive.DriveSubsystem;
import competition.subsystems.pose.PoseSubsystem;
import xbot.common.command.BaseCommand;
import xbot.common.injection.wpi_factories.CommonLibFactory;
import xbot.common.math.PIDFactory;
import xbot.common.math.PIDManager;


public class Drive5Feet extends BaseCommand{
    DriveSubsystem drive;
    double goal;
    PoseSubsystem pose;
    PIDManager pid;
    double currentPosition;

    @Inject
    public Drive5Feet(DriveSubsystem driveSubsystem, CommonLibFactory clf, PIDFactory pf, PoseSubsystem pose)
    {
        this.drive = driveSubsystem;
        this.pose = pose;
        pid = pf.createPIDManager("DriveToPoint");
        pid.setEnableErrorThreshold(true);
        pid.setErrorThreshold(3);
        pid.setEnableDerivativeThreshold(true);
        pid.setDerivativeThreshold(.2);
        this.requires(this.drive);
        pid.setP(.5);
        pid.setD(4);
    
    }
    @Override
    public void initialize(){
        goal = drive.getLeftTotalDistance() + 60;

    }
    @Override
    public void execute() {
        currentPosition = drive.getLeftTotalDistance();
        System.out.println("inches: " + currentPosition);
        double power = pid.calculate(goal, currentPosition);
        power *= .3;
        drive.tankDrive(power, power);
    }
    public boolean isFinished()
    {
        return pid.isOnTarget();
        
    }
}