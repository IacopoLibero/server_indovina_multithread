import java.net.ServerSocket;
import java.net.Socket;

public class App 
{
    public static void main(String[] args) 
    {
        try 
        {
            System.out.println("Server in avvio!");
            ServerSocket server = new ServerSocket(4567); //crea server su cui ricevere
            do 
            {
                Socket s = server.accept(); //crea socket
                MioThread m = new MioThread(s); //crea processo
                m.start(); //start processo
            } 
            while (true);
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
            System.out.println("errore durante l'istanza del server");
            System.exit(1);
        }
    }
}

