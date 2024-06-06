import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class KMeansDriver {
    public static void main(String[] args) throws Exception {
        if (args.length != 3) {  // Expecting three arguments now
            System.err.println("Usage: KMeansDriver <input path> <output path> <centroids>");
            System.exit(-1);
        }

        Configuration conf = new Configuration();
        conf.set("centroids", args[2]);  // Set centroids passed as third argument

        Job job = Job.getInstance(conf, "K Means Clustering");
        job.setJarByClass(KMeansDriver.class);
        job.setMapperClass(KMeansMapper.class);
        job.setReducerClass(KMeansReducer.class);

        job.setOutputKeyClass(FloatWritable.class);
        job.setOutputValueClass(FloatWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
