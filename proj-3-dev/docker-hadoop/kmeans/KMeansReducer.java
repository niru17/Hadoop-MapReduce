import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KMeansReducer extends Reducer<FloatWritable, FloatWritable, FloatWritable, FloatWritable> {

    @Override
    public void reduce(FloatWritable key, Iterable<FloatWritable> values, Context context) throws IOException, InterruptedException {
        float sum = 0;
        int count = 0;
        for (FloatWritable val : values) {
            sum += val.get();
            count++;
        }
        float newCentroid = sum / count;

        // Emit the new centroid and the original value
        context.write(new FloatWritable(newCentroid), key);
    }
}
