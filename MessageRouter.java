public class MessageRouter {

    public static void sendMessage(
            String sender,
            String receiver,
            String message) {

        ChatHistoryManager.saveMessage(
                sender,
                receiver,
                message);

        ClientHandler receiverHandler =
                UserManager.getUser(receiver);

        if (receiverHandler != null) {

            receiverHandler.sendMessage(
                    sender + ": " + message);

        } else {

            System.out.println(
                    "User not found: " + receiver);
        }
    }
    public static void sendGroupMessage(
        String sender,
        String groupName,
        String message) {

            ChatHistoryManager.saveGroupMessage(
            groupName,
            sender,
            message);

    var members =
            GroupManager.getMembers(
                    groupName);

    if (members == null) {
        return;
    }

    for (String member : members) {

        if (!member.equals(sender)) {

            ClientHandler handler =
                    UserManager.getUser(member);

            if (handler != null) {

                handler.sendMessage(
                        "[GROUP:" +
                        groupName +
                        "] " +
                        sender +
                        ": " +
                        message);
            }
        }
    }
}
}