package _01.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BlockingServer {

    public static int DEFAULT_PORT = 8888;

    /**
     * @param args
     */
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            // 服务器监听
            serverSocket = new ServerSocket(DEFAULT_PORT);
            System.out.println("已启动，端口：" + DEFAULT_PORT);
        } catch (IOException e) {
            System.out.println("启动异常，端口：" + DEFAULT_PORT);
            System.out.println(e.getMessage());
        }
        try (
                // 接受客户端建立链接，生成Socket实例
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                // 接收客户端的信息
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                // 发送信息给客户端
                out.println(inputLine);
                System.out.println("BlockingServer -> " + clientSocket.getRemoteSocketAddress() + ":" + inputLine);
            }
        } catch (IOException e) {
            System.out.println("BlockingServer异常!" + e.getMessage());
        }
    }

}