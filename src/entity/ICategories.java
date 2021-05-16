package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public interface ICategories {
	void inputData(Scanner input, List<Categories> categories);//nhap thong tin danh muc san pham
	String displayData();//hien thi thong tin danh muc san pham
	
}
