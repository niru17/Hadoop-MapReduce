import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class KMeansMapper extends Mapper<LongWritable, Text, FloatWritable, FloatWritable> {

    private float[] centroids;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        // Initialize centroids from configuration, split by comma
        String[] centroidStrs = context.getConfiguration().get("centroids").split(",");
        centroids = new float[centroidStrs.length];
        for (int i = 0; i < centroidStrs.length; i++) {
            centroids[i] = Float.parseFloat(centroidStrs[i]);
        }
    }

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        float number = Float.parseFloat(value.toString());
        float minDistance = Float.MAX_VALUE;
        float closestCentroid = centroids[0];

        // Find the closest centroid
        for (float centroid : centroids) {
            float distance = Math.abs(centroid - number);
            if (distance < minDistance) {
                minDistance = distance;
                closestCentroid = centroid;
            }
        }

        context.write(new FloatWritable(closestCentroid), new FloatWritable(number));
    }
}
