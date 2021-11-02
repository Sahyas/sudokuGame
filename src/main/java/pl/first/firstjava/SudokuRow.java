package pl.first.firstjava;

public class SudokuRow{
    public boolean verify(){
        int expectedValue = 362880;
        int result = 1;
        for(int i=0; i<9; i++){
           // result*=row[i];
            if(result==expectedValue)
                return true;
        }
        return false;
    }
}
