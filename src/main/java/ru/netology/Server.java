package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final Integer LOCALHOST_PORT = 8080;

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(LOCALHOST_PORT)) {
            System.out.println("Сервер стартовал");
            while (true) {
                try (
                        Socket clientSocket = serverSocket.accept();
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {
                    System.out.println("New connection accepted. " + clientSocket.getPort());

                    out.println("Write your name");
                    String name = in.readLine();

                    out.println("Are you child? (yes/no)");
                    String response = in.readLine();

                    switch (response) {
                        case "no": {
                            out.printf("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name);
                            break;
                        }
                        case "yes": {
                            out.printf("Welcome to the kids area, %s! Let's play!", name);
                            break;
                        }
                        default: {
                            out.printf("Your response not valid. Welcome to the kids area, %s! Let's play!", name);
                            break;
                        }
                    }

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}