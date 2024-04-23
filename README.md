# hadoop-utils
Teragen and Terasort are commonly used to evaluate the performance, scalability, and efficiency of Hadoop clusters, especially in handling big data workloads. To perform Teragen and Terasort in a Hadoop cluster using Java, you'll typically use the Hadoop MapReduce framework. Below is an example Java code for both Teragen and Terasort.


To set the appropriate libraries in the classpath for compiling and running the Java code with Hadoop MapReduce, you typically need to include the Hadoop libraries along with your code. Here's how you can do it:

Compile Command:
Assuming you have Hadoop installed on your system, find the location of the Hadoop JAR files (hadoop-common.jar, hadoop-mapreduce-client-core.jar, etc.). These JAR files contain the necessary Hadoop classes for MapReduce jobs.
Compile your Java code with the Hadoop libraries included in the classpath. Here's an example compilation command:
bash
Copy code
javac -classpath "/path/to/hadoop-common.jar:/path/to/hadoop-mapreduce-client-core.jar:/other/dependencies/*" TeragenTerasort.java
Replace /path/to/hadoop-common.jar, /path/to/hadoop-mapreduce-client-core.jar, and /other/dependencies/* with the actual paths to your Hadoop JAR files and any other dependencies your code requires. The asterisk (*) at the end includes all JAR files in the specified directory.
Run Command:
After compiling successfully, run the Java code with the appropriate classpath settings. Here's an example command:
bash
Copy code
java -classpath "/path/to/hadoop-common.jar:/path/to/hadoop-mapreduce-client-core.jar:/other/dependencies/*:." TeragenTerasort
Replace /path/to/hadoop-common.jar, /path/to/hadoop-mapreduce-client-core.jar, /other/dependencies/*, and . with the actual paths and dependencies needed for your Hadoop job. The . at the end includes the current directory in the classpath, assuming your compiled Java class (TeragenTerasort.class) is in the current directory.
Setting CLASSPATH Environment Variable (Optional):
If you prefer setting the classpath using an environment variable, you can do so. For example, on Linux/Mac:
bash
Copy code
export CLASSPATH="/path/to/hadoop-common.jar:/path/to/hadoop-mapreduce-client-core.jar:/other/dependencies/*:."
On Windows:
cmd
Copy code
set CLASSPATH="C:\path\to\hadoop-common.jar;C:\path\to\hadoop-mapreduce-client-core.jar;C:\other\dependencies\*;."
Then, you can compile and run your Java code without specifying the classpath explicitly in the commands.
