package mn.edu.must.sqat;

public class GradeCalculator {

    public String letterGrade(double score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("score must be between 0 and 100: " + score);
        }
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    public double totalScore(double att, double lab, double quiz1, double quiz2, double exam) {
        checkRange("att", att, 10);
        checkRange("lab", lab, 40);
        checkRange("quiz1", quiz1, 10);
        checkRange("quiz2", quiz2, 10);
        checkRange("exam", exam, 30);
        return att + lab + quiz1 + quiz2 + exam;
    }

    private void checkRange(String name, double value, double max) {
        if (value < 0 || value > max) {
            throw new IllegalArgumentException(name + " must be between 0 and " + max + ": " + value);
        }
    }
}