import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        task1.generateAnswer(3);
        task1.displayAnswer();

        Task2 task2 = new Task2();
        task2.start();

        Task3 task3 = new Task3();
        System.out.println(task3.factorialSumOfDigits(100));
    }


}
