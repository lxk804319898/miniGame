package guessNumber;

import java.util.Scanner;
import java.util.concurrent.RecursiveTask;

public class GuessNumber extends RecursiveTask<Integer> {

    private int[] target;

    private int times;

    private int locationRight;

    private int locationWrong;

    private int count = 0;

    public GuessNumber(int n){
        times = n;
        target = new int[times];
        for(int i = 0; i < times; i++){
            int number;
            do {
                number = (int)(Math.random()*10);
            }while (check(i,number));
            target[i] = number;
        }
    }

    @Override
    public Integer compute(){
        target = new int[times];
        Scanner input = new Scanner(System.in);
        for(int i = 0; i < times; i++){
            int number;
            do {
                number = (int)(Math.random()*10);
            }while (check(i,number));
            target[i] = number;
        }
        do{
            System.out.print("请输入猜的"+times+"个数:");
            String numbers = new String();
            if(input.hasNext()){
                numbers = input.nextLine();
            }
            if(numbers.length()!=times){
                System.out.println("请输入正确的个数！");
                continue;
            }
            System.out.println(locationRight+" "+locationWrong);
            count++;
        } while (locationRight!=times);
        System.out.println("猜对了，一共猜了"+count+"次");
        input.close();
        return null;
    }

    public String guess(String guess){
        if(guess.length()!=times){
            return "请输入"+times+"个数字！";
        }
        char[] numbersArray = guess.toCharArray();
        int[] numbers = new int[times];
        for(int i = 0; i < times; i++){
            numbers[i] = numbersArray[i]-48;
        }
        locationRight = 0;
        locationWrong = 0;
        for(int i = 0; i < times; i++){
            if(numbers[i] == target[i]){
                locationRight++;
            }else if(check(times, numbers[i])){
                locationWrong++;
            }
        }
        count++;
        return "第"+count+"次："+locationRight+" "+locationWrong;
    }

    private Boolean check(int index, int number){
        for(int i = 0; i < index; i++){
            if(target[i]==number){
                return true;
            }
        }
        return false;
    }
}
