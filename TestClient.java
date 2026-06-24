import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TestClient {

    public static void main(String[] args) {

        try {

            Socket socket =
                    new Socket("localhost", 5000);

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()));

            PrintWriter writer =
                    new PrintWriter(
                            socket.getOutputStream(),
                            true);

            Scanner scanner =
                    new Scanner(System.in);

            System.out.println(reader.readLine());

            String username =
                    scanner.nextLine();

            writer.println(username);

            Thread receiveThread =
                    new Thread(() -> {

                        try {

                            String message;

                            while ((message =
                                    reader.readLine()) != null) {

                                System.out.println(message);
                            }

                        } catch (Exception ignored) {
                        }
                    });

            receiveThread.start();

            while (true) {

                String message =
                        scanner.nextLine();

                writer.println(message);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}