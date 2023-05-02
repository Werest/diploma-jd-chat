package ru.werest;

import ru.werest.logger.ServerLogger;

import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static ru.werest.Constants.*;

public class ServerImpl {
    static List<Socket> clients = new ArrayList<>();
    static int port;

    private static final ServerLogger serverLogger = ServerLogger.getInstance();

    public static void main(String[] args) {
        //Properties
        try (FileReader fileReader = new FileReader(PROPERTIES_FILE)) {
            Properties properties = new Properties();
            properties.load(fileReader);
            port = Integer.parseInt(properties.getProperty(PORT));
            serverLogger.setPath(properties.getProperty(SERVER_LOG));
        } catch (IOException e) {
            System.out.println(ERROR_LOAD_PROPERTIES_FILE);
            return;
        }
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер стартовал! " +
                    serverSocket.getInetAddress().getHostAddress() + ":" + serverSocket.getLocalPort());

            while (true) {
                Socket clientSocket = serverSocket.accept();
                clients.add(clientSocket);
                new Thread(new Server(clientSocket)).start();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
