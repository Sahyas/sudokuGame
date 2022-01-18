import exceptions.DaoException;
import java.sql.SQLException;

public interface Dao<T> {
    T read() throws DaoException;

    void write(T obj) throws SQLException, DaoException;
}
