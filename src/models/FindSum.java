package models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindSum {

    private static Double countSum(String fileData) {
        Double sum = 0.0;
        String data = fileData + ' ';
        Pattern pattern = Pattern.compile("\\-?\\d*\\.\\d*");
        Matcher matcher = pattern.matcher(data);
        matcher.matches();
        while (matcher.find()) {
            sum += Double.parseDouble(matcher.group(0));
        }
        return sum;
    }

    public static String getSum(String file) throws IOException {
        String data = new String(Files.readAllBytes(Paths.get(file)));
        return String.valueOf(countSum(data));
    }
}
