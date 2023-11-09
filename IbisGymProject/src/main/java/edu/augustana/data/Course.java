package edu.augustana.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Course {

    public static Course currentCourse = new Course();

    //TODO: eventually make a list of lesson plans
    private LessonPlan oneLessonPlan = new LessonPlan("Untitled");

    public LessonPlan getOneLessonPlan() {
        return oneLessonPlan;
    }

    public static Course loadFromFile(File logFile) throws IOException {
        FileReader reader = new FileReader(logFile);
        Gson gson = new Gson();
        return gson.fromJson(reader,Course.class);
    }

    public void saveToFile(File logFile) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String serializedCourseText = gson.toJson(this);
        PrintWriter writer = new PrintWriter(new FileWriter(logFile));
        writer.println(serializedCourseText);
        writer.close();
    }
}
