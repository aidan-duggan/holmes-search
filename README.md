# holmes-search
A toy application to Spark search of Sherlock Holmes stories

As a tool to learn a bit about Cassandra and Spark developed this very simple program for doing search operations on the text of sherlock holmes novels (there is nothing Holmes specific obviously but it a source of public domain text and helps give some flavour to everything). The program is in 3 main sections, 1 section creates the cassandra key space etc. 1 section is a Spark job for reading the text of the novels and writing them into cassandra, the final section uses Spark Cassandra connector framework to do search on the text (a simple count is the current sample).

This was all developed with Atom on a very old laptop ,also as a learning experience. So without a full IDE to developer the first draft is very very rough. Also in general the aim was solely as a learning exercise for myself so the code is not in any way really ment to be sane or readable, it is not exactly functionality google does not already provide!

But for those looking for simple examples of Cassandra and Spark maybe it will be useful, who knows, I certainly learned a lot!

## Steps to run program

1. Download and run Cassandra

   Download [Cassandra](http://cassandra.apache.org/doc/latest/getting_started/index.html) and start it up. You can run some sample commands to insure it is working.

2. Download the source and compile using Maven 3.2 and Java 8 or higher.

   Pull down this repo and compile the source using Maven 3.2 and Java 8 or higher. This will generate a jar in the target folder that contains the code and all the dependencies bundled up.

3. Download Spark

   Download [Spark](http://spark.apache.org/docs/latest/) and test that the sample program executes.

4. Run the Spark Job to load the Cassandra database.

   Once Cassandra is working, the code is compiling and Spark is ready to go we now have to load the database with the text. To do that just run the following command, this will tell spark to execute the main in the class and jar provided:

   > {spark_directory}/bin/spark-submit --class "com.pog.holmesspark.HolmesSparkWrite" --master local[4] {source_dir}/target/holmes-db-0.1.jar

   At the moment this will print the Cassandra version number to confirm the connection made.

5. Run the Spark Job to query the database

   Now that the db contains the text of the novels we can run Spark jobs on it. The sample one will print out the count of the number of times "your Majesty" is said (this is hard coded at this stage..). 

   > {spark_directory}/bin/spark-submit --class "com.pog.holmessearch.HolmesSearch" --master local[4] {source_dir}/target/holmes-db-0.1.jar

### Actions to do:

Enhance docs with images, and improve default system.outs

Run the code through an IDE to improve formatting and warnings etc.

Refactor the code to present a more useful interface for doing different types of operations.

Try to run Spark and Cassandra in cluster mode and not just on the local machine.
