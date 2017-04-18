# holmes-search
A toy application to Spark search of Sherlock Holmes stories

As a tool to learn a bit about Cassandra and Spark developed this very simple program for doing search operations on the text of sherlock holmes novels (there is nothing Holmes specific obviously but it a source of public domain text and helps give some flavour to everything). The program is in 3 main sections, 1 section creates the cassandra key space etc. 1 section is a Spark job for reading the text of the novels and writing them into cassandra, the final section uses Spark Cassandra connector framework to do search on the text (a simple count is the current sample).

This was all developed with Atom on a very old laptop ,also as a learning experience. So without a full IDE to developer the first draft is very very rough. Also in general the aim was solely as a learning exercise for myself so the code is not in any way really ment to be sane or readable, it is not exactly functionality google does not already provide!

But for those looking for simple examples of Cassandra and Spark maybe it will be useful, who knows, I certainly learned a lot!

Actions to do:

Provide doc and scripts for running the program.

Run the code through an IDE to improve formatting and warnings etc.

Refactor the code to present a more useful interface for doing different types of operations.

Try to run Spark and Cassandra in cluster mode and not just on the local machine.
