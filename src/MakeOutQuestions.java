import java.util.*;

/**
 * Created by GMUK on 2018/10/7 0007.
 */
public class MakeOutQuestions {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Float> hashMap = new HashMap<>();
        System.out.print("请输入需要创建的题目的个数：");
        int count = scanner.nextInt();
        String questionTemp;
        float answertemp;
        for (int index = 0; index < count; index++) {
            do {
                questionTemp = getQuestion(2, 100);
                answertemp = getAnswer(questionTemp);
            } while (answertemp < 0);
            hashMap.put(questionTemp, answertemp);
        }
        Set<Map.Entry<String, Float>> ms = hashMap.entrySet();
        for (Map.Entry entry : ms) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }


    private static String getQuestion(Integer operatorNumber,Integer numberRange){
        char[] operator = new char[]{'+', '-', '*', '/'};
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int operatorIndex = 0; operatorIndex < operatorNumber; operatorIndex++) {
            stringBuilder.append(random.nextInt(numberRange+1));
            stringBuilder.append(operator[random.nextInt(4)]);
        }
        stringBuilder.append(random.nextInt(numberRange+1));
        return stringBuilder.toString();
    }

    private static Float getAnswer(String question){
        Stack<Character> operatorStack=new Stack<Character>();
        Stack<Float> numberStack=new Stack<Float>();
        char operatorTemp;
        StringBuilder numberTemp=new StringBuilder();
        for (int questionIndex=0;questionIndex<question.length();questionIndex++){
            char singleChar=question.charAt(questionIndex);
            if (Character.isDigit(singleChar)){
                numberTemp.append(singleChar);
            }else {
                if (!operatorStack.isEmpty()&&operatorStack.getTop()=='*'){
                    operatorStack.pop();
                    numberStack.push(numberStack.pop()*Float.valueOf(numberTemp.toString()));
                }else if (!operatorStack.isEmpty()&&operatorStack.getTop()=='/'){
                    operatorStack.pop();
                    numberStack.push(numberStack.pop()/Float.valueOf(numberTemp.toString()));
                }else if(!operatorStack.isEmpty()&&operatorStack.getTop()=='-'){
                    numberStack.push(-Float.valueOf(numberTemp.toString()));
                }else {
                    numberStack.push(Float.valueOf(numberTemp.toString()));
                }
                operatorStack.push(singleChar);
                numberTemp.delete(0,numberTemp.length());
            }
        }
        if (!operatorStack.isEmpty()&&operatorStack.getTop()=='*'){
            numberStack.push(numberStack.pop()*Float.valueOf(numberTemp.toString()));
            operatorStack.pop();
        }else if (!operatorStack.isEmpty()&&operatorStack.getTop()=='/'){
            numberStack.push(numberStack.pop()/Float.valueOf(numberTemp.toString()));
            operatorStack.pop();
        }else if(!operatorStack.isEmpty()&&operatorStack.getTop()=='-'){
            numberStack.push(-Float.valueOf(numberTemp.toString()));
        }else {
            numberStack.push(Float.valueOf(numberTemp.toString()));
        }
        while (!operatorStack.isEmpty()){
            operatorStack.pop();
                numberStack.push(numberStack.pop()+numberStack.pop());
        }
        return numberStack.pop();
    }
}
