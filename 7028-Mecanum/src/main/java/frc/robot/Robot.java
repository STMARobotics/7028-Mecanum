package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private TalonSRX leftFront = new TalonSRX(0);
  private TalonSRX rightFront = new TalonSRX(1);
  private TalonSRX leftRear = new TalonSRX(2);
  private TalonSRX rightRear = new TalonSRX(3);
  private MechaniumDrive driveTrain = new MechaniumDrive(leftFront, leftRear, rightFront, rightRear);
  private XboxController controller = new XboxController(0);
  
  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();

    leftFront.configFactoryDefault();
    leftRear.configFactoryDefault();
    rightRear.configFactoryDefault();
    rightFront.configFactoryDefault();
    leftRear.setInverted(true);
    leftFront.setInverted(true);
    leftFront.setNeutralMode(NeutralMode.Brake);
    leftRear.setNeutralMode(NeutralMode.Brake);
    rightFront.setNeutralMode(NeutralMode.Brake);
    rightRear.setNeutralMode(NeutralMode.Brake);

  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    driveTrain.driveCartesian(-controller.getY(Hand.kLeft), controller.getX(Hand.kLeft), controller.getX(Hand.kRight));
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }
}
