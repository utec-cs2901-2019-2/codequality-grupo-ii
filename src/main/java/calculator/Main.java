package calculator ;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String eq = scanner.nextLine();
        operation op = new operation(eq);
        System.out.println(op.operate());
    }
}
