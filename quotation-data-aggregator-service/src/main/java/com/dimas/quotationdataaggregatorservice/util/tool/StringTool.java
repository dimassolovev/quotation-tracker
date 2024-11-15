package com.dimas.quotationdataaggregatorservice.util.tool;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.stream.Collectors;

@Component
public class StringTool {

    public String filterEmptyLines(String csvData) {
        return new BufferedReader(new StringReader(csvData)).lines()
                .filter(line -> !line.trim().isEmpty())
                .collect(Collectors.joining("\n"));
    }

    public String removeUnnecessarySectionsBeforeSection(String csvData, String section) {
        var index = csvData.indexOf(section);
        return index != -1 ? csvData.substring(index) : csvData;
    }

    public String removeSection(String csvData, String section) {
        return csvData.replaceFirst(section + "\\s*\n", "");
    }

    public String removeUnnecessarySectionsAfterSection(String csvData, String section) {
        var index = csvData.indexOf(section);
        return index != -1 ? csvData.substring(0, index) : csvData;
    }
}
