package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Server {
    private List<ClientHandler> clients;
    private AuthService authService;

    private int PORT = 8189;
    ServerSocket server = null;
    Socket socket = null;

    public Server() {
        clients = new Vector<>();
        authService = new SimpleAuthService();

        try {
            server = new ServerSocket(PORT);
            System.out.println("Сервер запущен");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");

                new ClientHandler(this, socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public void broadcastMsg(ClientHandler sender, String msg) {
        String senderNickname = sender.getNickname();
        String _clientNickname = null;
        String _msg = msg;
        if (msg.startsWith("/w ")) {
            String[] tmp = msg.split("\\s");
            if (tmp.length > 2) {
                _clientNickname = tmp[1];
                _msg = String.join(" ", Arrays.copyOfRange(tmp, 2, tmp.length))
                        .trim();
            }
        }
        String message = String.format("%s : %s", senderNickname, _msg);

        for (ClientHandler c : clients) {
            String clientNickname = c.getNickname();
            if ((_clientNickname == null)
                    || (clientNickname.equals(_clientNickname)
                    || (clientNickname.equals(senderNickname)))
            ) {
                c.sendMsg(message);
            }
        }
    }

    public void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }

}
