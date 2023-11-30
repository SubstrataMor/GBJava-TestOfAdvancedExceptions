package user;

import exceptions.CloseCounterException;
import outputService.SaveDataFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StartProgram implements SaveDataFile {

    public void start() {
        User user = new User();
        System.out.println(user);
        saveData(user);
    }

    /**
     * Метод сохранения данных пользователя в файл. Имя файла будет содержать фамилию пользователя.
     * Метод принимает объект класса User, в котором содержатся введенные пользователем данные,
     * создает файл и сохраняет в него все данные одной строкой.
     * @param user
     */
    @Override
    public void saveData(User user) {
        String fileName = "src/data\\" + user.getSureName().toLowerCase() + ".txt"; //Создаем имя файла по фамилии пользователя.
        try {
            File file = new File(fileName);     //Создаем файл.
            if (file.createNewFile()) System.out.println("Файл создан");    //Если файл создался, сообщаем об этом.

            try (FileWriter fileWriter = new FileWriter(file, true)){   //Открываем поток записи данных в файл.
                if (file.length() > 0){         //Если файл не пустой,
                    fileWriter.write('\n');  //то продолжаем запись в него с новой строки.
                }
                fileWriter.write(String.format("%s %s %s %s %s %s", user.getSureName(), user.getName(),
                        user.getLastName(), user.getBirthday(), user.getPhone(), user.getSex()));
            } catch (CloseCounterException e) {
                System.out.println(e.getMessage());
            } catch (Exception e){

            }
        }
        catch (IOException e) {
            System.out.println("Ошибка при создании файла");
            e.printStackTrace();
        }
    }
}
