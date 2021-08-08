package org;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Helper.lessons = initLessons();

        Parent root = FXMLLoader.load(getClass().getResource("/main_scene.fxml"));
        primaryStage.setTitle("Learning Java");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    private Lesson[] initLessons() throws FileNotFoundException {
        File dir = new File("src/main/resources/lessons");
        File[] files = dir.listFiles();

        Lesson[] lessonArr = new Lesson[files.length];

        for (int i = 0; i < lessonArr.length; i++) {

            FileReader in = new FileReader(new File("src/main/resources/lessons/" + files[i].getName()));

            String text = in.readAll();
            String cmd = generateCMD(files[i].getName().replaceAll("[.][^.]+$", ""));

            lessonArr[i] = new Lesson(cmd, text);
        }

        return lessonArr;
    }

    private String generateCMD(String name) {
        StringBuilder cmd = new StringBuilder("java Main < src/main/resources/tests/");

        for (int i = 5; i < name.length(); i++) {
            cmd.append(name.charAt(i));
        }

        cmd.append("/tests/");

        return cmd.toString();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
