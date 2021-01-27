# DataStructures-Performance
Back-end code for Coursera course by University of California San Diego.

#### Week 1
Centers on environment set up,
ensure you have a JDK 1.8 + with a supporting Eclipse IDE.

Setup can be tricky, consider your java path, jfx location and compiler compliance level.

The error prompts spice things up, google's your friend. *wink


#### Week 2
Focuses on implementations in the document package. 

A view on String immutability, memory models, interned strings and regular expressions.

[Document class](https://github.com/bisscay/DataStructures-Performance/blob/main/MOOCTextEditor/src/document/Document.java)

[BasicDocument class](https://github.com/bisscay/DataStructures-Performance/blob/main/MOOCTextEditor/src/document/BasicDocument.java)

#### Week 3
Centers on performance (Asymptotic Analysis and Benchmarking), below is a plot comparing two classes in the document package.

BasicDocument in blue has a steeper linear slope performing poorly as the input scales, compared to EfficientDocument in red. 

[EfficientDocument](https://github.com/bisscay/DataStructures-Performance/blob/main/MOOCTextEditor/src/document/EfficientDocument.java)

**Benchmark Plot:**

![Week 3 Benchmark Image](https://github.com/bisscay/DataStructures-Performance/blob/main/benchmarkWeek3.png)

#### Week 4
In Part 1, JUnit is used to adopt a test-driven approach in developing a doubly linked list using sentinels.

[My Linked List Tester](https://github.com/bisscay/DataStructures-Performance/blob/main/MOOCTextEditor/src/textgen/MyLinkedListTester.java)

[My Linked List Class](https://github.com/bisscay/DataStructures-Performance/blob/main/MOOCTextEditor/src/textgen/MyLinkedList.java)

In Part 2, a random MarkovTextGenerator is built. One key view is the space complexity advantage a StringBuilder  has over immutable strings.

[Markov Text Generator](https://github.com/bisscay/DataStructures-Performance/blob/main/MOOCTextEditor/src/textgen/MarkovTextGeneratorLoL.java)
