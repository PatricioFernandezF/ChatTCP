package TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTCP{
	
	private String serverAddress;
    private int port;
    private Socket socket;
    
    int opcion;
    
    BufferedReader in;
    PrintWriter out;
    
    public static void main(String[] args) {
    	ClienteTCP cliente=new ClienteTCP("localhost", 9991);
    	
    }
    
    public ClienteTCP(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
        Lector l=new Lector();
        Escritor e=new Escritor();
        
    
        try {
			conectar();
			l.start();
	        e.start();
	        
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        
        
    }

    public void conectar() throws IOException {
        socket = new Socket(serverAddress, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }
    
    public void enviarMensaje(String message) {
        out.println(message);
    }
    
    public String recibirMensaje() throws IOException {
    	//System.out.println("Escuchando");
        return in.readLine();
    }
    
    
    public class Lector extends Thread{
    	
    	public Lector() {
    		
    	}
    	
    	public void run()
    	{
    		while(true)
    		{
    			try {
    				String frase=recibirMensaje();
    				System.out.println(frase);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		
    	}
    	
    	
    }
    
    public class Escritor extends Thread{
    	
	
		public Escritor() {

		}
	
    	public void run()
    	{
    		while(true)
    		{
	    		Scanner scanner = new Scanner(System.in);
	    	    String mensaje = scanner.nextLine();
	    	    //System.out.println("Mensaje enviado");
	    	    enviarMensaje(mensaje);
	    	    
    		}
    	}
    	
    	
    }
    

}
