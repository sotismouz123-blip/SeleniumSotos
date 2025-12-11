package com.Ironfx.uat.UatProjectIronFx.utilities;

import java.util.List;
import java.util.stream.Collectors;

public class CodePoints {

    public static String toCodePoints(String text) {
        if (text == null) return "";
        return text.codePoints()
                   .mapToObj(cp -> String.format("U+%04X", cp))
                   .collect(Collectors.joining(" "));
    }

    public static boolean containsByCodePoints(List<String> actualOptions, String expected) {
        String expectedCp = toCodePoints(expected);
        return actualOptions.stream()
                .map(CodePoints::toCodePoints)
                .anyMatch(cp -> cp.equals(expectedCp));
    }

    public static List<String> missingByCodePoints(List<String> actualOptions, List<String> expectedLangs) {
        return expectedLangs.stream()
                .filter(expected -> !containsByCodePoints(actualOptions, expected))
                .collect(Collectors.toList());
    }
}