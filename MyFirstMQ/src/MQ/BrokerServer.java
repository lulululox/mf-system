package MQ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class BrokerServer implements Callable {
    public static final int SERVICE_PORT = 9999;
    private Socket socket;

    public BrokerServer(Socket socket) {
        this.socket = socket;
    }

    public BrokerServer() {

    }

    @Override
    public String call() throws Exception {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream())
        ) {
            while (true) {
                String str = in.readLine();
                if (str == null) {
                    continue;
                }
                System.out.println("接收到原始数据：" + str);

                if (str.equals("CONSUME")) { //CONSUME 表示要消费一条消息
                    //从消息队列中消费一条消息
                    String message = Broker.consume();
                    out.println(message);
                    out.flush();
                } else {
                    //其他情况都表示生产消息放到消息队列中
                    Broker.produce(str);
                }
                return str;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        BrokerServer brokerserver=new BrokerServer();
        ServerSocket server = new ServerSocket(SERVICE_PORT);
        while (true) {
            ExecutorService ser=Executors.newFixedThreadPool(3);
            BrokerServer brokerServer = new BrokerServer(server.accept());
            FutureTask<String> futureTask = new FutureTask<>(brokerserver);
            new Thread(futureTask).start();
//            String str= futureTask.get();

        }
    }
}

