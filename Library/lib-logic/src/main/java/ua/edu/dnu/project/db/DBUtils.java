package ua.edu.dnu.project.db;

import com.google.gson.Gson;
import ua.edu.dnu.project.exception.ServiceException;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    private static void createIfNotExists(String fileName) throws FileNotFoundException {
        Path path = Paths.get(fileName);
        if(!Files.exists(path)){
            boolean isCounterList = fileName.equals(
                    getPropertiesFromResource("db.properties").getProperty("id-counters"));
            String initialData = isCounterList ? "[0,0,0]" : "[]";
            try {
                Files.createDirectories(path.getParent());
                Files.write(path, initialData.getBytes());
            } catch (IOException ex) {
                throw new FileNotFoundException("Неможливо відновити файл з даними: " + fileName);
            }
        }
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
        createIfNotExists(fileName);
        Scanner scanner = new Scanner(new File(fileName));
        StringBuilder str = new StringBuilder();
        while (scanner.hasNextLine()){
            str.append(scanner.nextLine()).append("\n");
        }
        return str.toString();
    }
}
