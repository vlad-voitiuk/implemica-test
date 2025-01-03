import java.util.ArrayList;
import java.util.List;
//    Для введенного числа 2 - 2 :
//            ()()
//            (())
//скобочное выражение "(()(()))" - правильное
// 3 - 3 :
// ()()()
// ((()))
// (()())
// (())()
// ()(())
public class Task1 {

    private final List<String> result;

    public Task1() {
        result = new ArrayList<>();
    }

    private void solution(int input, int open, int closed, String s, List<String> result) {

        // Amount of openBracket(OB) and closedBracket(CB) should be equal in that case task conditions are valid.
        // OB can be added only if the count of OB is less then input, and CB can be added only if its count is less then OB at any given index
        if (open == input && closed == input){
                result.add(s);
                return;
            }

        // Adding OB until their count reaches input
        if (open < input){
                solution(input, open + 1, closed, s + "(", result);
            }

        // Adding CB until their count not bigger then OB counter
        if (closed < open){
                solution(input, open, closed + 1, s + ")", result);
            }
        }

        public void generateAnswer(int input){
            solution(input, 0, 0, "", result);
        }

        public void displayAnswer(){
            for (String s: result){
                System.out.println(s);
            }
        }

    }


