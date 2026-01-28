//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter how many rows to form a diamond: ");
    String input = scanner.nextLine();
    int rows = input.matches("^\\d+$") ? Integer.parseInt(input) : 0;

    // Pyramid
    for (int i = 0; i <= rows; i++) {
        for(int j = rows - 1; j >= i; j--) {
            System.out.print(" ");
        }
        for (int k = 0; k < 2 * i + 1; k++) {
            System.out.print("*");
        }
        System.out.print("\n");
    }

    // Reversed Pyramid
    for (int i = rows - 1; i >= 0; i--) {
        for (int j = rows - 1; j >= i; j--) {
            System.out.print(" ");
        }
        for (int k = 0; k < 2 * i + 1; k++) {
            System.out.print("*");
        }
        System.out.print("\n");
    }
}
