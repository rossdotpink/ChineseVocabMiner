package dev.rosswilson.chinesevocabminer;

import java.util.*;
import java.util.stream.*;

public class CharacterTreeBuilder {

    CharacterTree<Character, DictionaryEntry> tree;

    public CharacterTreeBuilder() {}

    public CharacterTree<Character, DictionaryEntry> buildCharacterTree(List<DictionaryEntry> list) {
        tree = new CharacterTree<Character, DictionaryEntry>('r');
        tree.getRoot().setData(new DictionaryEntry(List.of('r'), List.of('r'), "Root", List.of("Root node")));
        list.stream().forEach(s -> {
//            addEntry(tree.getRoot(), s.getSimplified().iterator(), s);
            addEntry(tree.getRoot(), s.getTraditional().iterator(), s);
        });
        return tree;
    }

    public void addEntry(CharacterTree.Node<Character, DictionaryEntry> node,
                         Iterator<Character> characterIterator,
                         DictionaryEntry entry) {
        // Advance the character
        Character character = characterIterator.next();
//        CharacterTree.Node<Character, DictionaryEntry> nextNode;

        // If current character exists and we have more characters
        if (!Objects.isNull(node.getChild(character)) && characterIterator.hasNext()) {
            addEntry(node.getChild(character), characterIterator, entry);
        }

        // If current character exists and we have no more characters
//        if (!Objects.isNull(node.getChild(character)) && !characterIterator.hasNext()) {
//            addEntry(node.getChild(character), characterIterator, entry);
//        }

        // If current character does not exist, and we have more characters
        if (Objects.isNull(node.getChild(character)) && characterIterator.hasNext()) {
            CharacterTree.Node<Character, DictionaryEntry> newNode = new CharacterTree.Node<>();
            newNode.setId(character);
            node.addChild(newNode);
            addEntry(node.getChild(character), characterIterator, entry);
//            System.out.println("Adding '" + character + "' as child of '" + node.getId() +"'");
//            try {
//                Thread.sleep(100);
//            } catch (Exception e) {
//
//            }
        }

        // If current character does not exist, and we don't have more characters
        if (Objects.isNull(node.getChild(character)) && !characterIterator.hasNext()) {
            CharacterTree.Node<Character, DictionaryEntry> newNode = new CharacterTree.Node<>();
            newNode.setId(character);
            newNode.setData(entry);
            node.addChild(newNode);
            return;
        }

    }
}
