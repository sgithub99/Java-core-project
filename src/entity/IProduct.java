package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public interface IProduct {
	float MIN_INTEREST_RATE = 0.2f;
	void inputData(Scanner input, List<Product> products);//nhap thong tin san pham
	String displayData(); //hien thi thong tin san pham
	float calProfit(); //tinh loi nhuan tren tat ca san pham

}
