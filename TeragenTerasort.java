import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

public class TeragenTerasort {

    public static class TeragenMapper extends Mapper<LongWritable, NullWritable, Text, NullWritable> {
        private final Text outputKey = new Text();

        @Override
        protected void map(LongWritable key, NullWritable value, Context context) throws IOException, InterruptedException {
            // Generate data here (e.g., random data)
            String data = generateData();
            outputKey.set(data);
            context.write(outputKey, NullWritable.get());
        }

        private String generateData() {
            // Implement data generation logic (e.g., random strings or numbers)
            // Example: return UUID.randomUUID().toString();
            return "Sample data"; // Replace with actual data generation code
        }
    }

    public static class TerasortReducer extends Reducer<Text, NullWritable, Text, NullWritable> {
        @Override
        protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            context.write(key, NullWritable.get());
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job teragenJob = Job.getInstance(conf, "Teragen");
        teragenJob.setJarByClass(TeragenTerasort.class);
        teragenJob.setMapperClass(TeragenMapper.class);
        teragenJob.setOutputKeyClass(Text.class);
        teragenJob.setOutputValueClass(NullWritable.class);
        teragenJob.setNumReduceTasks(0); // No reducer needed for Teragen
        TextInputFormat.addInputPath(teragenJob, new Path("input/teragen_input"));
        TextOutputFormat.setOutputPath(teragenJob, new Path("output/teragen_output"));

        boolean teragenSuccess = teragenJob.waitForCompletion(true);
        if (!teragenSuccess) {
            System.exit(1);
        }

        Job terasortJob = Job.getInstance(conf, "Terasort");
        terasortJob.setJarByClass(TeragenTerasort.class);
        terasortJob.setMapperClass(Mapper.class); // Default identity mapper for Terasort
        terasortJob.setReducerClass(TerasortReducer.class);
        terasortJob.setOutputKeyClass(Text.class);
        terasortJob.setOutputValueClass(NullWritable.class);
        terasortJob.setNumReduceTasks(10); // Set the number of reducers as needed
        TextInputFormat.addInputPath(terasortJob, new Path("output/teragen_output"));
        TextOutputFormat.setOutputPath(terasortJob, new Path("output/terasort_output"));

        System.exit(terasortJob.waitForCompletion(true) ? 0 : 1);
    }
}
