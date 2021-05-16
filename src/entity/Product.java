package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Product implements IProduct, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productId;// Ma san pham gom 4 ky tu bat dau bang "C" va la duy nhat
	private String productName;// Ten san pham gom 6-50 ky tu va duy nhat
	private String title;// Tieu de san pham tu 6-30 ky tu
	private float importPrice;// so thuc lon hon 0
	private float exportPrice;// gia ban san pham la so thuc va co gia tri lon hon gia ban it nhat
								// MIN_INTEREST_RATE lan
	private float profit;// loi nhuan tinh theo profit = exportPrice - importPrice
	private String descriptions;// mo ta san pham - ko duoc de trong
	private boolean productStatus;// khi nhap chi nhan true hoac false
	private Categories categories;// danh muc san pham cua tat ca san pham

	public Product() {

	}

	public Product(String productId, String productName, String title, float importPrice, float exportPrice,
			String descriptions, boolean productStatus) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.title = title;
		this.importPrice = importPrice;
		this.exportPrice = exportPrice;
		this.descriptions = descriptions;
		this.productStatus = productStatus;

	}

	public float getExportPrice() {
		return exportPrice;
	}

	public void setExportPrice(float exportPrice) {
		this.exportPrice = exportPrice;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getImportPrice() {
		return importPrice;
	}

	public void setImportPrice(float importPrice) {
		this.importPrice = importPrice;
	}

	public float getProfit() {
		return profit;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public boolean isProductStatus() {
		return productStatus;
	}

	public void setProductStatus(boolean productStatus) {
		this.productStatus = productStatus;
	}

	public Categories getCategories() {
		return categories;
	}

	public int getCategoriesID() {
		return categories.getCatalogId();
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	@Override
	public void inputData(Scanner input, List<Product> products) {
		boolean flag = false;
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
			for (Product p : products) {
				if (p.getProductId().equals(productId)) {
					System.out.println("Ma san pham da ton tai");
					flag = false;
					break;
				}
			}
		} while (!flag);
		boolean flag1 = false;
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
		} while (!flag1);
		boolean flag2 = false;
		do {
			flag2 = true;
			System.out.println("Tieu de san pham: ");
			title = input.nextLine();
			if (title.length() < 6 || title.length() > 30) {
				flag2 = false;
				System.out.println("Tieu de san pham gom tu 6-30 ky tu");
			}
		} while (!flag2);

		boolean flag3 = false;
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
		} while (!flag3);

		boolean flag4 = false;
		do {
			flag4 = true;
			try {
				System.out.println("Gia ban san pham: ");
				exportPrice = Float.parseFloat(input.nextLine());
				if (exportPrice <= (MIN_INTEREST_RATE * importPrice + importPrice)) {
					System.out.printf("Yeu cau gia ban san pham co gia tri lon hon gia ban it nhat la %f lan\n",
							MIN_INTEREST_RATE);
					flag4 = false;
				}
			} catch (Exception e) {
				System.out.println("Yeu cau nhap so!");
			}
		} while (!flag4);
		boolean flag5 = false;
		do {
			flag5 = true;
			System.out.println("Mo ta san pham");
			descriptions = input.nextLine();
			if (descriptions.length() <= 0) {
				flag5 = false;
				System.out.println("Mo ta khong duoc de trong");
			}
		} while (!flag5);

		boolean flag6 = false;
		do {
			System.out.println("Trang thai san pham");
			String status = input.nextLine();
			if (status.equals("true") || status.equals("false")) {
				flag6 = true;
				productStatus = Boolean.parseBoolean(status);
			} else {
				System.out.println("Trang thai san pham chi duoc viet 'true' hoac 'false'");
			}
		} while (!flag6);
	}

	@Override
	public String displayData() {
		String proStatus;
		if (productStatus) {
			proStatus = "Hoat dong";
		} else {
			proStatus = "Khong hoat dong";
		}
		return "- Ma san pham: " + this.productId + "\n" + " - Ten san pham: " + this.productName + "\n"
				+ " - Tieu de san pham: " + this.title + "\n" + " - Gia nhap san pham: " + this.importPrice + "\n"
				+ " - Gia ban san pham: " + this.exportPrice + "\n" + " - Loi nhuan san pham: " + this.calProfit()
				+ "\n" + " - Mo ta san pham: " + this.descriptions + "\n" + " - Trang thai san pham: " + proStatus
				+ "\n" + " - Ma danh muc: " + this.categories.getCatalogId();
	}

	@Override
	public float calProfit() {
		return profit = exportPrice - importPrice;
	}

}
