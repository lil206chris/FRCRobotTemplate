package competition.operator_interface;

import java.util.ArrayList;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import competition.subsystems.drive.commands.DriveToOrientation;
import competition.subsystems.drive.commands.ArcadeDriveCommand;
import competition.subsystems.drive.commands.DriveSlowlyCommand;
import competition.subsystems.drive.commands.TankDriveWithJoysticksCommand;
import competition.subsystems.drive.commands.TurnLeft90Degrees;
import edu.wpi.first.wpilibj.command.Command;
import xbot.common.command.SimpleCommandGroup;
import xbot.common.command.SimpleCommandGroup.ExecutionType;

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
                               DriveSlowlyCommand slow,
                               DriveToOrientation trick)
    {
        oi.gamepad.getifAvailable(1).whenPressed(tank);
        oi.gamepad.getifAvailable(2).whenPressed(arcade);
        oi.gamepad.getifAvailable(3).whenPressed(turn);
        oi.gamepad.getifAvailable(4).whenPressed(slow);
        oi.gamepad.getifAvailable(6).whenPressed(trick);

        var commandList = new ArrayList<Command>();
        commandList.add(slow);
        commandList.add(turn);

        var commandGroup = new SimpleCommandGroup("Auto", commandList, ExecutionType.Serial);
        oi.gamepad.getifAvailable(5).whenPressed(commandGroup);

    }
    
}
