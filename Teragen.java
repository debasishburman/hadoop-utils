import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;

public class Teragen {

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

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Teragen");
        job.setJarByClass(Teragen.class);
        job.setMapperClass(TeragenMapper.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        // Set the number of mappers and output path as needed
        // job.setNumReduceTasks(0);
        // FileOutputFormat.setOutputPath(job, new Path("hdfs://<namenode>:<port>/output/teragen"));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
