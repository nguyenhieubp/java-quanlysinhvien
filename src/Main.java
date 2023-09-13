import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    private ArrayList<Student> studentList = new ArrayList<>();

    public void addStudent(Student student) {
        studentList.add(student);
    }

    public boolean isEmpty() {
        return studentList.isEmpty();
    }

    public void removeStudent(String studentID) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentID().equals(studentID)) {
                studentList.remove(i);
                System.out.println("Sinh viên có mã " + studentID + " đã được xóa.");
                return;
            }
        }
        System.out.println("Không tìm thấy sinh viên có mã " + studentID);
    }

    public void displayStudentsByScoreDescending() {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s2.getScore(), s1.getScore());
            }
        });

        System.out.println("Danh sách sinh viên theo điểm giảm dần:");
        System.out.println("---------------------------------------------------------------------");
        System.out.printf("%-20s | %-10s | %-15s | %-5s%n", "Họ tên", "Năm sinh", "Mã sinh viên", "Điểm");
        System.out.println("---------------------------------------------------------------------");
        for (Student student : studentList) {
            System.out.printf("%-20s | %-10d | %-15s | %-5.2f%n", student.getFullName(), student.getBirthYear(), student.getStudentID(), student.getScore());
        }
        System.out.println("---------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        Main studentManagement = new Main();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addNewStudent(studentManagement, scanner);
                        break;
                    case 2:
                        checkIfListIsEmpty(studentManagement);
                        break;
                    case 3:
                        searchStudent(studentManagement, scanner);
                        break;
                    case 4:
                        removeStudentFromList(studentManagement, scanner);
                        break;
                    case 5:
                        studentManagement.displayStudentsByScoreDescending();
                        break;
                    case 6:
                        exitProgram();
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập số.");
                scanner.next();
            }
        }
    }

    public static void displayMenu() {
        System.out.println("Chọn chức năng:");
        System.out.println("1. Thêm sinh viên mới");
        System.out.println("2. Kiểm tra danh sách sinh viên rỗng");
        System.out.println("3. Tìm kiếm sinh viên theo mã hoặc tên");
        System.out.println("4. Xóa sinh viên theo mã");
        System.out.println("5. Hiển thị danh sách sinh viên theo điểm giảm dần");
        System.out.println("6. Thoát");
        System.out.print("Nhập lựa chọn của bạn: ");
    }

    public static void addNewStudent(Main studentManagement, Scanner scanner) {
        System.out.print("Nhập họ tên: ");
        String fullName = scanner.nextLine();

        int birthYear = -1;
        while (true) {
            System.out.print("Nhập năm sinh: ");
            try {
                birthYear = scanner.nextInt();
                scanner.nextLine();  // Clear the buffer after reading an int
                if (birthYear >= 1900 && birthYear <= java.time.Year.now().getValue()) {
                    break;
                } else {
                    System.out.println("Lỗi: Năm sinh không hợp lệ. Vui lòng nhập lại.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập số.");
                scanner.nextLine();  // Clear the invalid input
            }
        }

        System.out.print("Nhập mã sinh viên: ");
        String studentID = scanner.nextLine();

        double score = -1.0;
        while (true) {
            System.out.print("Nhập điểm: ");
            try {
                score = scanner.nextDouble();
                scanner.nextLine();  // Clear the buffer after reading a double
                if (score >= 0.0 && score <= 10.0) {
                    break;
                } else {
                    System.out.println("Lỗi: Điểm không hợp lệ. Vui lòng nhập lại.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập số.");
                scanner.nextLine();  // Clear the invalid input
            }
        }

        Student newStudent = new Student(fullName, birthYear, studentID, score);
        studentManagement.addStudent(newStudent);
        System.out.println("Sinh viên đã được thêm vào danh sách.");
    }



    public static void checkIfListIsEmpty(Main studentManagement) {
        if (studentManagement.isEmpty()) {
            System.out.println("Danh sách sinh viên rỗng.");
        } else {
            System.out.println("Danh sách sinh viên không rỗng.");
        }
    }

    public static void removeStudentFromList(Main studentManagement, Scanner scanner) {
        System.out.print("Nhập mã sinh viên cần xóa: ");
        String studentID = scanner.nextLine();
        studentManagement.removeStudent(studentID);
    }

    public static void exitProgram() {
        System.out.println("Kết thúc chương trình.");
        System.exit(0);
    }

    public static void searchStudent(Main studentManagement, Scanner scanner) {
        System.out.println("Tìm kiếm theo:");
        System.out.println("1. Mã sinh viên");
        System.out.println("2. Tên sinh viên");
        System.out.print("Nhập lựa chọn của bạn: ");

        int searchOption = scanner.nextInt();
        scanner.nextLine();

        if (searchOption == 1) {
            System.out.print("Nhập mã sinh viên: ");
            String id = scanner.nextLine();
            studentManagement.searchByID(id);
        } else if (searchOption == 2) {
            System.out.print("Nhập tên sinh viên: ");
            String name = scanner.nextLine();
            studentManagement.searchByName(name);
        } else {
            System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    public void searchByID(String studentID) {
        for (Student student : studentList) {
            if (student.getStudentID().equals(studentID)) {
                displayStudent(student);
                return;
            }
        }
        System.out.println("Không tìm thấy sinh viên có mã: " + studentID);
    }

    public void searchByName(String name) {
        boolean found = false;
        for (Student student : studentList) {
            if (student.getFullName().equalsIgnoreCase(name)) {
                displayStudent(student);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy sinh viên có tên: " + name);
        }
    }

    public void displayStudent(Student student) {
        System.out.println("--------------------------");
        System.out.println("Họ tên: " + student.getFullName());
        System.out.println("Năm sinh: " + student.getBirthYear());
        System.out.println("Mã sinh viên: " + student.getStudentID());
        System.out.println("Điểm: " + student.getScore());
        System.out.println("--------------------------");
    }

}