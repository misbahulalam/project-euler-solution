package project.euler.util;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * @author <a href="mailto:misbahulalam@gmail.com">Misbahul Alam Chowdhury</a> on Oct 30, 2017
 * @since 1.0
 */
public class ResourcesUtils {

    @SneakyThrows({IOException.class})
    public static String readAsString(String fileName) {
        File file = new File("src/main/resources/" + fileName);
        return FileUtils.readFileToString(file, Charset.defaultCharset());
    }

    public static String[] readAsCsvString(String fileName) {
        return readAsString(fileName).split(",");
    }

    public static String[][] readAs2DStringArray(String fileName, String delimiter) {
        return Arrays.stream(readAsString(fileName).split("\n"))
                .map(line -> line.split(delimiter))
                .toArray(String[][]::new);
    }

}
