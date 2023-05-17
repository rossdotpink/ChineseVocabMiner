This is a personal project to fill a need I have when it comes to learning Chinese.

Sometimes I want to learn all the vocabulary necessary to read a particular body of text, let's say a web page. There
are some tools already which do this, but they are not flexible enough for my needs. Generally I will want to take the
list and upload it to some sort of SRS.

## DictionaryTree

This module returns a dictionary in the form of a tree structure. Currently it only returns CC-CEDict, for use with
Chinese.

## VocabMiner

This module parses a body of text and identifies as many vocabulary items as possible, using a dictionary tree structure
provided by `DictionaryTree`.

A window is defined of a fixed number of characters, determined by the longest entry in the `DictionaryTree`, pointing
to the first `n` characters of the text.

Characters within the window are looked up in the `DictionaryTree` in increasing length until either a match is found or
no match is found: if a match is found, the vocabulary item is added to the list to be returned and the window is moved
to start at the first character beyond its bounds i.e. to the next `n` characters; if no match is found,
the window is moved over by 1 character and the process is repeated.