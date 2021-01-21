package com.ssafy.hw;

import java.util.Arrays;
import java.util.Scanner;

public class ProductTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		TV []t = new TV[1];
		Refrigerator []r = new Refrigerator[1];
		int tcount = 0;
		int rcount = 0;
		while(true) {
			System.out.println("1. TV정보 입력");
			System.out.println("2. 냉장고정보 입력");
			System.out.println("3. 재고정보 출력");
			System.out.println("0. 프로그램 종료");
			int choice = sc.nextInt();
			switch(choice)
			{
			case 1:
				TV t1 = new TV();
				System.out.println("제품번호를 입력하세요.");
				t1.setNum(sc.nextInt());
				System.out.println("이름을 입력하세요.");
				sc.nextLine();
				t1.setName(sc.nextLine());
				System.out.println("가격를 입력하세요.");
				t1.setPrice(sc.nextInt());
				System.out.println("수량을 입력하세요.");
				t1.setQuan(sc.nextInt());
				System.out.println("크기를 입력하세요.");
				t1.setInch(sc.nextInt());
				System.out.println("패널유형을 입력하세요.");
				sc.nextLine();
				t1.setType(sc.nextLine());
				t[tcount] = t1;
				tcount++;
				break;
			case 2:
				Refrigerator r1 = new Refrigerator();
				System.out.println("제품번호를 입력하세요.");
				r1.setNum(sc.nextInt());
				sc.nextLine();
				System.out.println("이름을 입력하세요.");
				r1.setName(sc.nextLine());
				System.out.println("가격를 입력하세요.");
				r1.setPrice(sc.nextInt());
				System.out.println("수량을 입력하세요.");
				r1.setQuan(sc.nextInt());
				System.out.println("용량을 입력하세요.");
				r1.setVol(sc.nextInt());
				r[rcount] = r1;
				rcount++;
				break;
			case 3:
				for (int i = 0; i<tcount; i++) {
					System.out.println(t[i]);
				}
				for (int i = 0; i<rcount; i++) {
					System.out.println(r[i]);
				}
				break;
			case 0:
				sc.close();
				System.exit(0);
			}
			
			if (tcount==t.length) {
				t = Arrays.copyOf(t, tcount*2);
			}
			if (rcount==t.length) {
				r = Arrays.copyOf(r, rcount*2);
			}
			
		}
		
		
	}

}
