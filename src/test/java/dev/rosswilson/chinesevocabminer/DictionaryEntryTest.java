/*
 * Copyright (c) 2023. Ross Wilson.
 * All rights reserved.
 */

package dev.rosswilson.chinesevocabminer;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryEntryTest {
    DictionaryEntry a = new DictionaryEntry(
            List.of('㲈'),
            List.of('㲈'),
            "tao2",
            List.of("variant of 鞀|鼗[tao2]")
    );
    DictionaryEntry b = new DictionaryEntry(
            List.of('㲈'),
            List.of('㲈'),
            "tao2",
            List.of("variant of 鞀|鼗[tao2]")
    );
    DictionaryEntry c = new DictionaryEntry(
            List.of('㲈'),
            List.of('㲈'),
            "tao2",
            List.of("variant of 鞀|鼗[tao2]")
    );

    @Test
    void equals_givenSameObjects_returnsTrue() {
        assertEquals(a, b);
    }

    @Test
    void equals_givenDifferentObjects_returnsTrue() {
        assertEquals(b, c);
    }
}