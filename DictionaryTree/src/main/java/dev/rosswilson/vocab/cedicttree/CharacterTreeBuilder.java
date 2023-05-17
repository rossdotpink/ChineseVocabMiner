package dev.rosswilson.vocab.cedicttree;

import java.util.*;

public class CharacterTreeBuilder {

    CharacterTree<Character, DictionaryEntry> tree;

    public CharacterTreeBuilder() {}

    public CharacterTree<Character, DictionaryEntry> buildCharacterTree(List<DictionaryEntry> list) {
        tree = new CharacterTree<>('r');
        tree.getRoot().setData(new DictionaryEntry(List.of('r'), List.of('r'), "Root", List.of("Root node")));
        list.stream().forEach(s -> {
            addEntry(tree.getRoot(), s.getSimplified().iterator(), s);
            addEntry(tree.getRoot(), s.getTraditional().iterator(), s);
        });
        return tree;
    }

    public void addEntry(CharacterTree.Node<Character, DictionaryEntry> node,
                         Iterator<Character> characterIterator,
                         DictionaryEntry entry) {
        // Advance the character
        Character character = characterIterator.next();

        // If current character exists and we have more characters
        if (!Objects.isNull(node.getChild(character)) && characterIterator.hasNext()) {
            addEntry(node.getChild(character), characterIterator, entry);
        }

        // If current character exists and we have no more characters
        // ...

        // If current character does not exist, and we have more characters
        if (Objects.isNull(node.getChild(character)) && characterIterator.hasNext()) {
            CharacterTree.Node<Character, DictionaryEntry> newNode = new CharacterTree.Node<>();
            newNode.setId(character);
            node.addChild(newNode);
            addEntry(node.getChild(character), characterIterator, entry);
        }

        // If current character does not exist, and we don't have more characters
        if (Objects.isNull(node.getChild(character)) && !characterIterator.hasNext()) {
            CharacterTree.Node<Character, DictionaryEntry> newNode = new CharacterTree.Node<>();
            newNode.setId(character);
            newNode.setData(entry);
            node.addChild(newNode);
        }

    }
}
