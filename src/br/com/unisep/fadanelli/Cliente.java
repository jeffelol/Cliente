
package br.com.unisep.fadanelli;

import br.com.unisep.jeferson.Mensagem;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
    private String mensa;
    private String minhasMensagens;
    PrintStream print;
    BufferedReader read;

    public Cliente() {

        try {
            Socket socket = new Socket("localhost", 4444);

            String resp;
            OutputStream saida;
            saida = socket.getOutputStream();
            print = new PrintStream(saida);

            InputStream resposta;
            resposta = socket.getInputStream();
            read = new BufferedReader(new InputStreamReader(resposta));

        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    public String getMinhasMensagens() {
        print.println("LER");
        try {
            minhasMensagens = read.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (minhasMensagens.isEmpty()){
            minhasMensagens = "Nenhuma mensagem";
        }
        return minhasMensagens;
    }
    
    public String getMensa() {
        return mensa;
    }

    public void setMensa(String mensa) {
            print.println("MSG");

            Mensagem me = new Mensagem("Juca", mensa);

            Gson gson = new Gson();
            String msg = gson.toJson(me);
            print.println(msg);
    }
}
