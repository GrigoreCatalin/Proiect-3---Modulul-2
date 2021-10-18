import java.util.*;

public class RedixSort {

    public static int getMaxDigits(int maxNumber) {
        int counter = 0;
        while (maxNumber != 0) {
            maxNumber /= 10;
            counter++;
        }
        return counter;
    }

    public static int getMaxNumber(ArrayList<Integer> numbers) {
        int maxNumber = Integer.MIN_VALUE;

        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) > maxNumber) {
                maxNumber = numbers.get(i);
            }
        }
        return maxNumber;
    }

    public static ArrayList<Integer> ascendingNumbers(ArrayList<Integer> numbers, Queue<Integer>[] queue, int maxDigits) {
        int multiplication = 1;
        for (int i = 0; i < maxDigits; i++) {
            for (int j = 0; j < numbers.size(); j++) {
                int num = ((numbers.get(j)) / multiplication) % 10;
                queue[num].add((numbers.get(j)));
            }
            multiplication *= 10;


            int rightLimit = 10;

            int counter = 0;
            for (int leftLimit = 0; leftLimit < rightLimit; leftLimit++) {
                while (!queue[leftLimit].isEmpty()) {
                    int elementRemoved = queue[leftLimit].remove();
                    numbers.set(counter, elementRemoved);
                    counter++;
                }
            }
        }
        return numbers;
    }

    public static ArrayList<Integer> descendingNumbers(ArrayList<Integer> numbers, Queue<Integer>[] queue, int maxDigits) {
        int multiplication = 1;

        for (int i = 0; i < maxDigits; i++) {
            for (int j = 0; j < numbers.size(); j++) {
                int num = ((numbers.get(j)) / multiplication) % 10;
                queue[num].add((numbers.get(j)));
            }
            multiplication *= 10;


            int counter = 0;
            int leftLimit = 0;

            for (int rightLimit = 9; rightLimit >= 0; rightLimit--) {
                while (!queue[rightLimit].isEmpty()) {
                    int elementRemoved = queue[rightLimit].remove();
                    numbers.set(counter, elementRemoved);
                    counter++;
                }
            }
        }
        return numbers;
    }

    public static void display(ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            System.out.print(numbers.get(i) + " ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            numbers.add(sc.nextInt());
        }

        int maxNumber = getMaxNumber(numbers);
        int maxDigits = getMaxDigits(maxNumber);

        Deque<Integer>[] queue = (ArrayDeque<Integer>[]) (new ArrayDeque[10]);
        for (int i = 0; i < 10; i++) {
            queue[i] = new ArrayDeque<Integer>();
        }

        ArrayList<Integer> increasing = ascendingNumbers(numbers, queue, maxDigits);
        display(increasing);

        System.out.println();

        ArrayList<Integer> decreasing = descendingNumbers(numbers, queue, maxDigits);
        display(decreasing);


    }
}