import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные (Фамилия Имя Отчество дата рождения номер телефона пол):");
        String input = scanner.nextLine();

        String[] data = input.split(" ");
        if (data.length != 6) {
            System.out.println("Ошибка: неверное количество данных");
            return;
        }

        String surname = data[0];
        String name = data[1];
        String patronymic = data[2];
        String dateOfBirth = data[3];
        String phoneNumber = data[4];
        String gender = data[5];

        try {
            validateData(surname, name, patronymic, dateOfBirth, phoneNumber, gender);
            saveToFile(surname, name, patronymic, dateOfBirth, phoneNumber, gender);
            System.out.println("Данные успешно сохранены в файл");
        } catch (IllegalArgumentException | IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void validateData(String surname, String name, String patronymic, String dateOfBirth,
                                     String phoneNumber, String gender) {
        if (!isValidDate(dateOfBirth)) {
            throw new IllegalArgumentException("Неверный формат даты рождения");
        }

        if (!isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Неверный формат номера телефона");
        }

        if (!isValidGender(gender)) {
            throw new IllegalArgumentException("Неверный формат пола");
        }
    }

    private static boolean isValidDate(String date) {
        return date.matches("\\d{2}\\.\\d{2}\\.\\d{4}");
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d+");
    }

    private static boolean isValidGender(String gender) {
        return gender.equals("f") || gender.equals("m");
    }

    private static void saveToFile(String surname, String name, String patronymic, String dateOfBirth,
                                   String phoneNumber, String gender) throws IOException {
        FileWriter fileWriter = new FileWriter(surname + ".txt", true);
        fileWriter.write(surname + name + patronymic + dateOfBirth + " " + phoneNumber + gender + "\n");
        fileWriter.close();
    }
}