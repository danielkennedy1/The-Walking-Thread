package client;

import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.Scanner;

import util.Message;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        String serverAddress = "127.0.0.1"; // Replace with the actual server address
        int serverPort = 12345; // Replace with the actual server port

        // Get the MAC address of the client
        InetAddress ipAddress = InetAddress.getLocalHost();
        NetworkInterface network = NetworkInterface.getByInetAddress(ipAddress);
        byte[] mac = network.getHardwareAddress();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
        }
        String macAddress = sb.toString();

        boolean running = true;

        while (running){
            System.out.print("Enter message: ");
            String content = scanner.nextLine();

            try (Socket socket = new Socket(serverAddress, serverPort);
                 //PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                 ObjectOutput out = new ObjectOutputStream(socket.getOutputStream())) {

                Message message = new Message(serverAddress, serverPort, ipAddress.getHostAddress(), socket.getLocalPort(), macAddress, content);

                out.writeObject(message);

                if (message.toString().equals("exit")){
                    running = false;
                }

                System.out.println("Message sent to " + serverAddress + ":" + serverPort);

                //listen for response from server
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                Message response = (Message) inputStream.readObject();

                System.out.println("Response received from " + response.getServerAddress() + ":" + response.getServerPort());
                System.out.println("Response content: " + response);
            } catch (IOException e) {
                System.err.println("Error connecting to server: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
