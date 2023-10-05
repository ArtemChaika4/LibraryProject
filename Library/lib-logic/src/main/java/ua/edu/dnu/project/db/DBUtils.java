package ua.edu.dnu.project.db;

import com.google.gson.Gson;
import ua.edu.dnu.project.exception.ServiceException;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class DBUtils {
    private static final Gson gson = new Gson();

    //throws ServiceException
    public static <T> T readJson(String fileName, Type type) throws ServiceException, FileNotFoundException {
        String data = getFileString(fileName);
        T list = gson.fromJson(data, type);
        if(list == null){
            throw new ServiceException("Не вдалося завантажити дані. Файл пошкоджено: " + fileName);
        }
        return list;
    }

    //throws FileNotFoundException
    public static void writeJson(String fileName, Object obj) throws FileNotFoundException {
        try (PrintWriter printWriter = new PrintWriter(fileName)){
            printWriter.print(gson.toJson(obj));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Не вдалося зберегти дані. Файл не знайдено " + fileName);
        }
    }

    //throws FileNotFoundException
    public static InputStream getFileFromResourceAsStream(String fileName) throws FileNotFoundException {
        InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new FileNotFoundException("Файл не знайдено " + fileName);
        }
        return inputStream;
    }

    //throws FileNotFoundException
    public static Properties getPropertiesFromResource(String fileName) throws FileNotFoundException {
        Properties props = new Properties();
        try {
            props.load(getFileFromResourceAsStream("db/db.properties"));
        } catch (IOException e) {
            throw new FileNotFoundException("Не вдалося завантажити файл з ресурсами. Файл не знайдено "
                    + fileName);
        }
        return props;
    }

    //throws FileNotFoundException
    public static String getFileString(String fileName) throws FileNotFoundException {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Не вдалося зчитати дані. Файл не знайдено " + fileName);
        }
        StringBuilder str = new StringBuilder();
        while (scanner.hasNextLine()){
            str.append(scanner.nextLine()).append("\n");
        }
        return str.toString();
    }
}
