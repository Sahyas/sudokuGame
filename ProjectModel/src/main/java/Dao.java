import exceptions.LoadFromFileException;
import exceptions.WriteToFileException;

public interface Dao<T> {
    T read() throws LoadFromFileException;

    void write(T obj) throws WriteToFileException;
}
