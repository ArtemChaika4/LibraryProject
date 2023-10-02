package ua.edu.dnu.project.db;

import com.google.gson.Gson;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class DBUtils {
    private static final Gson gson = new Gson();

    public static <E> void readJson(String fileName, List<E> list, Type type){
        String data = getFileString(fileName);
        List<E> tmp = gson.fromJson(data, type);
        if(tmp != null){
            list.addAll(tmp);
        }
    }

    public static void writeJson(String fileName, Object obj){
        try (PrintWriter printWriter = new PrintWriter(fileName)){
            printWriter.print(gson.toJson(obj));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static InputStream getFileFromResourceAsStream(String fileName) {
        InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }

    public static Properties getPropertiesFromResource(String fileName){
        Properties props = new Properties();
        try {
            props.load(DBUtils.getFileFromResourceAsStream("db/db.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException("file not found! " + fileName);
        }
        return props;
    }

    public static String getFileString(String fileName) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StringBuilder str = new StringBuilder();
        while (scanner.hasNextLine()){
            str.append(scanner.nextLine()).append("\n");
        }
        return str.toString();
    }
}
