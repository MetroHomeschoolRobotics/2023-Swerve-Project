// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Drivetrain;

public class DriveTeleop extends CommandBase {

  private Drivetrain drivetrain;
  private CommandXboxController xboxController;

  /** Creates a new DriveTeleop. */
  public DriveTeleop(Drivetrain _drivetrain, CommandXboxController _xboxController) {
    drivetrain = _drivetrain;
    xboxController = _xboxController;
    addRequirements(_drivetrain);



    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double controllerAngle = Math.atan2(xboxController.getLeftY(), xboxController.getLeftX());
    double magnitude = MathUtil.applyDeadband(xboxController.getRawAxis(0), xboxController.getRawAxis(1), 0.01);
    double twist = MathUtil.applyDeadband(xboxController.getRightX(), 0.01);

    controllerAngle -= drivetrain.getRotation();
    
    drivetrain.drive(xboxController.getLeftX(), xboxController.getLeftY(), controllerAngle, isFinished());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
