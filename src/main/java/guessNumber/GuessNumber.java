package guessNumber;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.RecursiveTask;

public class GuessNumber extends RecursiveTask<Integer> {

    private int[] target;

    private final int times;

    private int locationRight;

    private int count = 0;

    private final Random random = new Random();

    public GuessNumber(int n) {
        times = n;
        target = new int[times];
        for (int i = 0; i < times; i++) {
            int number;
            do {
                number = random.nextInt(10);
            } while (Boolean.TRUE.equals(check(i, number)));
            target[i] = number;
        }
    }

    @Override
    public Integer compute() {
        target = new int[times];
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < times; i++) {
            int number;
            do {
                number = random.nextInt(10);
            } while (Boolean.TRUE.equals(check(i, number)));
            target[i] = number;
        }
        do {
            String numbers = "";
            if (input.hasNext()) {
                numbers = input.nextLine();
            }
            if (numbers.length() != times) {
                continue;
            }
            count++;
        } while (locationRight != times);
        input.close();
        return null;
    }

    public String guess(String guess) {
        if (guess.length() != times) {
            return "请输入" + times + "个数字！";
        }
        char[] numbersArray = guess.toCharArray();
        int[] numbers = new int[times];
        for (int i = 0; i < times; i++) {
            numbers[i] = numbersArray[i] - 48;
        }
        locationRight = 0;
        int locationWrong = 0;
        for (int i = 0; i < times; i++) {
            if (numbers[i] == target[i]) {
                locationRight++;
            } else if (Boolean.TRUE.equals(check(times, numbers[i]))) {
                locationWrong++;
            }
        }
        count++;
        return "第" + count + "次：" + locationRight + " " + locationWrong;
    }

    private Boolean check(int index, int number) {
        for (int i = 0; i < index; i++) {
            if (target[i] == number) {
                return true;
            }
        }
        return false;
    }
}
