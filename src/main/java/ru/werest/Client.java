package ru.werest;

import ru.werest.logger.ClientLogger;
import ru.werest.service.PropertiesService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Scanner;

import static ru.werest.Constants.*;

public class Client {
    private static PrintWriter printWriter;
    private static BufferedReader bufferedReader;
    static Socket socket;

    private static Scanner scanner;

    private static final ClientLogger clientLogger = ClientLogger.getInstance();

    static String nickName;

    public static void main(String[] args) throws IOException {
        System.out.println(CLIENT_PATH_PROPS);

        scanner = new Scanner(System.in);
        String pathProperties = scanner.nextLine();

        PropertiesService propertiesService = new PropertiesService();
        Properties properties = propertiesService.get(pathProperties);

        int port = Integer.parseInt(properties.getProperty(PORT));
        String host = properties.getProperty(HOST);

        clientLogger.setPath(properties.getProperty(CLIENT_LOG));

        System.out.println(CLIENT_READ_PROPS_SUCCESS);

        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            System.out.println(CLIENT_SERVER_BAD_GATEWAY);
            return;
        }

        try {
            printWriter = new
                    PrintWriter(socket.getOutputStream(), true);
            bufferedReader = new BufferedReader(new
                    InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println(AUTH_CLIENT_APP);
        System.out.println(NAME_CLIENT);

        nickName = scanner.nextLine();
        printWriter.println(nickName); //nickname
        try {
            System.out.println(bufferedReader.readLine()); //Добро пожаловать на наш сервер!
            new Write().start();
            new Read().start();
        } catch (SocketException e) {
            System.out.println(CLIENT_SERVER_DOWN);
        }
    }

    public static class Write extends Thread {
        @Override
        public void run() {
            while (!isInterrupted()) {
                String msg = scanner.nextLine();
                printWriter.println(msg);
                clientLogger.log(nickName, msg);
                if(msg.equals("/exit")) {
                    interrupt();
                    break;
                }
                printWriter.flush();
            }
        }
    }

    public static class Read extends Thread {
        @Override
        public void run() {
            String msgFromServer;
            while (true) {
                try {
                    msgFromServer = bufferedReader.readLine();
                } catch (IOException e) {
                    interrupt();
                    break;
                }

                if (msgFromServer.equals("/exit")) {
                    interrupt();
                    break;
                }
                System.out.println("[" + LocalDateTime.now() + "] " + msgFromServer);
                clientLogger.log(msgFromServer);
            }
        }
    }
}

