package dev.rosswilson.chinesevocabminer;

import lombok.*;

import java.util.*;

@Getter @AllArgsConstructor
public class DictionaryEntry {

    @NonNull
    private List<Character> traditional, simplified;
    private String pinyin;

    @NonNull
    private List<String> definition;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DictionaryEntry that = (DictionaryEntry) o;

        if (!traditional.equals(that.traditional)) return false;
        if (!simplified.equals(that.simplified)) return false;
        if (!pinyin.equals(that.pinyin)) return false;
        return definition.equals(that.definition);
    }

    @Override
    public int hashCode() {
        int result = traditional.hashCode();
        result = 31 * result + simplified.hashCode();
        result = 31 * result + pinyin.hashCode();
        result = 31 * result + definition.hashCode();
        return result;
    }
}
