package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import entity.Categories;
import entity.Product;
import entity.IProduct;

public class ShopManagement {
	private List<Categories> categories;
	private List<Product> products;

	public ShopManagement() {
		super();
	}

	public ShopManagement(List<Categories> categories, List<Product> products) {
		super();
		this.categories = categories;
		this.products = products;
	}

	public List<Categories> getCategories() {
		return categories;
	}

	public void setCategories(List<Categories> categories) {
		this.categories = categories;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public int menuApp() {
		System.out.println("=====================================MENU=====================================");
		System.out.println("1. Quan ly danh muc");
		System.out.println("2. Quan ly san pham");
		System.out.println("3. Thoat");
		System.out.println("Su lua chon cua ban: ");
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		return choice;
	}

	public int menuCateManagement() {
		System.out
				.println("=====================================QUAN LY DANH MUC=====================================");
		System.out.println("1. Danh sach danh muc");
		System.out.println("2. Them danh muc");
		System.out.println("3. Xoa danh muc");
		System.out.println("4. Tim kiem danh muc");
		System.out.println("5. Quay lai");
		System.out.println("Su lua chon cua ban: ");
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		return choice;
	}

	/**
	 * categories are added
	 * 
	 * @param categories
	 */
	public void addCategory(List<Categories> categories) {
		System.out.println("Nhap so luong danh muc can them");
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		for (int i = 0; i < n; i++) {
			Categories c = new Categories();
			c.inputData(input, categories);
			categories.add(c);
		}
	}

	/**
	 * categories are deleted by categoriesID
	 * 
	 * @param categories
	 */
	public void deleteCategory(List<Categories> categories) {
		boolean flag = false;
		if (categories.size() > 0) {
			try {
				Scanner input = new Scanner(System.in);
				System.out.println("Nhap ma danh muc can xoa");
				int cateId = input.nextInt();
				input.nextLine();
				for (Categories c : categories) {
					if (cateId == c.getCatalogId()) {
						categories.remove(c);
						System.out.println("Xoa danh muc thanh cong");
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("Ma danh muc phai la so nguyen");
				e.printStackTrace();
			}
		} else {
			System.out.println("Danh sach danh muc rong");
		}

	}

	/**
	 * categories are searched by categoriesID
	 * 
	 * @param categories
	 */
	public void searchCategoryById(List<Categories> categories) {
		List<Categories> categories2 = new ArrayList<Categories>();
		System.out.println("Nhap ten danh muc can tim kiem");
		Scanner input = new Scanner(System.in);
		String nameCate = input.nextLine();
		boolean flag = false;
		for (Categories c : categories) {
			if (nameCate.equals(c.getCatalogName())) {
				categories2.add(c);
				flag = true;
				break;
			}
		}
		if (!flag) {
			System.out.println("Khong tim thay danh muc co ten: " + nameCate);
		}
		for (Categories c : categories2) {
			System.out.println(c.displayData());
		}

	}

	public int menuListCategory() {
		System.out.println(
				"=====================================DANH SACH DANH MUC=====================================");
		System.out.println("1. Danh sach cay danh muc");
		System.out.println("2. Thong tin chi tiet danh muc");
		System.out.println("3. Quay lai");
		System.out.println("Su lua chon cua ban: ");
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		return choice;
	}

	/**
	 * show tree category
	 * 
	 * @param categories
	 */
	public void showTreeCategory(List<Categories> categories) {
		Collections.sort(categories, new Comparator<Categories>() {
			@Override
			public int compare(Categories c1, Categories c2) {
				// TODO Auto-generated method stub
				return c1.getCatalogName().compareTo(c2.getCatalogName());
			}
		});

		int count1, count2, count3;
		count1 = count2 = count3 = 1;
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getParentId() == 0) {
				System.out.printf("%d.%s\n", count1, categories.get(i).getCatalogName());

				for (int j = 0; j < categories.size(); j++) {
					if (categories.get(j).getParentId() == categories.get(i).getCatalogId()) {
						System.out.printf("\t%d.%d.%s\n", count1, count2, categories.get(j).getCatalogName());
						for (int k = 0; k < categories.size(); k++) {
							if (categories.get(k).getParentId() == categories.get(j).getCatalogId()) {
								System.out.printf("\t\t%d.%d.%d.%s\n", count1, count2, count3,
										categories.get(k).getCatalogName());
								count3++;
							}
						}
						count2++;
					}
				}
				count2 = 1;
				count1++;
			}
		}
	}

	/**
	 * show category in more detail
	 * 
	 * @param categories
	 */
	public void showCateDetail(List<Categories> categories) {
		boolean flag = false;
		Scanner input = new Scanner(System.in);
		System.out.println("Nhap ten danh muc can xem thong tin");
		String nameCate = input.nextLine();
		for (Categories c : categories) {
			if (nameCate.equals(c.getCatalogName())) {
				System.out.println(c.displayData());
				for (Categories c1 : categories) {
					if (c1.getParentId() == c.getCatalogId()) {
						System.out.println(c1.displayData());
					}
				}
				flag = true;
			}
		}
		if (!flag) {
			System.out.printf("Khong tim thay ten danh muc co ten la '%s'\n", nameCate);
		}
	}

	public int menuProduct() {
		System.out
				.println("=====================================QUAN LY SAN PHAM=====================================");
		System.out.println("1. Them san pham moi");
		System.out.println("2. Tinh loi nhuan san pham");
		System.out.println("3. Hien thi thong tin san pham");
		System.out.println("4. Sap xep san pham");
		System.out.println("5. Cap nhat thong tin san pham");
		System.out.println("6. Cap nhat trang thai san pham");
		System.out.println("7. Quay lai");
		System.out.println("Su lua chon cua ban: ");
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		return choice;
	}

	/**
	 * products are added to list
	 * 
	 * @param products
	 * @param categories
	 */
	public void addProduct(List<Product> products, List<Categories> categories) {
		Product product = new Product();
		Categories category = null;
		Scanner input = new Scanner(System.in);
		product.inputData(input, products);
		System.out.println("Nhap ma danh muc: ");
		int cateId = Integer.parseInt(input.nextLine());
		boolean checkC = false;
		for (Categories c : categories) {
			if (cateId == c.getCatalogId()) {
				category = c;
				checkC = true;
				break;
			}
		}
		if (!checkC) {
			System.out.println("Ma danh muc khong ton tai");
		} else {
			product.setCategories(category);
		}
		boolean checkP = false;
		for (Product p : products) {
			if (product.getProductId().equals(p.getProductId())) {
				checkP = true;
				break;
			}
		}
		if (checkP) {
			System.out.println("Ma san pham da ton tai");
			return;
		}
		products.add(product);
	}

	public int menuProductInfor() {
		System.out.println(
				"=====================================THONG TIN SAN PHAM=====================================");
		System.out.println("1. Hien thi san pham theo danh muc");
		System.out.println("2. Hien thi chi tiet san pham");
		System.out.println("3. Quay lai");
		System.out.println("Su lua chon cua ban: ");
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		return choice;
	}

	/**
	 * show product by category
	 * 
	 * @param categories
	 * @param products
	 */
	public void showProductByCate(List<Categories> categories, List<Product> products) {
		int count1, count2, count3;
		count1 = count2 = count3 = 1;
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getParentId() == 0) {
				System.out.printf("%d.%s\n", count1, categories.get(i).getCatalogName());
				for (Product product : products) {
					if (product.getCategoriesID() == categories.get(i).getCatalogId()) {
						System.out.printf("- %s\n", product.getProductName());
					}
				}
				for (int j = 0; j < categories.size(); j++) {
					if (categories.get(j).getParentId() == categories.get(i).getCatalogId()) {
						System.out.printf("\t%d.%d.%s\n", count1, count2, categories.get(j).getCatalogName());
						for (Product product : products) {
							if (product.getCategoriesID() == categories.get(j).getCatalogId()) {
								System.out.printf("\t - %s\n", product.getProductName());
							}
						}
						for (int k = 0; k < categories.size(); k++) {
							if (categories.get(k).getParentId() == categories.get(j).getCatalogId()) {
								System.out.printf("\t\t%d.%d.%d.%s\n", count1, count2, count3,
										categories.get(k).getCatalogName());
								count3++;
								for (Product product : products) {
									if (product.getCategoriesID() == categories.get(k).getCatalogId()) {
										System.out.printf("\t\t - %s\n", product.getProductName());
									}
								}
							}
						}
						count2++;
					}
				}
				count2 = 1;
				count1++;
			}
		}
	}

	public void showProductDetail(List<Product> products) {
		for (Product p : products) {
			System.out.println("=============CHI TIET SAN PHAM=============");
			System.out.println(p.displayData());
		}
	}

	public int menuSortProduct() {
		System.out
				.println("=====================================SAP XEP SAN PHAM=====================================");
		System.out.println("1. Sap xep san pham theo gia suat tang dan");
		System.out.println("2. Sap xep san pham theo loi nhuan giam dan");
		System.out.println("3. Quay lai");
		System.out.println("Su lua chon cua ban: ");
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		return choice;
	}

	/**
	 * sorting decrease products are classified by profits
	 */
	public void sortProductByExportPrice() {
		Collections.sort(products, new Comparator<Product>() {
			@Override
			public int compare(Product p1, Product p2) {
				// TODO Auto-generated method stub
				return p1.getExportPrice() > p2.getExportPrice() ? 1 : -1;
			}
		});
		for (Product p : products) {
			System.out.println("================THONG TIN SAN PHAM================");
			System.out.println(p.displayData());
		}
	}

	/**
	 * sorting decrease products are classified by profits
	 */
	public void sortProductByProfit() {
		Collections.sort(products, new Comparator<Product>() {
			@Override
			public int compare(Product p1, Product p2) {
				// TODO Auto-generated method stub
				return p1.calProfit() < p2.calProfit() ? 1 : -1;
			}
		});
		for (Product p : products) {
			System.out.println("================THONG TIN SAN PHAM================");
			System.out.println(p.displayData());
		}
	}

	/**
	 * Update information of product
	 * 
	 * @param products:   list of product
	 * @param categories: list of category
	 */
	public void updateProductInfo(List<Product> products, List<Categories> categories) {
		float MIN_INTEREST_RATE = 0.2f;
		Scanner input = new Scanner(System.in);
		System.out.println("Nhap ma san pham ban muon cap nhat");
		boolean flag = false;
		String productId;
		do {
			flag = true;
			System.out.println("Ma san pham: ");
			productId = input.nextLine();
			if (productId.length() != 4) {
				System.out.println("Do dai ma san pham gom 4 ky tu!");
				flag = false;
			}
			if (productId.charAt(0) != 'C') {
				System.out.println("Chu cai bat dau ky tu la 'C'");
				flag = false;
			}

		} while (!flag);
		if (products.size() > 0) {
			boolean isExist = false;
			for (Product product : products) {
				if (productId.equals(product.getProductId())) {
					product.setProductId(productId);
					isExist = true;
					boolean flag1 = false;
					String productName;
					do {
						flag1 = true;
						System.out.println("Ten san pham: ");
						productName = input.nextLine();
						if (productName.length() < 6 || productName.length() > 50) {
							flag1 = false;
							System.out.println("Ten san pham phai tu 6-50 ky tu");
						}
						for (Product p : products) {
							if (p.getProductName().equals(productName)) {
								System.out.println("Ten san pham da ton tai");
								flag1 = false;
								break;
							}
						}
						if (flag1) {
							product.setProductName(productName);
							;
						}
					} while (!flag1);
					boolean flag2 = false;
					String title;
					do {
						flag2 = true;
						System.out.println("Tieu de san pham: ");
						title = input.nextLine();
						if (title.length() < 6 || title.length() > 30) {
							flag2 = false;
							System.out.println("Tieu de san pham gom tu 6-30 ky tu");
						}
						if (flag2) {
							product.setTitle(title);
						}
					} while (!flag2);
					boolean flag3 = false;
					float importPrice = 0;
					do {
						flag3 = true;
						try {
							System.out.println("Gia nhap san pham: ");
							importPrice = Float.parseFloat(input.nextLine());
							if (importPrice < 0) {
								flag3 = false;
								System.out.println("Gia nhap san pham phai lon hon 0");
							}
						} catch (Exception e) {
							System.out.println("Yeu cau nhap so!");
						}
						if (flag3) {
							product.setImportPrice(importPrice);
						}
					} while (!flag3);

					boolean flag4 = false;
					float exportPrice = 0;
					do {
						flag4 = true;
						try {
							System.out.println("Gia ban san pham: ");
							exportPrice = Float.parseFloat(input.nextLine());
							if (exportPrice <= (MIN_INTEREST_RATE * importPrice + importPrice)) {
								System.out.printf(
										"Yeu cau gia ban san pham co gia tri lon hon gia ban it nhat la %f lan\n",
										MIN_INTEREST_RATE);
								flag4 = false;
							}
						} catch (Exception e) {
							System.out.println("Yeu cau nhap so!");
						}
						if (flag4) {
							product.setExportPrice(exportPrice);
						}
					} while (!flag4);
					boolean flag5 = false;
					String descriptions;
					do {
						flag5 = true;
						System.out.println("Mo ta san pham");
						descriptions = input.nextLine();
						if (descriptions.length() <= 0) {
							flag5 = false;
							System.out.println("Mo ta khong duoc de trong");
						}
						if (flag5) {
							product.setDescriptions(descriptions);
						}
					} while (!flag5);

					boolean flag6 = false;
					boolean productStatus;
					do {
						System.out.println("Trang thai san pham");
						String status = input.nextLine();
						if (status.equals("true") || status.equals("false")) {
							flag6 = true;
							productStatus = Boolean.parseBoolean(status);
							product.setProductStatus(productStatus);
						} else {
							System.out.println("Trang thai san pham chi duoc viet 'true' hoac 'false'");
						}
					} while (!flag6);
					System.out.println("Nhap ma danh muc: ");
					Categories category = null;
					int cateId = Integer.parseInt(input.nextLine());
					boolean checkC = false;
					for (Categories c : categories) {
						if (cateId == c.getCatalogId()) {
							category = c;
							checkC = true;
							break;
						}
					}
					if (!checkC) {
						System.out.println("Ma danh muc khong ton tai");
						return;
					} else {
						product.setCategories(category);
					}
					break;
				}
			}
			if (!isExist) {
				System.out.println("Ma san pham khong ton tai trong danh sach");
			}
		} else {
			System.out.println("Danh sach san pham trong");
		}

	}

	/**
	 * Update status product
	 * 
	 * @param products: list of Product
	 */
	public void updateStatusProduct(List<Product> products) {
		Scanner input = new Scanner(System.in);
		System.out.println("Nhap ma san pham ban muon cap nhat trang thai");
		boolean flag = false;
		String productId;
		do {
			flag = true;
			System.out.println("Ma san pham: ");
			productId = input.nextLine();
			if (productId.length() != 4) {
				System.out.println("Do dai ma san pham gom 4 ky tu!");
				flag = false;
			}
			if (productId.charAt(0) != 'C') {
				System.out.println("Chu cai bat dau ky tu la 'C'");
				flag = false;
			}
		} while (!flag);
		if (products.size() > 0) {
			for (Product product : products) {
				if (productId.equals(product.getProductId())) {
					product.setProductId(productId);
					boolean flag6 = false;
					boolean productStatus;
					do {
						System.out.println("Trang thai san pham");
						String status = input.nextLine();
						if (status.equals("true") || status.equals("false")) {
							flag6 = true;
							productStatus = Boolean.parseBoolean(status);
							product.setProductStatus(productStatus);
						} else {
							System.out.println("Trang thai san pham chi duoc viet 'true' hoac 'false'");
						}
					} while (!flag6);
				}
			}
		}
	}

}
