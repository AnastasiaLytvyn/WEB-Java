package models;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class GetResult {

    public String readFiles(File file, ExecutorService executorService) throws Exception {

        String filename = file.getAbsolutePath();

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            List<Future<String>> allTasks = new ArrayList<>();
            for (var tempFile : files) {
                Callable<String> recursion = () -> readFiles(tempFile, executorService);
                Future<String> submit = executorService.submit(recursion);
                allTasks.add(submit);
            }
            String data = "";
            for (var task : allTasks) {
                data += task.get() + "/n";
            }
            return data;
        }

        if (file.getName().endsWith(".txt")) {
            String result = FindSum.getSum(file.getAbsolutePath());
            FileWriter printWriter = new FileWriter("C:/Study/5sem/Java/labs/result.txt", true);
            printWriter.write("Sum = " + result + ' ' + "Path: \"" + file.getAbsolutePath() + "\"\n");
            printWriter.close();
            return filename;
        }
        return "";
    }

}
