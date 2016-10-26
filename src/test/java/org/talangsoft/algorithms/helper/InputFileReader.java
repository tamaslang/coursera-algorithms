package org.talangsoft.algorithms.helper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InputFileReader {
    public static int[] readIntElements(String sourceFile) throws Exception {
        // reading lines
        ClassLoader classLoader = InputFileReader.class.getClassLoader();
        java.net.URL url = classLoader.getResource(sourceFile);
        java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
        return java.nio.file.Files.readAllLines(resPath).stream().mapToInt(Integer::valueOf).toArray();
    }


    private static final String TAB = "\t";

    private static Integer[] strArrToIntArrWithoutHead(String[] strArr) {
        return Arrays.asList(Arrays.copyOfRange(strArr, 1, strArr.length)).stream().map(Integer::valueOf).toArray(Integer[]::new);
    }

    public static Map<Integer, Integer[]> readGraphElements(String sourceFile) throws Exception {
        // reading lines
        ClassLoader classLoader = InputFileReader.class.getClassLoader();
        java.net.URL url = classLoader.getResource(sourceFile);
        java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
        List<String> lines = java.nio.file.Files.readAllLines(resPath);
        return lines.stream().map(line -> line.split(TAB)).collect(Collectors.toMap(lineValues -> new Integer(lineValues[0]), lineValues -> strArrToIntArrWithoutHead(lineValues)));
    }
}
