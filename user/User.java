package user;

import checker.NumericChecker;
import exceptions.MyInputNameException;
import inputService.InputData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class User implements InputData, NumericChecker {
    private String name;
    private String sureName;
    private String lastName;
    private String birthday;
    private String phone;
    private String sex;

    public String getName() {
        return name;
    }

    public String getSureName() {
        return sureName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPhone() {
        return phone;
    }

    public String getSex() {
        return sex;
    }

    public User(){
        this.name = inputName();
        this.sureName = inputSureName();
        this.lastName = inputLastName();
        this.birthday = inputBirthday();
        this.phone = inputPhone();
        this.sex = inputSex();
    }

    /**
     * Далее следует несколько методов, с помощью которых пользователь вводит
     * свои данные в конструктор.
     * Использование рекурсии в данных методах не совсем верное решение,
     * гораздо лучше и правильней будет применение бесконечного цикла while (true),
     * либо добавление условия выхода из рекурсии с назначением счетчика итераций.
     * Человек редко ошибается при написании личных данных, если не делает это намеренно,
     * поэтому использование рекурсии как мне кажется уместно в данном случае,
     * при условии использования этого приложения для персональных нужд.
     * @return (inputVar) - имя пользователя, которое вводится через консоль.
     * Если имя пользователя введено неверно, то будет выброшено исключение MyInputNameException,
     * с просьбой повторного ввода имени.
     */
    @Override
    public String inputName() {
        System.out.println("Введите имя:");
        String inputVar;
        try  {  //try-with-resources намеренно не применяется, так как закрытие потока происходит в последнем методе.
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            inputVar = buffer.readLine();   //Открываем поток ввода данных через консоль.
            if (inputVar == null | inputVar.equals("")) //Проверяем введенные данные на их отсутствие.
                throw new MyInputNameException("Поле c именем должно быть заполнено.");
            else if (isMumeric(inputVar)) {     //Проверяем данные на содержание числовых значений.
                throw new MyInputNameException("Поле с именем должно содержать только буквы.");
            }
            return inputVar;
        } catch (MyInputNameException e) {
            System.out.println(e);
            String newName = inputName();   //Рекурсия вызывается, если пользователь ввел неверные данные.
            return newName;                 //Возвращаем уже новую переменную с правильными данными.
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String inputSureName() {
        System.out.println("Введите фамилию:");
        String inputVar;
        try  {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            inputVar = buffer.readLine();
            if (inputVar == null | inputVar.equals(""))
                throw new MyInputNameException("Поле c фамилией должно быть заполнено.");
            else if (isMumeric(inputVar)) {
                throw new MyInputNameException("Поле с фамилией должно содержать только буквы.");
            }
            return inputVar;
        } catch (MyInputNameException e) {
            System.out.println(e);
            String newName = inputSureName();
            return newName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String inputLastName() {
        System.out.println("Введите отчество:");
        String inputVar;
        try  {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            inputVar = buffer.readLine();
            if (inputVar == null | inputVar.equals(""))
                throw new MyInputNameException("Поле c отчеством должно быть заполнено.");
            else if (isMumeric(inputVar)) {
                throw new MyInputNameException("Поле с отчеством должно содержать только буквы.");
            }
            return inputVar;
        } catch (MyInputNameException e) {
            System.out.println(e);
            String newName = inputLastName();
            return newName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String inputBirthday() {
        System.out.println("Введите дату рождения в формате dd.mm.yyyy:");
        String inputVar;
        try  {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            inputVar = buffer.readLine();
            if (!inputVar.matches("\\d{2}\\.\\d{2}\\.\\d{4}"))  //Проверка на формат даты.
                throw new MyInputNameException("Неверный формат даты.");
            return inputVar;
        } catch (MyInputNameException e) {
            System.out.println(e);
            String newName = inputBirthday();
            return newName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String inputPhone() {
        System.out.println("Введите телефон:");
        String inputVar;
        try  {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            inputVar = buffer.readLine();
            if (!inputVar.matches("\\d+"))  //Проверка на числовой формат данных.
                throw new MyInputNameException("Номер телефона должен быть из цифр.");
            return inputVar;
        } catch (MyInputNameException e) {
            System.out.println(e);
            String newName = inputPhone();
            return newName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String inputSex() {
        System.out.println("Введите пол:");
        String inputVar;
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            inputVar = buffer.readLine().toLowerCase(Locale.ROOT);
            if (!inputVar.matches("[fm]") && !inputVar.matches("[жм]")) //проверка на ввод конкретных символов.
                throw new MyInputNameException("Укажите мужской или женский пол одной буквой.(f,ж - женский и m,м - мужской)");
            return inputVar;
        } catch (MyInputNameException e) {
            System.out.println(e);
            String newName = inputSex();
            return newName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод проверки строки на наличие чисел в ней.
     * @param string    //Принимает строку, введенную пользователем.
     * @return  //Возвращает true, если строка содержит числовые символы.
     */
    @Override
    public boolean isMumeric(String string) {
        return string.chars().allMatch(Character::isDigit);
    }

    @Override
    public String toString() {
        return "user.User{" +
                "ФИО:'" + sureName + ' ' +
                name + ' ' +
                lastName + '\'' +
                ", ДР:'" + birthday + '\'' +
                ", Тел.:'" + phone + '\'' +
                ", Пол:'" + sex + '\'' +
                '}';
    }
}