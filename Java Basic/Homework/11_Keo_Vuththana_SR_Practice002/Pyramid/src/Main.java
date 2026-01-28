//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter how many rows you want: ");

    // Pyramid
    String input = scanner.nextLine();
    int rows = input.matches("^\\d+$") ? Integer.parseInt(input) : 0;

    // Odd number Formula (2n + 1)
    for(int i = 0; i < rows; i++) {
        for (int j = rows; j > i; j--) {
            System.out.print(" ");
        }
        for (int k = 0; k < 2 * i + 1; k++) {
            System.out.print("*");
        }
        System.out.print("\n");
    }
}
