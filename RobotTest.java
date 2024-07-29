package salomia.Robot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RobotTest {
    public static void main(String[] args) {

      Robot monRobot = new Robot("mofo", 2, 3, 2, "ouest");
      monRobot.recuperation("mofo");


      // il faut encore reprendre les valeurs dans la base de donnée pour les updater

   }

}
