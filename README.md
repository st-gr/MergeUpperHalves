# MergeUpperHalves PDF Tool

**MergeUpperHalves** is a Java-based command-line utility designed to efficiently process PDF documents by merging the upper halves of two consecutive pages into a single page. This tool is particularly useful for documents where critical content resides primarily in the top halves of the pages, such as certain types of forms, tickets, or boarding passes. By merging pages in this way, it helps in reducing the overall page count and conserves paper when printing.

## Key Features

- **Page Reduction:** Effectively halves the number of pages in a PDF document by merging the top halves of consecutive pages.
- **Space Efficiency:** Ideal for documents like boarding passes where only the top part of each page is used.

## Usage

To use the tool, simply run the following command:

```bash
java -jar MergeUpperHalves.jar path/to/your/input.pdf
```

The tool processes the input PDF and generates an output file with '_modified' appended to the original filename.

## Building from Source

**MergeUpperHalves** is built using Apache Ant and Apache Ivy for dependency management. To build the project, navigate to the project root and execute:

```bash
ant build
```

This command compiles the source code, fetches necessary dependencies, and creates an executable jar file.

## Contribution

Contributions to the **MergeUpperHalves** project are welcome. Whether it's feature enhancements, bug fixes, or documentation improvements, feel free to fork this repository and submit a pull request.