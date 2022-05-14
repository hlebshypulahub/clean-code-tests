package com.b.simple.design.business.student;

public class StudentHelper {
    public static final int GRADE_B_LOWER_LIMIT = 51;
    public static final int GRADE_B_UPPER_LIMIT = 80;
    public static final int GRADE_A_LOWER_LIMIT = 91;
    public static final int INSUFFICIENT_LIMIT = 20;
    public static final int IF_MATH_LIMIT_INCREMENT_10 = 10;
    public static final int IF_MATH_LIMIT_INCREMENT_5 = 5;

    /* PROBLEM 1 */
    /*
     * You get a grade B if marks are between 51 and 80 (both inclusive). Except for Maths where the upper limit is increased by 10.
     */
//    public boolean isGradeB(int marks, boolean isMaths) {
//		return isMaths ? marks>=51 && marks<=90 : marks>=51 && marks<=80;
//    }

    public boolean isGradeB(int marks, boolean isMaths) {
        int upperLimitIncrement = isMaths ? IF_MATH_LIMIT_INCREMENT_10 : 0;
        int upperLimit = GRADE_B_UPPER_LIMIT + upperLimitIncrement;

        return marks >= GRADE_B_LOWER_LIMIT && marks <= upperLimit;
    }

    /* PROBLEM 2 */
	/*
	You are awarded a grade based on your marks.
	Grade A = 91 to 100, Grade B = 51 to 90, Otherwise Grade C
	Except for Maths where marks to get a Grade are 5 higher than required for other subjects.
	*/
//    public String getGrade(int mark, boolean isMaths) {
//        String grade = "C";
//
//        if (isGradeA(mark, isMaths))
//            grade = "A";
//        else if (isBGrade(mark, isMaths)) {
//            grade = "B";
//        }
//        return grade;
//    }
//
//    private boolean isGradeA(int mark, boolean isMaths) {
//        int lowerLimitForAGrade = isMaths ? 95
//                : 90;
//        return mark > lowerLimitForAGrade;
//    }
//
//    private boolean isBGrade(int mark, boolean isMaths) {
//        int lowerLimitGradeB = isMaths ? 55
//                : 50;
//        return mark > lowerLimitGradeB && mark < 90;
//    }

    public String getGrade(int mark, boolean isMaths) {
        int limitIncrement = isMaths ? IF_MATH_LIMIT_INCREMENT_5 : 0;

        int lowerLimitA = GRADE_A_LOWER_LIMIT + limitIncrement;
        if (mark >= lowerLimitA) {
            return Mark.A.name();
        }

        int lowerLimitB = GRADE_B_LOWER_LIMIT + limitIncrement;
        if (mark >= lowerLimitB) {
            return Mark.B.name();
        }

        return Mark.C.name();
    }

    /// We can update the above method to return an enum and tests to expect an enum,
    /// but now I left it as String representation of enum
    enum Mark {
        A, B, C
    }

    /*  PROBLEM 3
     * You and your Friend are planning to enter a Subject Quiz.
     * However, there is a marks' requirement that you should attain to qualify.
     *
     * Return value can be YES, NO or MAYBE.
     *
     * YES If either of you are very good at the subject(has 80 or more marks)
     * However, there is an exception that if either of you is not good in the subject(20 or fewer marks), it is NO.
     * In all other conditions, return MAYBE.
     *
     * However, the definition for good and not good are 5 marks higher if the subject is Mathematics.
     *
     * marks1 - your marks
     * marks2 - your friends marks
     */
//    public String willQualifyForQuiz(int marks1, int marks2, boolean isMaths) {
//        if ((isMaths ? marks1 <= 25 : marks1 <= 20)
//                || (isMaths ? marks2 <= 25 : marks2 <= 20)) return "NO";
//        if ((isMaths ? marks1 >= 85 : marks1 >= 80)
//                || (isMaths ? marks2 >= 85 : marks2 >= 80)) return "YES";
//        return "MAYBE";
//    }

    public String willQualifyForQuiz(int marks1, int marks2, boolean isMaths) {
        int upperLimit = recalcLimit(isMaths, GRADE_B_UPPER_LIMIT);
        int lowerLimit = recalcLimit(isMaths, INSUFFICIENT_LIMIT);

        if (marks1 >= upperLimit || marks2 >= upperLimit) {
            return Answer.YES.name();
        }
        if (marks1 <= lowerLimit || marks2 <= lowerLimit) {
            return Answer.NO.name();
        }

        return Answer.MAYBE.name();
    }

    private int recalcLimit(boolean isMaths, int limit) {
        int limitIncrement = isMaths ? IF_MATH_LIMIT_INCREMENT_5 : 0;
        return limit + limitIncrement;
    }

    enum Answer {
        YES, NO, MAYBE
    }
}