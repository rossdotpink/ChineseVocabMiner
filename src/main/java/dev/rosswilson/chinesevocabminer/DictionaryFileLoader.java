/*
 * Copyright (c) 2023. Ross Wilson.
 * All rights reserved.
 */

package dev.rosswilson.chinesevocabminer;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

import static dev.rosswilson.chinesevocabminer.GlobalConstants.*;

public class DictionaryFileLoader {

    public List<DictionaryEntry> getDictionaryEntryList(String resourcePath) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(resourcePath);

        try (
                InputStreamReader streamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(streamReader)) {
            return getDictionaryEntryList(bufferedReader);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<DictionaryEntry> getDictionaryEntryList() throws Exception {
        try {
            return getDictionaryEntryList(DICTIONARY_RESOURCE_PATH);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<DictionaryEntry> getDictionaryEntryList(BufferedReader bufferedReader) {
        Stream<String> stream = bufferedReader.lines();
        return stream
                .filter(s -> !s.startsWith("#"))
                .map(this::lineToDictionaryEntry)
                .collect(Collectors.toList());
    }

     public DictionaryEntry lineToDictionaryEntry(String line) {
        String[] splitLine = line.split("/");
        String charactersAndPinyin = splitLine[0].trim().substring(0, splitLine[0].length()-1);
        List<String> definitions = Arrays.stream(splitLine).skip(1).toList();
        String splitCharactersAndPinyin = charactersAndPinyin.split("]")[0];
        String[] charsAndPinyinSplitBySpace = splitCharactersAndPinyin.split(" ");
        List<Character> traditional = new ArrayList<>();
        List<Character> simplified = new ArrayList<>();
        for (Character c : charsAndPinyinSplitBySpace[0].toCharArray()) traditional.add(c);
        for (Character c : charsAndPinyinSplitBySpace[1].toCharArray()) simplified.add(c);

        String pinyin = charsAndPinyinSplitBySpace[2].substring(1);

        DictionaryEntry entry = new DictionaryEntry(
                traditional,
                simplified,
                pinyin,
                definitions
        );

        return entry;
    }
}
