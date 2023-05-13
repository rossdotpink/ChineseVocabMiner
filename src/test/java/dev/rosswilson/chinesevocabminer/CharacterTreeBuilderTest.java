package dev.rosswilson.chinesevocabminer;

import org.junit.jupiter.api.*;

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
    }

    void recursivelyPrint(CharacterTree.Node<Character, DictionaryEntry> rootNode, int indentation) throws InterruptedException {
        Thread.sleep(50);
        for (int i = 1; i < indentation; i++) {
            System.out.print('-');
        }
        indentation++;
//        System.out.println(rootNode.getId() + rootNode.getData().getTraditional().toString());
        System.out.print(rootNode.getId());

        if (rootNode.getChildren().isEmpty()) {
            System.out.println(" : " + rootNode.getData().getDefinition().get(0));
            return;
        }
        System.out.println();

        for (CharacterTree.Node<Character, DictionaryEntry> childNode : rootNode.getChildren()) {

            recursivelyPrint(childNode, indentation);
        }




    }

    @Test
    void addEntry() {
    }
}