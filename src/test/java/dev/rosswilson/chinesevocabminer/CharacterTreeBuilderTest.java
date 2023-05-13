package dev.rosswilson.chinesevocabminer;

import org.junit.jupiter.api.*;

import java.net.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTreeBuilderTest {

    CharacterTreeBuilder builder;
    CharacterTree<Character, DictionaryEntry> tree;

    @Test
    void buildCharacterTree() throws Exception {
        builder = new CharacterTreeBuilder();

        DictionaryFileLoader loader = new DictionaryFileLoader();
        tree = builder.buildCharacterTree(loader.getDictionaryEntryStream(
                "/Users/rosswilson/IdeaProjects/ChineseVocabMiner/src/main/resources/cedict_ts.u8"));
        recursivelyPrint(tree.getRoot(), 0);
//        for (CharacterTree.Node<Character, DictionaryEntry> node : tree.getRoot().getChildren()) {
//            String def = Objects.isNull(node.getData()) ? "noDef" : node.getData().getTraditional().toString();
//            System.out.println(node.getId() + " : " + def);
//            if (!node.getChildren().isEmpty()) {
//                for (CharacterTree.Node<Character, DictionaryEntry> innerNode : tree.getRoot().getChildren()) {
//                    String innerDef = Objects.isNull(innerNode.getData()) ? "noDef" : innerNode.getData().getTraditional().toString();
//                    System.out.println("- " + innerNode.getId() + " : " + innerDef);
//                    if (!innerNode.getChildren().isEmpty()) {
//
//                        for (CharacterTree.Node<Character, DictionaryEntry> innerInnerNode : tree.getRoot().getChildren()) {
//                            String innerInnerDef = Objects.isNull(innerInnerNode.getData()) ? "noDef" : innerInnerNode.getData().getTraditional().toString();
//                            System.out.println("-- " + innerInnerNode.getId() + " : " + innerInnerDef);
//                            Thread.sleep(200);
//                        }
//                    }
//                }
//            }
//        }
    }

    void recursivelyPrint(CharacterTree.Node<Character, DictionaryEntry> rootNode, int indentation) throws InterruptedException {
        Thread.sleep(200);
        for (int i = 1; i < indentation; i++) {
            System.out.print('-');
        }
//        System.out.println(rootNode.getId() + rootNode.getData().getTraditional().toString());
        System.out.println(rootNode.getId());

        int loopIndentation = indentation++;
        for (CharacterTree.Node<Character, DictionaryEntry> childNode : rootNode.getChildren()) {

            recursivelyPrint(childNode, loopIndentation++);
        }


    }

    @Test
    void addEntry() {
    }
}