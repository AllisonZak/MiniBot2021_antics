// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
//Alex
package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveDistance extends CommandBase {
  private final Drivetrain m_drive;
  private final double m_distance;
  private final double m_speed;

  /**
   * Creates a new DriveDistance. This command will drive your your robot for a desired distance at
   * a desired speed.
   *
   * @param speed The speed at which the robot will drive
   * @param inches The number of inches the robot will drive
   * @param drive The drivetrain subsystem on which this command will run
   */
  public DriveDistance(double speed, double inches, Drivetrain drive) {
    m_distance = inches;
    m_speed = speed;
    m_drive = drive;
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drive.arcadeDrive(0, 0);
    m_drive.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.arcadeDrive(m_speed, 0);
    SmartDashboard.putNumber(">>Auto Left Drive Encoder", m_drive.getLeftEncoderCount());
    SmartDashboard.putNumber(">>Auto Right Drive Encoder", m_drive.getRightEncoderCount());
    SmartDashboard.putNumber(">>Auto Left Drive Distance", m_drive.getLeftDistanceMeter());
    SmartDashboard.putNumber(">>Auto Right Drive Distance", m_drive.getRightDistanceMeter());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.arcadeDrive(0, 0);
    System.out.print("\nSpeed: " + m_speed + " Distance: " + m_distance + "\n");
    System.out.print("Left Distance: " + m_drive.getLeftDistanceMeter() + "\n");
    System.out.print("Right Distance: " + m_drive.getRightDistanceMeter() + "\n");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // Compare distance travelled from start to desired distance
    return Math.abs(m_drive.getAverageDistanceMeter()) >= m_distance;
  }
}
