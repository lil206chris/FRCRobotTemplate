package competition.operator_interface;

import java.util.ArrayList;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import competition.subsystems.drive.commands.ArcadeDriveCommand;
import competition.subsystems.drive.commands.Drive5Feet;
import competition.subsystems.drive.commands.DriveSlowlyCommand;
import competition.subsystems.drive.commands.DriveToOrientation;
import competition.subsystems.drive.commands.TankDriveWithJoysticksCommand;
import competition.subsystems.drive.commands.TurnLeft90Degrees;
import edu.wpi.first.wpilibj.command.Command;
import xbot.common.command.SimpleCommandGroup;
import xbot.common.command.SimpleCommandGroup.ExecutionType;
import xbot.common.subsystems.pose.ResetHeadingAndDistanceCommandGroup;

@Singleton
public class OperatorCommandMap {
    // For mapping operator interface buttons to commands

    // Example for setting up a command to fire when a button is pressed:
    /*
    @Inject
    public void setupMyCommands(
            OperatorInterface operatorInterface,
            MyCommand myCommand)
    {
        operatorInterface.leftButtons.getifAvailable(1).whenPressed(myCommand);
    }
    */
    @Inject
    public void simpleCommands(OperatorInterface oi, 
                               TankDriveWithJoysticksCommand tank,
                               ArcadeDriveCommand arcade, 
                               TurnLeft90Degrees turn,
                               TurnLeft90Degrees turn1, 
                               DriveSlowlyCommand slow,
                               DriveSlowlyCommand slow1,
                               DriveToOrientation trick,
                               Provider<DriveToOrientation> orientationProvider,
                               Provider<DriveSlowlyCommand> slowProvider,
                               Drive5Feet five,
                               ResetHeadingAndDistanceCommandGroup reset)
    {
        oi.gamepad.getifAvailable(1).whenPressed(tank);
        oi.gamepad.getifAvailable(2).whenPressed(reset);
        oi.gamepad.getifAvailable(3).whenPressed(turn);
        oi.gamepad.getifAvailable(4).whenPressed(five);

        var commandList = new ArrayList<Command>();
        commandList.add(slow1);
        commandList.add(turn1);



        var commandGroup = new SimpleCommandGroup("Autothings", commandList, ExecutionType.Serial);
        oi.gamepad.getifAvailable(5).whenPressed(commandGroup);

        var commandStar = new ArrayList<Command>();
        commandStar.add(slowProvider.get());
        commandStar.add(orientationProvider.get());
        commandStar.add(slowProvider.get());
        commandStar.add(orientationProvider.get());
        commandStar.add(slowProvider.get());
        commandStar.add(orientationProvider.get());
        commandStar.add(slowProvider.get());
        commandStar.add(orientationProvider.get());
        commandStar.add(slowProvider.get());
        commandStar.add(orientationProvider.get());
        
        var makestar = new SimpleCommandGroup("makeStar", commandStar, ExecutionType.Serial);
        oi.gamepad.getifAvailable(6).whenPressed(makestar);
    }
    
}
