package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Categories implements ICategories, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int catalogId;
	private String catalogName;
	private String descriptions;
	private boolean catalogStatus;
	private int parentId = 0;

	public Categories() {
		super();
	}

	public Categories(int catalogId, String catalogName, String descriptions, boolean catalogStatus, int parentId) {
		super();
		this.catalogId = catalogId;
		this.catalogName = catalogName;
		this.descriptions = descriptions;
		this.catalogStatus = catalogStatus;
		this.parentId = parentId;
	}

	public int getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public boolean isCatalogStatus() {
		return catalogStatus;
	}

	public void setCatalogStatus(boolean catalogStatus) {
		this.catalogStatus = catalogStatus;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	@Override
	public void inputData(Scanner input, List<Categories> categories) {
		boolean flag = false;
		do {
			flag = true;
			try {
				System.out.println("Nhap ma danh muc ");
				catalogId = input.nextInt();
				input.nextLine();
				if (catalogId <= 0) {
					System.out.println("Ma danh muc phai lon hon 0");
					flag = false;
				}
				for (Categories c : categories) {
					if (c.getCatalogId() == catalogId) {
						System.out.println("Ma danh muc da ton tai");
						flag = false;
						break;
					}
				}
			} catch (NullPointerException e) {
				System.out.println("Ma danh muc phai la so nguyen");
				e.printStackTrace();
				flag = false;
			}
		} while (!flag);
		boolean flag1 = false;
		do {
			flag1 = true;
			System.out.println("Nhap ten danh muc");
			catalogName = input.nextLine();
			if (catalogName.length() < 6 || catalogName.length() > 30) {
				System.out.println("Ten danh muc phai tu 6-30 ky tu");
				flag1 = false;
			}
		} while (!flag1);

		boolean flag2 = false;
		do {
			flag2 = true;
			System.out.println("Nhap mo ta danh muc");
			descriptions = input.nextLine();
			if (descriptions.isEmpty()) {
				System.out.println("Mo ta khong duoc de trong khi nhap");
				flag2 = false;
			}
		} while (!flag2);
		boolean flag3 = false;
		do {
			System.out.println("Nhap trang thai danh muc");
			String caStatus = input.nextLine();
			if (caStatus.equals("true") || caStatus.equals("false")) {
				catalogStatus = Boolean.parseBoolean(caStatus);
				flag3 = true;
			}
		} while (!flag3);
		boolean flag4 = false;
		do {
			flag4 = true;
			try {
				System.out.println("Nhap ma danh muc cha: ");
				parentId = input.nextInt();
				input.nextLine();
				if (categories.size() == 0 && parentId > 0) {
					System.out.println("Ma cha goc la 0");
					flag4 = false;
				}
			} catch (Exception e) {
				System.out.println("Ma danh muc cha phai la so nguyen");
				System.out.println(e);
			}
		} while (!flag4);
	}

	@Override
	public String displayData() {
		String status;
		if(catalogStatus) {
			status = "Hoat Dong";
		}else {
			status = "Khong Hoat Dong";
		}
		return "Ma danh muc: " + this.catalogId + " - Ten danh muc: " + this.catalogName + "\n" + "Mo ta: "
				+ this.descriptions + "\n" + "Danh muc cha: " + this.parentId + " - Trang thai: " + status;
	}
}
