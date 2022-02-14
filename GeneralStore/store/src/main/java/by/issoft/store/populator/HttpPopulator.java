package by.issoft.store.populator;

import by.issoft.store.Store;
import lombok.SneakyThrows;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class HttpPopulator extends Populator{

    //это все перенесется в HttpServer!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    static final int port = 8080;
    static final String newLine = "\r\n";

    public HttpPopulator(Store store) { super(store); }

    @SneakyThrows
    @Override
    public void fillStoreWithPopulator() {
        try
        {
        ServerSocket socket = new ServerSocket(port);

        while (true)
        {
            Socket connection = socket.accept();

            try
            {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            OutputStream out = new BufferedOutputStream(connection.getOutputStream());
            PrintStream pout = new PrintStream(out);

            String request = in.readLine();
            if(request==null) continue;

            while (true)
            {
                String ignore = in.readLine();
                if (ignore==null) || ignore.length()==0) break;
            }

            if (!request.startsWith("GET ") || !(request.endsWith(" HTTP/1.0") || request.endsWith(" HTTP/1.1")))
            {
                pout.print("HTTP/1.0 400 Bad Request"+newLine+newLine);
            }
            else
            {
                String response = "Hello, World"; //будет возвращать лист продуктов

                pout.print(
                        "HTTP/1.0 200 OK" + newLine +
                                "Content-Type: text/plain" + newLine +
                                "Date: " + new Date() + newLine +
                                "Content-length: " + response.length() + newLine + newLine +
                                response
                );
            }

            pout.close();
            } catch (Throwable tri){
                System.err.println("Error handling request: "+tri);
            }
        }
        } catch (Throwable tr)
        {
            System.err.println("Could not start server: "+tr);
        }
    }


}
