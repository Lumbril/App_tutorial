package org;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Checker extends Thread{
    private int maxPoint;
    private int points;
    private String dir;
    private File [] testFiles;
    private Lesson lesson;

    public Checker(int maxPoint, String dir, File [] testFiles, Lesson lesson) {
        this.maxPoint = maxPoint;
        this.points = 0;
        this.dir = dir;
        this.testFiles = testFiles;
        this.lesson = lesson;
    }

    @Override
    public void run() {
        for (int i = 0; i < testFiles.length; i++) {
            String command = lesson.getInputCMD() + testFiles[i].getName() + " " + lesson.getOutputCMD();
            String urlAns = lesson.getUrlAns();

            try {
                check(dir, command, urlAns, i + 1);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void check(String dir, String command, String urlAns, int step) throws IOException, InterruptedException {
        PrintAns file = new PrintAns(command, dir);

        file.start();
        file.join();

        //Runtime.getRuntime().exec("cmd.exe /c" + command, null, new File(dir));

        Scanner in = new Scanner(new File("src/main/resources/user_answer/ans.txt"));

        StringBuilder userAnswer = new StringBuilder();

        while (in.hasNext()) {
            userAnswer.append(in.nextLine());
        }

        in.close();

        String url = urlAns + step + ".txt";
        in = new Scanner(new File(url));

        StringBuilder ans = new StringBuilder();

        while (in.hasNext()) {
            ans.append(in.nextLine());
        }

        in.close();

        points += ans.compareTo(userAnswer) == 0 ? 1 : 0;
    }

    public int getMaxPoint() {
        return maxPoint;
    }

    public int getPoints() {
        return points;
    }

    public String getRating() {
        return "Оценка: " + points + "/" + maxPoint;
    }
}
