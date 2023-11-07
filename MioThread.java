import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;

public class MioThread extends Thread
{

    Socket s;

    public MioThread(Socket s)
    {
        this.s = s;
    }

    public void run()
    {
        try 
        {
            System.out.println("Un client si è collegato");

            Random random = new Random();
            int numero = random.nextInt(100)+1;
            System.out.println(numero);

            BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream())); //istanza per ricevere dati dal client
            DataOutputStream output = new DataOutputStream(s.getOutputStream()); //istanza per inviare dati al client
            int risposta = 0;
            do 
            {
                String stringaRicevuta = input.readLine(); //riceve dati
                int numeroRicevuto = Integer.parseInt(stringaRicevuta);
                System.out.println("Numero ricevuto: " + numeroRicevuto);
                if(numeroRicevuto>numero)
                {
                    output.writeBytes("2\n"); //invia dati
                    risposta = 2;
                }
                if(numeroRicevuto<numero)
                {
                    output.writeBytes("1\n"); //invia dati
                    risposta = 1;
                }    
                if(numeroRicevuto==numero)
                {
                    output.writeBytes("3\n"); //invia dati
                    risposta = 3;
                }
            } while (risposta != 3);
            s.close(); //chiude socket
        }
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
            System.out.println("errore durante l'istanza del server");
            System.exit(1);
        }
    }
}