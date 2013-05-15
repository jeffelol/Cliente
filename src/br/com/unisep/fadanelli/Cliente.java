
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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
    public static void main(String[] args){
        try {
            Socket socket = new Socket("localhost", 4444);
            
            String resp;
            OutputStream saida;
            saida = socket.getOutputStream();
            PrintStream print = new PrintStream(saida);

            InputStream resposta;
            resposta = socket.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(resposta));
            
            print.println("MSG");
            
            Mensagem me = new Mensagem("Juca", "Testando 123");
            
            Gson gson = new Gson();
            String msg = gson.toJson(me);
            print.println(msg);
            
            resp = read.readLine();
            
            System.out.println("" + resp);
            
            print.close();
            saida.close();
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}
