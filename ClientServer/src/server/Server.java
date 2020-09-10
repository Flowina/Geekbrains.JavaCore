package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private int PORT = 8189;
    ServerSocket server = null;
    Socket socket = null;
    private DataInputStream in;
    private DataOutputStream out;

    public Server() {
        try {
            server = new ServerSocket(PORT);
            System.out.println("Сервер запущен");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");

                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());

                // поток для приема сообщений
                createClientListenerThread().start();

                // поток для отправки сообщений, введенных с клавиатуры
                createInputListenerThread().start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Thread createInputListenerThread() {
        return new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                do {
                    String text = scanner.nextLine();
                    sendMsg(text);
                } while (true);
            }
        });
    }

    private Thread createClientListenerThread() {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String str = in.readUTF();

                        if (str.equals("/end")) {
                            out.writeUTF("/end");
                            break;
                        }
                        System.out.println("[" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "] " + str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Клиент отключился");
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    void sendMsg(String msg) {
        try {
            if (socket != null && !socket.isClosed()) {
                out.writeUTF(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
