package client;

import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.Scanner;

import util.*;


public class Main {
    private static String serverAddress;
    private static int serverPort;
    private static InetAddress ipAddress;
    private static String macAddress;

    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private static String myName;
    private static final Scanner scanner = new Scanner(System.in);
    private static Socket socket;

    public static void main(String[] args) throws IOException {
        serverAddress = "127.0.0.1";
        serverPort = 12345;

        // Set client address and mac address for messages
        ipAddress = InetAddress.getLocalHost();
        byte[]mac = NetworkInterface.getByInetAddress(ipAddress).getHardwareAddress();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
        }
        macAddress = sb.toString();

        socket = new Socket(serverAddress, serverPort);
        out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
        in = new ObjectInputStream(socket.getInputStream());

        System.out.print("Enter your name: ");
        myName = scanner.nextLine();

        // Create separate threads for input and output
        Thread inputThread = new Thread(Main::handleInput);
        Thread outputThread = new Thread(Main::handleOutput);

        try {
            inputThread.start();
            outputThread.start();
            inputThread.join();
            outputThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            try{
                in.close();
                out.close();
                socket.close();
            }  catch (IOException e) {
                System.err.println("Error closing connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    private static void handleInput(){
        try{
            while (socket.isConnected()){
                Message message = (Message) in.readObject();
                System.out.println(message);
                System.out.print(">");
            }
        } catch (IOException | ClassNotFoundException e){
            if (e.getMessage() != null){
                System.err.println("Error reading from server: " + e.getMessage());
            }
        }
    }
    private static void handleOutput(){
        try{
            System.out.print(">");
            while (socket.isConnected()){
                String input = scanner.nextLine();
                Message message;
                if(input.charAt(0) == '/'){
                    String commandString = input.split("\s")[0];
                    String operand = input.substring(commandString.length());
                    message = new CommandMessage(serverAddress, serverPort, ipAddress.getHostAddress(), socket.getLocalPort(), macAddress, operand, Command.getCommand(commandString));
                }else{
                    message = new Chat(serverAddress, serverPort, ipAddress.getHostAddress(), socket.getLocalPort(), macAddress, input, myName);
                }

                out.writeObject(message);
                out.flush();

                if (message.toString().equals("/exit")) {
                    socket.close();
                    System.exit(0);
                }
            }
        } catch (IOException  e){
            System.err.println("Error sending to server: " + e.getMessage());
        }
    }
}
