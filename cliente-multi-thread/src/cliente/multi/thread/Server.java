/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente.multi.thread;

import java.net.ServerSocket;
import java.io.IOException;
import java.net.Socket;
/**
 *
 * @author User
 */
public class Server {
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    public void startServer() {
        try {
            //esperando por conexao dos clientes na porta
            while (!serverSocket.isClosed()) {
                
                Socket socket = serverSocket.accept();
                 System.out.println("Um novo cliente " + socket.getInetAddress().getHostAddress() + " conectado");
                ClientHandler clientHandler = new ClientHandler(socket);
                
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        }catch (IOException e) {
            closeServerSocket();
            
        }
    }
    public void closeServerSocket() {
       try {
           if(serverSocket != null) {
               serverSocket.close();
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
    
    public static void main (String [] args) throws IOException {
        
        ServerSocket serverSocket = new ServerSocket(4444);
        System.out.println("Servidor ON! esperando conexao!");
        Server server = new Server(serverSocket);
        server.startServer();
    }
}
