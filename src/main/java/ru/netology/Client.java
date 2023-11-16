package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (
                Socket clientSocket = new Socket("netology.homework", Server.LOCALHOST_PORT);
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            Scanner scanner = new Scanner(System.in);

            System.out.println(in.readLine());
            String name = scanner.nextLine();
            out.println(name);

            System.out.println(in.readLine());
            String response = scanner.nextLine();
            out.println(response);

            System.out.println(in.readLine());

            scanner.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
