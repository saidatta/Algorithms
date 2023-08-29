package leetcode.string;

/**
 * https://leetcode.com/problems/student-attendance-record-i/#/description
 *
 * Created by venkatamunnangi on 5/15/17.
 */
public class StudentAttendanceRecordI {

    public boolean checkRecordRegex(String s) {
        return !s.matches(".*LLL.*|.*A.*A.*");
    }

    public boolean checkRecord(String s) {
        if(s == null || s.isEmpty()) {
            return true;
        }


        int n = s.length();
        char[] cs = s.toCharArray();
        int numberOfAbsentee = 0;
        boolean hasTooManyLeaves = false;

        if(n < 3) {
            return s.equals("AA");
        }

        for(int i = 0; i<n-2;i++) {
            boolean isMultipleLeave = cs[i] == 'L' && cs[i+1] == 'L' && cs[i+2] == 'L';

            if(isMultipleLeave) {
                hasTooManyLeaves = true;
            }

            if(cs[i] == 'A') {
                numberOfAbsentee++;
            }

            if(i == n -3) {
                if(cs[n-2] == 'A') {
                    numberOfAbsentee++;
                }
                if(cs[n-1] == 'A') {
                    numberOfAbsentee++;
                }
            }
        }



        return !hasTooManyLeaves && numberOfAbsentee < 2;
    }

    public static void main(String... args) {
        StudentAttendanceRecordI studentAttendanceRecordI = new StudentAttendanceRecordI();
        System.out.println(studentAttendanceRecordI.checkRecordRegex("PPALLP")); // True
        System.out.println(studentAttendanceRecordI.checkRecordRegex("PPALLL")); // false
    }
}
