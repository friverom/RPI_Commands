/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpi_commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Federico
 */
public class RPI_Commands {

    static Socket socket = null;
    static private BufferedReader bfin = null;
    static private InputStreamReader in = null;
    static private PrintWriter out = null;
    static private String thisline="";
    private static final int INTRUSION = 30001;
    private static final int ENERGY = 30002;
    private static final int LIGHTS = 30003;
    private static final int AC = 30004;

    public static void main(String[] args) throws IOException {
        
        int port=0;
                
        switch(args[0].toLowerCase()){
            case "ac":
                port=AC;
                break;
            case "lights":
                port=LIGHTS;
                break;
            case "energy":
                port=ENERGY;
                break;
            case "intrusion":
                port=INTRUSION;
        }
        if(port!=0){
        socket = new Socket("localhost", port);
        in = new InputStreamReader(socket.getInputStream());
        bfin = new BufferedReader(in);
        out = new PrintWriter(socket.getOutputStream(), true);
        String command=args[1].toLowerCase()+" "+args[2].toLowerCase();
        if(args.length==4){
            command=command+" "+args[3].toLowerCase();
        }
        System.out.println(command);
        out.println(command);
        while((thisline=bfin.readLine())!=null){
            System.out.println(thisline);
        }
        bfin.close();
        out.close();
        in.close();
        socket.close();
        } else {
            System.out.println("Command error");
        }
    }
    
}
