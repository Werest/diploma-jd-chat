package ru.werest;

import ru.werest.logger.ServerLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;

import static ru.werest.Constants.*;
import static ru.werest.ServerImpl.clients;


public class Server implements Runnable {

    ServerLogger serverLogger = ServerLogger.getInstance();

    Socket clientSocket;

    public Server(Socket socket) {
        this.clientSocket = socket;
    }


    @Override
    public void run() {
        System.out.println(SERVER_CLIENT_CONNECT + this.clientSocket.getInetAddress() + ":" + this.clientSocket.getPort());
        serverLogger.log(SERVER_CLIENT_CONNECT + this.clientSocket.getInetAddress() + ":" + this.clientSocket.getPort());

        String nickName = USER_ANON;

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));

            nickName = bufferedReader.readLine();

            PrintWriter printWriter = new PrintWriter(this.clientSocket.getOutputStream());
            printWriter.println(WELCOME_SERVER + nickName);
            printWriter.flush();

            sendMessageClients(CONNECT_USER_MESSAGE + nickName);
            serverLogger.log(WELCOME_SERVER + nickName);
            serverLogger.log(CONNECT_USER_MESSAGE + nickName);
        } catch (IOException e) {
            try {
                disconnectClient(this.clientSocket);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        while (true) {
            try {
                assert bufferedReader != null;

                String message;
                message = bufferedReader.readLine();

                if (message.equals("/exit")) {
                    sendMessageClients(BYE_SERVER + nickName);
                    clients.remove(clientSocket);
                    serverLogger.log(BYE_SERVER + nickName);

                    PrintWriter printWriter = new PrintWriter(this.clientSocket.getOutputStream());
                    printWriter.println(message);
                    printWriter.flush();

                    break;
                } else {
                    String sendMessage = nickName + ">> " + message;
                    sendMessageClients(sendMessage);
                    serverLogger.log(sendMessage);
                }
            } catch (IOException e) {
                if (!this.clientSocket.isClosed()) {
                    try {
                        disconnectClient(this.clientSocket);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                break;
            }
        }
    }

    private void sendMessageClients(String message) {
        List<Socket> clientList = clients.stream().filter(x -> x != this.clientSocket).collect(Collectors.toList());

        for (Socket cl : clientList) {
            try {
                PrintWriter printWriter = new PrintWriter(cl.getOutputStream());
                printWriter.println(message);
                printWriter.flush();
            } catch (IOException e) {
                break;
            }
        }
    }

    private void disconnectClient(Socket clientSocket) throws IOException {
        System.out.println(CLIENT_DOWN_CONNECT + this.clientSocket.getInetAddress() + ":" + this.clientSocket.getPort());
        clientSocket.close();
        serverLogger.log(CLIENT_DOWN_CONNECT + this.clientSocket.getInetAddress() + ":" + this.clientSocket.getPort());
    }
}
