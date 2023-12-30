# MergeUpperHalves

MergeUpperHalves is a simple Java utility that merges the top halves of consecutive PDF pages into single pages in a new document. This is particularly useful for documents where only the top half of each page contains relevant information, effectively reducing the page count by half.
I use it to merge bording passes 2 by 1 into one pdf to save on paper and space.

## Usage

To use MergeUpperHalves, ensure you have Java installed on your system. I am using OpenJDK Runtime Environment SapMachine (build 20.0.1+9)

### Running the Program

```bash
java -jar MergeUpperHalves.jar path/to/input.pdf
