import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;

public class MioThread extends Thread
{

    Socket s;
    int n_biglietti;

    public MioThread(Socket s, int b)
    {
        this.s = s;
        this.n_biglietti=b;
    }

    public void run()
    {
        try 
        {
            System.out.println("Un client si è collegato");

            BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream())); //istanza per ricevere dati dal client
            DataOutputStream output = new DataOutputStream(s.getOutputStream()); //istanza per inviare dati al client
            int n=1; 
            do 
            {
                String stringaRicevuta = input.readLine(); //riceve dati

                if(stringaRicevuta=='D')
                {
                    output.writeBytes("disponibili "+n_biglietti+"\n"); //invia dati
                }
                if(stringaRicevuta=='A')
                {
                    if(n_biglietti==0)
                        output.writeBytes("biglietti terminati\n");
                    else
                    {
                        output.writeBytes("biglietto acquistato\n");
                        n_biglietti--;
                    }
                }
                if(stringaRicevuta=='Q')
                {
                    output.writeBytes("exit\n");
                    n=2;
                }
            } while (n!=2);
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