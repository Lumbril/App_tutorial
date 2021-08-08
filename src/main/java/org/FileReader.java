package org;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    private Scanner in;

    FileReader(File file) throws FileNotFoundException {
        in = new Scanner(file);
    }

    public String readAll() {
        StringBuilder data = new StringBuilder();

        do {
            String str = in.nextLine();

            data.append(str);
            data.append('\n');
        } while (in.hasNext());

        in.close();

        return data.toString();
    }
}
