package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import dao.ShopManagement;
import entity.*;

public class App {

	public static void main(String[] args) {
		List<Categories> categories = new ArrayList<Categories>();
		List<Product> products = new ArrayList<Product>();

		categories = loadCategory();
		products = loadProduct();
		
		ShopManagement shopManagement = new ShopManagement(categories, products);

		int choiceA;
		boolean exitA = false;
		do {
			choiceA = shopManagement.menuApp();
			switch (choiceA) {
			case 1:
				int choiceC;
				boolean exitC = false;
				do {
					choiceC = shopManagement.menuCateManagement();
					switch (choiceC) {
					case 1:// Danh sach danh muc
						int choiceC1;
						boolean exitC1 = false;
						do {
							choiceC1 = shopManagement.menuListCategory();
							switch (choiceC1) {
							case 1:

								shopManagement.showTreeCategory(categories);

								break;
							case 2:
								shopManagement.showCateDetail(categories);
								break;
							case 3:
								exitC1 = true;
								break;
							default:
								System.out.println("Vui long nhap lua chon tu 1-3");
								break;
							}
						} while (!exitC1);
						break;
					case 2:// Them danh muc
						shopManagement.addCategory(categories);
						break;
					case 3:// Xoa danh muc
						shopManagement.deleteCategory(categories);
						break;
					case 4:// Tim kiem danh muc
						shopManagement.searchCategoryById(categories);
						break;
					case 5:// Quay lai
						exitC = true;
						break;
					default:
						System.out.println("Vui long nhap lua chon tu 1-5");
						break;
					}
				} while (!exitC);
				break;
			case 2:
				int choiceP;
				boolean exitP = false;
				do {
					choiceP = shopManagement.menuProduct();
					switch (choiceP) {
					case 1:// Them moi san pham
						System.out.println("Nhap so luong san pham can them");
						Scanner input = new Scanner(System.in);
						int n = input.nextInt();
						for (int i = 0; i < n; i++) {
							shopManagement.addProduct(products, categories);
						}
						break;
					case 2:// Tinh loi nhuan san pham
						for (Product p : products) {
							System.out.println("Ma san pham: " + p.getProductId() + "- Ten san pham: "
									+ p.getProductName() + " - Loi nhuan: " + p.calProfit());
						}
						break;
					case 3:// Hien thi thong tin san pham
						int choiceP1;
						boolean exitP1 = false;
						do {
							choiceP1 = shopManagement.menuProductInfor();
							switch (choiceP1) {
							case 1:// Hien thi san pham theo danh muc
								shopManagement.showProductByCate(categories, products);
								break;
							case 2:// Hien thi chi tiet san pham
								shopManagement.showProductDetail(products);
								break;
							case 3:
								exitP1 = true;
								break;
							default:
								System.out.println("Vui long nhap tu 1-3");
								break;
							}
						} while (!exitP1);
						break;
					case 4:// Sap xep san pham
						int choiceP2;
						boolean exitP2 = false;
						do {
							choiceP2 = shopManagement.menuSortProduct();
							switch (choiceP2) {
							case 1:// sap xep san pham theo gia suat tang dan
								System.out.println("=============SAP XEP SAN PHAM THEO GIA BAN TANG DAN=============");
								shopManagement.sortProductByExportPrice();
								break;
							case 2:// sap xep san pham theo loi nhuan giam dan
								System.out
										.println("=============SAP XEP SAN PHAM THEO LOI NHUAN GIAM DAN=============");
								shopManagement.sortProductByProfit();
								break;
							case 3:// quay lai
								exitP2 = true;
								break;
							default:
								break;
							}
						} while (!exitP2);
						break;
					case 5:// Cap nhat thong tin san pham
						shopManagement.updateProductInfo(products, categories);
						break;
					case 6:// Cap nhat trang thai san pham
						shopManagement.updateStatusProduct(products);
						break;
					case 7:
						exitP = true;
						break;
					default:
						System.out.println("Vui long nhap tu 1-7");
						break;
					}

				} while (!exitP);
				break;
			case 3:
				exitA = true;
				break;
			default:
				break;
			}
		} while (!exitA);
		saveData(categories, products);
	}

	private static void saveData(List<Categories> categories, List<Product> products) {
		ObjectOutputStream cos = null;
		try {
			cos = new ObjectOutputStream(new FileOutputStream("D:\\ECLIPSE\\FINAL_PROJECT\\src\\app\\categories.txt"));
			cos.writeObject(categories);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				cos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		ObjectOutputStream pos = null;
		try {
			pos = new ObjectOutputStream(new FileOutputStream("D:\\ECLIPSE\\FINAL_PROJECT\\src\\app\\products.txt"));
			pos.writeObject(products);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private static List<Categories> loadCategory() {
		List<Categories> categories = new ArrayList<Categories>();
		// readObject
		try {
			File file = new File("D:\\ECLIPSE\\FINAL_PROJECT\\src\\app\\categories.txt");
			if (file.exists()) {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
				categories = (List<Categories>) ois.readObject();
				ois.close();
			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (categories.size() == 0) {
			categories.add(new Categories(1, "Quan ao", "abc", true, 0));
			categories.add(new Categories(2, "Quan ao nam", "abc", true, 1));
			categories.add(new Categories(3, "Quan ao nu", "abc", true, 1));
			categories.add(new Categories(4, "Ao so mi", "abc", true, 2));
			categories.add(new Categories(5, "Quan au", "abc", true, 2));
			categories.add(new Categories(6, "Do dien tu", "abc", true, 0));
			categories.add(new Categories(7, "Dien thoai", "abc", true, 6));

		}

		return categories;

	}

	private static void readProduct() {
		ObjectOutputStream oos = null;
		List<Product> products = new ArrayList<Product>();
		try {
			oos = new ObjectOutputStream(new FileOutputStream("D:\\ECLIPSE\\FINAL_PROJECT\\src\\app\\products.txt"));
			oos.writeObject(products);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void readCategories() {
		ObjectOutputStream oos = null;
		List<Categories> categories = new ArrayList<Categories>();
		try {
			oos = new ObjectOutputStream(new FileOutputStream("D:\\ECLIPSE\\FINAL_PROJECT\\src\\app\\categories.txt"));
			oos.writeObject(categories);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private static List<Product> loadProduct() {
		List<Product> products = new ArrayList<Product>();
		try {
			File file = new File("D:\\ECLIPSE\\FINAL_PROJECT\\src\\app\\products.txt");
			if (file.exists()) {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
				products = (List<Product>) ois.readObject();
				ois.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return products;
	}

}