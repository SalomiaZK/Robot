package salomia.Robot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;



public class Robot {
    private String name;
    private int id;
    private int x;
    private  int y;
    private String direction = "nord";

    public static int prompt() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your choice: ");
        int userChoice = Integer.parseInt(scanner.nextLine());
        return userChoice;

    }

    public static void menu() {
        System.out.println("======= WELCOME MASTER ====== ");
        System.out.println("|                             |");
        System.out.println("|      1.Avancer              |");
        System.out.println("|      2.tourner              |");
        System.out.println("|      3.Etat du robot        |");
        System.out.println("|      4.Quit                 |");
        System.out.println("|                             |");
        System.out.println("         ============          ");
    }

    public void avancer(){
       if(direction.equals("nord"))
           y+= 1;
       if(direction.equals("sud"))
           y-=1;
       if(direction.equals("est"))
           x+= 1;
       if(direction.equals("ouest"))
           x-=1;
        try {
            // cherche une classe du meme nom
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/robotic", "postgres", "hello");
            Statement stmt = conn.createStatement();
            int res = stmt.executeUpdate("update robot set y = "+ y +", x = " + x + " where id = "+ this.id +""); // ensemble de resultats

            if(res == 1){
                System.out.println("well done");
            }else {
                System.out.println("domazy");
            }
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);}
        System.out.println("avancer de " + y);
    } // don't freaking touch it, it works


   /* void choice(int choix) {
        if (choix == 1) {
            avancer();
        }
        if (choix == 2) {
            tourner();
        }
        if (choix == 3) {
            showInfo();
        }
    }*/

    public void showInfo() {
        System.out.println("la place du robot est " + direction);
    }

    public void tourner(){
      switch (direction){
          case "nord":
              direction = "est";
              break;
          case "est":
              direction = "sud";
              break;
          case "sud":
              direction = "ouest";
              break;
          case "ouest":
              direction = "nord";
              break;
      }

        try {
            // cherche une classe du meme nom
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/robotic", "postgres", "hello");
            Statement stmt = conn.createStatement();
            int res = stmt.executeUpdate("update robot set direction = '"+ direction +"' where id = " + this.id); // ensemble de resultats

            if(res == 1){
                System.out.println("well done");
            }else {
                System.out.println("domazy");
            }
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);}
      // upaate table robot form robotic set direction = direction where id = this.id
        System.out.println("Sa direction est maintenant :" + direction);
    } // mec ce truc marche, n y touche plus

    public  void  createRobot(){
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/robotic", "postgres", "hello");
            Statement stmt = conn.createStatement();
            int res = stmt.executeUpdate("insert into robot values('" + this.name+ "', '" +this.direction+ "', 0, 0," + this.id +");");


            //  assert (res ==2) : ("domazy");
            if(res == 1){
                System.out.println("well done");
            }else {
                System.out.println("domazy");
            }
            conn.close();
        }
        catch (Exception e){
            System.out.println(e);
        }




    } // aza kitihana ral a

    public  Robot recuperation(String name){
        // recuperation : manao new robot(name, direction , x, y , id)(tous les attributs avec leurs valeurs respectif) fa tsy implementena fa stockena (en tant que données java)
        // manao select aloh de alaina avy amin'ilay select ireo values ilain
try {
    Class.forName("org.postgresql.Driver");
    Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/robotic", "postgres", "hello");
    Statement statement = conn.createStatement();
    ResultSet res = statement.executeQuery("select * from robot where name =" + name);
        var id = res.getInt("id");
        var nom = res.getString("name");
        var x = res.getInt("x");
        var y = res.getInt("y");

        // me reste plus qu a trouver ou suis je censé instancier mon robot

    Robot newRobot = new Robot(nom, id, x, y, direction);

    return newRobot;

}catch (Exception e){
    System.out.println(e);
}
return null;
    }


    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Robot(String name, int id, int x, int y, String direction) {
        this.name = name;
        this.id = id;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public Robot(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Robot() {
    }


}
