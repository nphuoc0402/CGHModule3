public class Calculator {

    public int calculate(int foperand, String operator, int soperand){
        int result = 0;
        switch (operator){
            case "+":
                result = foperand + soperand;
                break;
            case "-":
                result = foperand - soperand;
                break;
            case "*" :
                result = foperand * soperand;
                break;
            case "/":
                if(soperand == 0){
                    throw new RuntimeException("Divide By Zero!");
                }else {
                    result = foperand / soperand;
                }
                break;
        }
        return result;

    }
}
