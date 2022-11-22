package controllers;

import models.GetResult;
import views.InputDirectory;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainController {

    private final InputDirectory inputDirectory;
    public MainController() {
        this.inputDirectory = new InputDirectory();
    }

    public void run() throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();
        String directory;
        GetResult getResult;

        while (true) {
            directory = inputDirectory.getDirectory();
            if (inputDirectory.validator(directory)) {
                break;
            } else {
                System.out.println("Try \"C://Study//5sem//Java//Test folder\"");
            }
        }

        File dir = new File(directory);
        getResult = new GetResult();

        String resultFilePath = "C:/Study/5sem/Java/labs/result.txt";
        FileWriter resultFile = new FileWriter(resultFilePath, false);
        resultFile.write("");
        resultFile.close();

        getResult.readFiles(dir, executorService);
        executorService.shutdown();

        String data = new String(Files.readAllBytes(Paths.get(resultFilePath)));
        System.out.println(data);

    }
}
