import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.PriorityQueue;

public class TcpIpServer {

    public final int        SOCKET_SERVER_PORT;
    ServerSocket            serverSocket;
    PriorityQueue<Socket>   socketQueue;

    public TcpIpServer () {
        this.SOCKET_SERVER_PORT = 7777;
    }

    public void start() {
        try {
            serverSocket =  new ServerSocket(SOCKET_SERVER_PORT);
            socketQueue  =  new PriorityQueue<>();

            SocketQueueManager    queueManager   = new SocketQueueManager(socketQueue, serverSocket);
            SocketLinkingManager  linkingManager = new SocketLinkingManager(socketQueue);
            queueManager.start();
            linkingManager.start();
            System.out.println("서버가 시작되었습니다.");

        } catch (IOException e) {e.printStackTrace();}
    }

}