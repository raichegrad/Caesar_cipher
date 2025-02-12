import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice = getChoice(scanner);
        if (choice == -1) {
            System.out.println("Программа завершена.");
            return;
        }

        if (choice == 1) {
            System.out.println("Введите текст для шифрования:");
            String text = getText(scanner);
            if (text == null) return;

            System.out.println("Введите ключ:");
            int key = getKey(scanner);

            String encryptedText = caesarCipher(text, key);
            System.out.println("Зашифрованный текст: " + encryptedText);
            saveToFile(encryptedText, "Зашифрованный текст.txt");

        } else if (choice == 2) {
            System.out.println("Введите текст для расшифровки:");
            String text = getText(scanner);
            if (text == null) return;

            System.out.println("Введите ключ:");
            int key = getKey(scanner);

            String decryptedText = caesarCipher(text, -key);
            System.out.println("Расшифрованный текст: " + decryptedText);
            saveToFile(decryptedText, "Расшифрованный текст.txt");

        } else {
            System.out.println("Неверный выбор.");
        }
    }

    public static int getChoice(Scanner scanner) {
        System.out.println("Выберите действие:");
        System.out.println("1. Зашифровать текст");
        System.out.println("2. Расшифровать текст");

        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1 || choice == 2) {
                return choice;
            } else {
                System.out.println("Ошибка: введено неверное число. Попробуйте снова.");
            }
        } else {
            System.out.println("Ошибка: необходимо ввести число.");
            scanner.nextLine();
        }
        return -1;
    }

    public static String getText(Scanner scanner) {
        String text = scanner.nextLine();
        if (text.trim().isEmpty()) {
            System.out.println("Ошибка: текст не может быть пустым.");
            return null;
        }
        return text;
    }

    public static int getKey(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int key = scanner.nextInt();
                scanner.nextLine();
                return key;
            } else {
                System.out.println("Ошибка: ключ должен быть числом.");
                scanner.nextLine();
            }
        }
    }

    public static String caesarCipher(String text, int key) {
        String alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int index = alphabet.indexOf(Character.toUpperCase(c));

            if (index != -1) {
                int newIndex = (index + key) % alphabet.length();
                if (newIndex < 0) {
                    newIndex += alphabet.length();
                }
                char newChar = alphabet.charAt(newIndex);
                if (Character.isLowerCase(c)) {
                    newChar = Character.toLowerCase(newChar);
                }
                result.append(newChar);
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    public static void saveToFile(String text, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(text);
            writer.close();
            System.out.println("Текст сохранён в файл " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
