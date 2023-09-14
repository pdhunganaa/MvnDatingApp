import java.util.*;

class User {
    private String username;
    private String password;
    private String name;
    private int age;
    private String interests;
    private List<User> matches = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();

    public User(String username, String password, String name, int age, String interests) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.interests = interests;
    }

    public void addMatch(User user) {
        matches.add(user);
    }

    public void sendMessage(User receiver, String messageText) {
        Message message = new Message(this, receiver, messageText);
        receiver.receiveMessage(message);
        messages.add(message);
    }

    public void receiveMessage(Message message) {
        messages.add(message);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public List<User> getMatches() {
        return matches;
    }

    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        return "Username: " + username + ", Name: " + name + ", Age: " + age + ", Interests: " + interests;
    }
}

class Message {
    private User sender;
    private User receiver;
    private String content;
    private Date timestamp;

    public Message(User sender, User receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = new Date();
    }

    @Override
    public String toString() {
        return sender.getName() + " to " + receiver.getName() + ": " + content + " (" + timestamp + ")";
    }
}

public class DatingApp {
    private static List<User> users = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Dating App!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Register");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter interests: ");
        String interests = scanner.nextLine();

        User newUser = new User(username, password, name, age, interests);
        users.add(newUser);
        System.out.println("Registration successful!");
    }

    private static void loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = findUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful!");
            showUserProfile(user);
        } else {
            System.out.println("Login failed. Please check your username and password.");
        }
    }

    private static User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private static void showUserProfile(User user) {
        System.out.println("\nUser Profile:");
        System.out.println(user);

        List<User> matches = user.getMatches();
        if (!matches.isEmpty()) {
            System.out.println("\nMatches:");
            for (User match : matches) {
                System.out.println(match);
            }
        }

        List<Message> messages = user.getMessages();
        if (!messages.isEmpty()) {
            System.out.println("\nMessages:");
            for (Message message : messages) {
                System.out.println(message);
            }
        }
    }
}
