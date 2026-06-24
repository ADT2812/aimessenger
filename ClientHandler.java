import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private String username;
    private PrintWriter writer;

    public ClientHandler(Socket socket) {

        this.clientSocket = socket;
    }

    public void sendMessage(String message) {

        writer.println(message);
    }

    @Override
    public void run() {

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    clientSocket.getInputStream()));

            writer =
                    new PrintWriter(
                            clientSocket.getOutputStream(),
                            true);

            writer.println("Enter Username:");

            username = reader.readLine();

            UserManager.addUser(username, this);

            writer.println(
                "Connected! Use format: username:message");

            String input;

            while ((input = reader.readLine()) != null) {

    if (input.startsWith("/history ")) {

        String otherUser =
                input.substring(9);

        sendMessage(
                ChatHistoryManager.getConversation(
                        username,
                        otherUser));

        continue;
    }

    if (input.startsWith("/create ")) {

        String groupName =
                input.substring(8);

        GroupManager.createGroup(
                groupName);

        sendMessage(
                "Group created: " +
                groupName);

        continue;
    }

    if (input.startsWith("/join ")) {

        String groupName =
                input.substring(6);

        GroupManager.joinGroup(
                groupName,
                username);

        sendMessage(
                "Joined group: " +
                groupName);

        continue;
    }

    if (input.startsWith("/group ")) {

        String[] parts =
                input.split(" ", 3);

        if (parts.length == 3) {

            MessageRouter.sendGroupMessage(
                    username,
                    parts[1],
                    parts[2]);
        }

        continue;
    }

    if (input.contains(":")) {

        String[] parts =
                input.split(":", 2);

        String receiver =
                parts[0];

        String message =
                parts[1];

        MessageRouter.sendMessage(
                username,
                receiver,
                message);
    }
}

        } catch (Exception e) {

    System.out.println(
            username + " disconnected.");

} finally {

    try {

        UserManager.removeUser(username);

        clientSocket.close();

    } catch (Exception ignored) {
    }
}
    }
}
