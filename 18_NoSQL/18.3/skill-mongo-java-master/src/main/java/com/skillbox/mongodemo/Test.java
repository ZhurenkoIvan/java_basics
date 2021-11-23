package com.skillbox.mongodemo;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;
import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoCommandException;
import com.mongodb.client.*;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import org.bson.Document;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import com.mongodb.MongoClient;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        //Парсинг csv файла
        try {
            students = ParseStudentCsv("mongo.csv");
            students.forEach(student -> {
                System.out.println(student.getName());
                System.out.println(student.getAge());
                System.out.println(student.getCourses());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        MongoClient mongoClient = new MongoClient( "127.0.0.1" , 27017 );

        MongoDatabase database = mongoClient.getDatabase("local");

        // Создаем коллекцию
        MongoCollection<Document> studentsMongo = database.getCollection("Students");

        // Удалим из нее все документы
        studentsMongo.drop();

        // Создадим первый документ
        students.forEach(student -> {
            Document document = new Document();
            document.append("Name", student.getName());
            document.append("Age", student.getAge());
            document.append("Courses", student.getCourses());
            studentsMongo.insertOne(document);
        });

        System.out.println("Всего учеников: " + studentsMongo.countDocuments());

        List<Document> older40 = new ArrayList<>();

        studentsMongo.find(gt("Age", 40)).forEach((Consumer<Document>) older40::add);
        System.out.println("Учеников старше 40 лет: " + older40.size());
        studentsMongo.find().sort(new BasicDBObject("Age", 1)).limit(1).forEach((Consumer<Document>) document -> {
            System.out.println("Самый молодой студент: " + document.get("Name"));
        });
        studentsMongo.find().sort(new BasicDBObject("Age", -1)).limit(1).forEach((Consumer<Document>) document -> {
                    System.out.println("Курсы самого возрастного студента: " + document.get("Courses"));
        });
        String index = studentsMongo.createIndex(Indexes.ascending("Name"));
        Bson filter = eq("Name", "Opal Squires");
        Bson sort = Sorts.ascending("Name");
        Bson projection = fields(include("Name"), excludeId());
        FindIterable<Document> cursor = studentsMongo.find(filter).sort(sort).projection(projection);
        cursor.forEach((Consumer<? super Document>) student -> System.out.println(student.get("Name")));

    }


    private static List<Student> ParseStudentCsv(String filePath) throws IOException {
        //Загружаем строки из файла
        List<Student> students = new ArrayList<Student>();
        List<String> fileLines = Files.readAllLines(Paths.get(filePath));
        for (String fileLine : fileLines) {
            Student student = new Student();
            String[] splitedText = fileLine.split(",");
            ArrayList<String> columnList = new ArrayList<String>();
            for (int i = 0; i < splitedText.length; i++) {
                if (splitedText[i].contains("\"")) {
                    student.setCourses(ContainsKavichki(splitedText, i));
                    break;
                } else {
                    columnList.add(splitedText[i]);
                }
            }
            student.setName(columnList.get(0));
            student.setAge(Integer.parseInt(columnList.get(1)));
            students.add(student);
        }
        return students;
    }
    private static List<String> ContainsKavichki(String[] splitedText, int i) {
        List<String> courses = new ArrayList<>();
        if (splitedText[i].contains("\"")) {
            courses.add(splitedText[i].substring(1));
            i++;
            while (!splitedText[i].contains("\"")) {
                courses.add(splitedText[i]);
                i++;
            }
            courses.add(splitedText[i].substring(0, splitedText[i].length() - 1));
        }
        //Если в тексте одна кавычка и текст на нее заканчивается значит это часть предыдущей колонки
        return courses;
    }
}
