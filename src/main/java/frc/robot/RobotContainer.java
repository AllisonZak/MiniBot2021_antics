// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.List;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.AutonomousDistance;
import frc.robot.commands.AutonomousTime;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.OnBoardIO;
import frc.robot.subsystems.OnBoardIO.ChannelMode;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.button.Button;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final OnBoardIO m_onboardIO = new OnBoardIO(ChannelMode.INPUT, ChannelMode.INPUT);

  // Assumes a gamepad plugged into channnel 0
  private final Joystick m_controller = new Joystick(0);

  // Create SmartDashboard chooser for autonomous routines
  private final SendableChooser<Command> m_chooser = new SendableChooser<>();

  // NOTE: The I/O pin functionality of the 5 exposed I/O pins depends on the hardware "overlay"
  // that is specified when launching the wpilib-ws server on the Romi raspberry pi.
  // By default, the following are available (listed in order from inside of the board to outside):
  // - DIO 8 (mapped to Arduino pin 11, closest to the inside of the board)
  // - Analog In 0 (mapped to Analog Channel 6 / Arduino Pin 4)
  // - Analog In 1 (mapped to Analog Channel 2 / Arduino Pin 20)
  // - PWM 2 (mapped to Arduino Pin 21)
  // - PWM 3 (mapped to Arduino Pin 22)
  //
  // Your subsystem configuration should take the overlays into account

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Generate a trajectory following Ramsete command
   * 
   * This is very similar to the WPILib RamseteCommand example. It uses
   * constants defined in the Constants.java file. These constants were 
   * found empirically by using the frc-characterization tool.
   * 
   * @return A SequentialCommand that sets up and executes a trajectory following Ramsete command
   */
  private Command generateRamseteCommand() {
    var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(DriveConstants.ksVolts, 
                                       DriveConstants.kvVoltSecondsPerMeter, 
                                       DriveConstants.kaVoltSecondsSquaredPerMeter),
            DriveConstants.kDriveKinematics,
            10);

    TrajectoryConfig config =
        new TrajectoryConfig(AutoConstants.kMaxSpeedMetersPerSecond, 
                             AutoConstants.kMaxAccelerationMetersPerSecondSquared)
            .setKinematics(DriveConstants.kDriveKinematics)
            .addConstraint(autoVoltageConstraint);

    // This trajectory can be modified to suit your purposes
    // Note that all coordinates are in meters, and follow NWU conventions.
    // If you would like to specify coordinates in inches (which might be easier
    // to deal with for the Romi), you can use the () method

    
    //******************* START COURSE 2 *********************
     
      Trajectory exampleTrajectory2 = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(0, 0, new Rotation2d(0)),
        List.of(

            new Translation2d((0.3302),(0)), 
            new  Translation2d((0.4),(0.25)),
            new Translation2d((0.76),(0.24)),
            new Translation2d((1),(0.23)),
            new Translation2d((1.5),(0.15)),
            //new Translation2d((1.5),(.2)),
            new Translation2d((1.5),(-.2)),
            new Translation2d((1.8),(.1)),
            new Translation2d((1.9),(0.35)),
            new Translation2d((1.6),(0.36)),
            new Translation2d((1.53),(.26)),
            new Translation2d((1.46),(.1)),
            new Translation2d((.6),(.3)),
            new Translation2d((.3),(.32)),
           new Translation2d((.3),(.34)),
           new Translation2d((.3),(.4)),
           new Translation2d((.3),(.43)),
           new Translation2d((.3),(.45)),
           new Translation2d((.15),(.45))
           ),
         new Pose2d(((-.08)), (.45), new Rotation2d(Math.PI/1)),
        config);

    
    //******************* END COURSE 2 *********************
  

    //******************* START COURSE 1 *********************
    
    Trajectory exampleTrajectory1 = TrajectoryGenerator.generateTrajectory(
       // Start at the origin facing the +X direction
        new Pose2d(0, 0, new Rotation2d(0)),
       List.of(
         new Translation2d((.84),(-.18)),
         new Translation2d((.78),(-.35)),  // changed from .4
         new Translation2d((.7),(-.4)),
         new Translation2d((.5),(0)),
         new Translation2d((1),(0)),
         new Translation2d((1.3),(0)),
         new Translation2d((1.5),(0)),
         new Translation2d((1.55),(.4)),    
         new Translation2d((1.55),(.5)),
         new Translation2d((1),(.3)),
         new Translation2d((1),(-.3)),
         new Translation2d((1.3),(0)),
         new Translation2d((1.9),(.1)),
         new Translation2d((1.8),(.3))
         //new Translation2d((.5),(-.6))
        ),
      new Pose2d(((0)), (-.6), new Rotation2d(Math.PI/1)),
      config);
    
    //******************* END COURSE 1 *********************

    // Set Trejectory to Use for Autonomous Run
    Trajectory exampleTrajectory = exampleTrajectory2;


    RamseteCommand ramseteCommand = new RamseteCommand(
        exampleTrajectory,
        m_drivetrain::getPose,
        new RamseteController(AutoConstants.kRamseteB, AutoConstants.kRamseteZeta),
        new SimpleMotorFeedforward(DriveConstants.ksVolts, DriveConstants.kvVoltSecondsPerMeter, DriveConstants.kaVoltSecondsSquaredPerMeter),
        DriveConstants.kDriveKinematics,
        m_drivetrain::getWheelSpeeds,
        new PIDController(DriveConstants.kPDriveVel, 0, 0),
        new PIDController(DriveConstants.kPDriveVel, 0, 0),
        m_drivetrain::tankDriveVolts,
        m_drivetrain);

    m_drivetrain.resetOdometry(exampleTrajectory.getInitialPose());

    // Set up a sequence of commands
    // First, we want to reset the drivetrain odometry
    return new InstantCommand(() -> m_drivetrain.resetOdometry(exampleTrajectory.getInitialPose()), m_drivetrain)
        // next, we run the actual ramsete command
        .andThen(ramseteCommand)

        // Finally, we make sure that the robot stops
        .andThen(new InstantCommand(() -> m_drivetrain.tankDriveVolts(0, 0), m_drivetrain));
  } 

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Default command is arcade drive. This will run unless another command
    // is scheduled over it.
    m_drivetrain.setDefaultCommand(getArcadeDriveCommand());

    // Example of how to use the onboard IO
    Button onboardButtonA = new Button(m_onboardIO::getButtonAPressed);
    onboardButtonA
        .whenActive(new PrintCommand("Button A Pressed"))
        .whenInactive(new PrintCommand("Button A Released"));

    // Setup SmartDashboard options
    //m_chooser.setDefaultOption("Ramsete Trajectory", generateRamseteCommand());
    m_chooser.setDefaultOption("Auto Routine Distance", new AutonomousDistance(m_drivetrain));
    //m_chooser.addOption("Auto Routine Distance", new AutonomousDistance(m_drivetrain));
    m_chooser.addOption("Auto Routine Time", new AutonomousTime(m_drivetrain));
    
    SmartDashboard.putData(m_chooser);
   // SmartDashboard.putNumber(">> Left Drive Encoder", m_drivetrain.getLeftEncoderCount());
   // SmartDashboard.putNumber(">> Right Drive Encoder", m_drivetrain.getRightEncoderCount());
   // SmartDashboard.putNumber(">> Left Drive Distance", m_drivetrain.getLeftDistanceMeter());
   // SmartDashboard.putNumber(">> Right Drive Distance", m_drivetrain.getRightDistanceMeter());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
    

  }

  /**
   * Use this to pass the teleop command to the main {@link Robot} class.
   *
   * @return the command to run in teleop
   */

  // Modified getArcadeDriveCommand to work with GameCube controller
  // - separated acceleration from steering control
  // - normalized the steering and trigger (acceleration) control
  public Command getArcadeDriveCommand() {
    //double sccelerator = (((m_controller.getRawAxis(4) * 1.25) + 1) * 0.5);
    //double turning = m_controller.getRawAxis(0) * (4/3);
    return new ArcadeDrive(
      m_drivetrain,
      () -> normalizeTriggerWithDeadband(m_controller.getRawAxis(4), 0.1) - normalizeTriggerWithDeadband(m_controller.getRawAxis(3), 0.1),
      () -> noramlizeSteering(m_controller.getRawAxis(0))
    );
    
  }
    //if (rawInput > deadband) {
    // rawInput = rawInput 
   // } else {
     // rawInput = 0;
   //}
  public static double normalizeTriggerWithDeadband(double rawInput, double deadband) {
    final double offset = 0.82;
    deadband = Math.abs(deadband);
    double retVal = 0;
    rawInput += offset;
    rawInput = Math.abs(rawInput) > deadband ? rawInput : 0;
    if (rawInput != 0) {
      retVal = Math.signum(rawInput) * (Math.abs(rawInput) - deadband) / ((offset*2) - deadband);
    }
  
    return retVal;
  }
  public static double noramlizeSteering(double rawStick)
  {
    double finVal = 0;
    if (rawStick != 0){
    finVal = rawStick * (1.5);
    }
    return finVal;
  }
}
