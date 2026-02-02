import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;

void main() {
    Scanner scanner = new Scanner(System.in);
    int[] numbers = new int[5];

    for (int i = 0; i < numbers.length; i++) {
        System.out.print("Enter Integer " +( i + 1) + " : ");
        String input = scanner.nextLine();

        numbers[i] = input.matches("^-*\\d+$") ?
                Integer.parseInt(input) : 0;
    }

    System.out.println("Your Array: " + Arrays.toString(numbers));

    int max = MIN_VALUE;
    int min = MAX_VALUE;
    for (int num : numbers) {
        if (max < num) {
            max = num;
        }
        if (min > num) {
            min = num;
        }
    }

    System.out.println("Max: " + max);
    System.out.println("Min: " + min);
}