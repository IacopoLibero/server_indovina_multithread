import java.net.ServerSocket;
import java.net.Socket;

public class App 
{
    public static void main(String[] args) 
    {
        int n=5;
        try 
        {
            System.out.println("Server in avvio!");
            ServerSocket server = new ServerSocket(4567); //crea socket su cui ricevere
            do 
            {
                Socket s = server.accept(); //accetta connessione
                MioThread m = new MioThread(s,n); //crea thread
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

