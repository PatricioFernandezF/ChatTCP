package TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class ServidorTCP{
	
	public static ArrayList<ManejadorCliente> clientes;
	
	public static void main(String[] args) {
		clientes=new ArrayList<ManejadorCliente>();
		int i=1;
        try {
        	
        	System.out.println("Servidor Iniciado");
        	
            // Crea una instancia de ServerSocket en el puerto especificado
            ServerSocket serverSocket = new ServerSocket(9991);

            // Bucle infinito para escuchar y aceptar conexiones entrantes
            while (true) {
                // Acepta una nueva conexión
                Socket socketCliente = serverSocket.accept();
                
                
                System.out.println("Cliente Nuevo");

                // Crea una nueva instancia de ClientHandler para manejar la conexión con el cliente
                ManejadorCliente manejadorCliente = new ManejadorCliente(socketCliente,"Cliente "+i);
                i+=1;
                
                clientes.add(manejadorCliente);

                // Inicia el manejador del cliente en un hilo separado
                manejadorCliente.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void mandarMensajes(String nombre,String mensaje) {
		
		for (int i = 0; i < clientes.size(); i++) {
			clientes.get(i).mandarMensaje(nombre,mensaje);
		}
		
	}
	
	
	

}
