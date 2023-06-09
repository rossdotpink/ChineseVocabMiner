package dev.rosswilson.vocab.cedicttree;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class DictionaryFileLoaderTest {

    DictionaryFileLoader loader = new DictionaryFileLoader();

    @Test
    void lineToDictionaryEntry_givenDictionaryEntryString_returnsCorrectDictionaryEntry() {
        String line = "㲈 㲈 [tao2] /variant of 鞀|鼗[tao2]/";
        DictionaryEntry testEntry = new DictionaryEntry(
                List.of('㲈'),
                List.of('㲈'),
                "tao2",
                List.of("variant of 鞀|鼗[tao2]")
        );

        assertEquals(loader.lineToDictionaryEntry(line), testEntry);
    }
}