# Assignment3_Group3

**Introduction:**

The program accepts user input from the command line, reads information from a text file, and generates a word object of the binary tree. This word object is serialized into the `repository.ser` file. Each word object is also stored in a list within the Binary Search Tree (BST). The program allows users to produce output, specified via command line arguments, to generate reports using various iterators built into the BST.

- **Date:** Dec 1, 2023
- **Authors:** Isaac, Jayden, David

**User Options:**

Users can choose from three ways to retrieve information:

1. `-pf`: Print words in alphabetic order along with the corresponding list of files in which the words occur.
2. `-pl`: Print words in alphabetic order along with the corresponding list of files and numbers of the lines in which the word occurs.
3. `-po`: Print words in alphabetic order along with the corresponding list of files, numbers of the lines in which the word occurs, and the frequency of occurrence of the words.

**How to Run:**

To run this program, follow these steps:

1. **Clone the Repository:**

   ```shell
   git clone https://github.com/Saffy127/Assignment3_Group3.git
   
   cd Assignment3_Group3
   java -jar WordTracker.jar "input_file_path" -pf -f "output_file_path"
   java -jar WordTracker.jar "input_file_path" -pl -f "output_file_path"
   java -jar WordTracker.jar "input_file_path" -po -f "output_file_path"
