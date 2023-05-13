package dev.rosswilson.chinesevocabminer;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class DictionaryFileLoader {
    /*
     䫏 䫏 [qi1] /mask of a god used in ceremonies to exorcise demons and drive away pestilence/(archaic) ugly/
     */
    public List<DictionaryEntry> getDictionaryEntryStream(String filepath) throws Exception {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(filepath))) {
            return getDictionaryEntryStream(bufferedReader);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<DictionaryEntry> getDictionaryEntryStream() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("cedict_ts.u8");

        try (
                InputStreamReader streamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(streamReader)) {
            return getDictionaryEntryStream(bufferedReader);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<DictionaryEntry> getDictionaryEntryStream(BufferedReader bufferedReader) throws Exception {
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
