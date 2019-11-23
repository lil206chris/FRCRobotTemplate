package competition.subsystems.drive;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import org.apache.log4j.Logger;

import xbot.common.command.BaseSubsystem;
import xbot.common.controls.actuators.XCANTalon;
import xbot.common.injection.wpi_factories.CommonLibFactory;
import xbot.common.properties.XPropertyManager;

@Singleton
public class DriveSubsystem extends BaseSubsystem {
    private static Logger log = Logger.getLogger(DriveSubsystem.class);

    public final XCANTalon leftMaster;
    public final XCANTalon leftFollower;
    public final XCANTalon leftFollower2;
    public final XCANTalon rightMaster;
    public final XCANTalon rightFollower;
    public final XCANTalon rightFollower2;
    double ticksPerInch = 217.0;

    @Inject
    public DriveSubsystem(CommonLibFactory factory, XPropertyManager propManager) {
        log.info("Creating DriveSubsystem");

        this.leftMaster = factory.createCANTalon(33);
        this.leftFollower = factory.createCANTalon(34);
        this.leftFollower2 = factory.createCANTalon(32);
        this.rightMaster = factory.createCANTalon(22);
        this.rightFollower = factory.createCANTalon(21);
        this.rightFollower2 = factory.createCANTalon(23);
/**
        XCANTalon.configureMotorTeam("LeftDrive", "LeftMaster", leftMaster, leftFollower, 
        true, true, false);
        XCANTalon.configureMotorTeam("RightDrive", "RightMaster", rightMaster, rightFollower, 
        false, false, false);
        */

        leftMaster.configureAsMasterMotor(this.getPrefix(), "leftMaster", false, false);
        leftFollower.configureAsFollowerMotor(leftMaster, false);
        leftFollower2.configureAsFollowerMotor(leftMaster, false);
        rightMaster.configureAsMasterMotor(this.getPrefix(), "rightMaster", true, false);
        rightFollower.configureAsFollowerMotor(rightMaster, true);
        rightFollower2.configureAsFollowerMotor(rightMaster, true);
        
    }

    public void tankDrive(double leftPower, double rightPower) {
        this.leftMaster.simpleSet(leftPower);
        this.rightMaster.simpleSet(rightPower);
    }
    
    
    public double getLeftTotalDistance(){
        return -1 * (leftMaster.getSelectedSensorPosition(0));
    }
    
    public double getRightTotalDistance(){
        return rightMaster.getSelectedSensorPosition(0);
    }

}
