public class Student {
    private String fullName;
    private int birthYear;
    private String studentID;
    private double score;

    public Student(String fullName, int birthYear, String studentID, double score) {
        this.fullName = fullName;
        this.birthYear = birthYear;
        this.studentID = studentID;
        this.score = score;
    }

    public String getFullName() {
        return fullName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getStudentID() {
        return studentID;
    }

    public double getScore() {
        return score;
    }
}
