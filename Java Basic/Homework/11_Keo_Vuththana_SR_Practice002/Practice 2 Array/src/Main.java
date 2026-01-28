void main() {
  int[][] students = new int[2][4];
  int[][] scores = new int[2][1];
  int[][] average = new int[2][1];

  String format = "^\\d+$";
  for (int i = 0; i < students.length; i++) {
    System.out.println("Student " + (i + 1) + ":");
    for (int j = 0; j < students[i].length; j++) {
      Scanner scanner = new Scanner(System.in);
      students[i][j] = switch (j) {
        case 0 -> {
          System.out.print("Web: ");
          String web = scanner.nextLine();
          yield scores[i][0] += web.matches(format) ? Integer.parseInt(web) : 0;
        }

        case 1 -> {
          System.out.print("Java: ");
          String java = scanner.nextLine();
          yield scores[i][0] += java.matches(format) ? Integer.parseInt(java) : 0;
        }

        case 2 -> {
          System.out.print("Korea: ");
          String korea = scanner.nextLine();
          yield scores[i][0] += korea.matches(format) ? Integer.parseInt(korea) : 0;
        }

        case 3 -> {
          System.out.print("Database: ");
          String db = scanner.nextLine();
          yield scores[i][0] += db.matches(format) ? Integer.parseInt(db) : 0;
        }
        default -> 0;
      };
    }
  }


  // Student 1
  System.out.println("Student 2 Total Score: " + scores[0][0]);
  System.out.println("Student 2 Average Score: " + scores[0][0] / students[0].length);

  // Student 2
  System.out.println("Student 2 Total Score: " + scores[1][0]);
  System.out.println("Student 2 Average Score: " + scores[1][0] / students[1].length );

}
