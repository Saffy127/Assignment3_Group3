Project Name: Assignment3_Group3

Introduction:

The program accepts the user's input on command line and read information from the text file and generate a word object
of the binary tree, which is serialized into the repository.ser file. The program will be stored in a list for each word
object stored in the BST. The program will also produce output, specified by the user at command line, to generate reports
using a variety of iterators built into the BST

DATE: Dec 1, 2023

Author: Isaac, Jayden, David


Users can choose three ways to retrieve information:

1. -pf to print in alphabetic order all words along with the corresponding list of files in which
the words occur.
2. -pl to print in alphabetic order all words along with the corresponding list of files and
numbers of the lines in which the word occur.
3. -po to print in alphabetic order all words along with the corresponding list of files,
numbers of the lines in which the word occur and the frequency of occurrence of the
words.

How to run this program:

Download the zip locally or clone our repo from github: git clone https://github.com/Saffy127/Assignment3_Group3.git

Open command line on the WordTracker.jar folder.
It needs three parameters: the input file path, the processing type, and the output file path
Example:
java -jar WordTracker.jar "input_file_path" -pf -f "output_file_path"
java -jar WordTracker.jar "input_file_path" -pl -f "output_file_path"
java -jar WordTracker.jar "input_file_path" -po -f "output_file_path"
