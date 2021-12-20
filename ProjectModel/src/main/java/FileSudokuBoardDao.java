import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileSudokuBoardDao implements  Dao<SudokuBoard>, AutoCloseable {
    String fileName;

    FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SudokuBoard read() {
        try (FileInputStream fin = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fin)) {
            SudokuBoard board = (SudokuBoard) ois.readObject();
            return board;
        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " not found");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found in file " + fileName);
        }
        return null;
    }

    @Override
    public void write(SudokuBoard board) {
        try (FileOutputStream fout = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fout);) {
            oos.writeObject(board);
        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void close() throws Exception {

    }
}