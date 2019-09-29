package functions;

import elements.GeneralTask;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public interface Saveable {
    public void save(ArrayList<GeneralTask> tasks) throws FileNotFoundException, UnsupportedEncodingException;
}
