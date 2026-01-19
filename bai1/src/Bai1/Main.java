/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bai1;

/**
 *
 * @author 84337
 */
import static Bai1.Main.menu;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Book> bookList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            menu();
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    deleteBook();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    showBooks();
                    break;
                case 5:
                    timSachLapTrinh();
                    break;
                case 6:
                    timSachTheoGia();
                    break;
                case 7:
                    timSachTheoTacGia();
                    break;
                case 0:
                    System.out.println("Exit program!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    static void menu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1. Them sach");
        System.out.println("2. Xoa sach");
        System.out.println("3. Sua sach");
        System.out.println("4. Xuat tat ca sach");
        System.out.println("5. Tim sach chua 'Lap trinh'");
        System.out.println("6. Tim K sach co gia <= P");
        System.out.println("7. Tim sach theo tac gia");
        System.out.println("0. Thoat");
        System.out.print("Chon: ");
    }

    static void addBook() {
        System.out.print("Ma sach: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Ten sach: ");
        String title = sc.nextLine();
        System.out.print("Tac gia: ");
        String author = sc.nextLine();
        System.out.print("Gia: ");
        double price = Double.parseDouble(sc.nextLine());

        bookList.add(new Book(id, title, author, price));
        System.out.println("Đã thêm sách!");
    }

    static void deleteBook() {
        System.out.print("Nhập mã sách cần xóa: ");
        int id = Integer.parseInt(sc.nextLine());

        bookList.removeIf(b -> b.getId() == id);
        System.out.println("Đã xóa (nếu tồn tại).");
    }

    static void updateBook() {
        System.out.print("Nhập mã sách cần sửa: ");
        int id = Integer.parseInt(sc.nextLine());

        for (Book b : bookList) {
            if (b.getId() == id) {
                System.out.print("Tên mới: ");
                b.setTitle(sc.nextLine());
                System.out.print("Tác giả mới: ");
                b.setAuthor(sc.nextLine());
                System.out.print("Giá mới: ");
                b.setPrice(Double.parseDouble(sc.nextLine()));
                System.out.println("Đã cập nhật!");
                return;
            }
        }
        System.out.println("Không tìm thấy sách!");
    }

    static void showBooks() {
        if (bookList.isEmpty()) {
            System.out.println("Khong co sach!");
            return;
        }

        for (Book b : bookList) {
            System.out.println(b);
        }
        
    }

    static void timSachLapTrinh() {
        for (Book b : bookList) {
            if (b.getTitle().toLowerCase().contains("lập trình")) {
                System.out.println(b);
            }
        }
    }

    static void timSachTheoGia() {
        System.out.print("Nhập K: ");
        int K = Integer.parseInt(sc.nextLine());
        System.out.print("Nhập P: ");
        double P = Double.parseDouble(sc.nextLine());

        int dem = 0;
        for (Book b : bookList) {
            if (b.getPrice() <= P && dem < K) {
                System.out.println(b);
                dem++;
            }
        }
    }

    static void timSachTheoTacGia() {
        System.out.print("Nhập các tác giả (cách nhau bởi dấu phẩy): ");
        String[] tg = sc.nextLine().split(",");

        for (Book b : bookList) {
            for (String t : tg) {
                if (b.getAuthor().equalsIgnoreCase(t.trim())) {
                    System.out.println(b);
                }
            }
        }
    }
}
