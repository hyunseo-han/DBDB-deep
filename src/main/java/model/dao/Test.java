package model.dao;

import java.util.Scanner;

public class Test {
    private static RentDao rentDao = new RentDao();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("대여할 물건 id 입력하시오: ");
        String productId = scanner.next();
        System.out.println();
        

    }

}
