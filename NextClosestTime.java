import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * 
 */
public class NextClosestTime {


    /**
     * Given a time represented in the format "HH:MM", 
     * form the next closest time by reusing the current digits. 
     * There is no limit on how many times a digit can be reused.
     * 
     * Runtime: 15 ms, faster than 16.99% of Java online submissions.
     * Memory Usage: 39.6 MB, less than 16.58% of Java online submissions.
     * 
     * 66 / 66 test cases passed.
     * Status: Accepted
     * Runtime: 15 ms
     * Memory Usage: 39.6 MB
     */
    static public String nextClosestTime0(String time) {

        // **** minutes in a day ****
        final var MINS_IN_DAY = 1440;

        // **** initialization ****
        var minutes = Integer.parseInt(time.substring(0, 2)) * 60;
        minutes += Integer.parseInt(time.substring(3));

        // ???? ????
        System.out.println("<<< minutes: " + minutes);

        // **** should use an int[] array ****
        var digits = new int[10];
        for (char c : time.toCharArray()) {

            // **** skip ':' ****
            if (c == ':') continue;

            // **** add this digit to the set ****
            digits[c - '0'] = 1;
        }

        // ???? ????
        System.out.println("<<<  digits: " + Arrays.toString(digits));

        // **** loop until we find a valied time ****
        var isValid = false;
        while (!isValid) {

            // **** move to the next minute or next day ****
            minutes = minutes + 1 == MINS_IN_DAY ? 0 : minutes + 1;

            // ??? ????
            // System.out.println("<<< minutes: " + minutes);

            // **** for ease of use ****
            int[] nextTime = {  minutes / 60 / 10,
                                minutes / 60 % 10,
                                minutes % 60 / 10,
                                minutes % 60 % 10};

            // ???? ????
            // System.out.println("<<< nextTime: " + Arrays.toString(nextTime));

            // **** is this a valid time ****
            if (digits[nextTime[0]] == 1 &&
                digits[nextTime[1]] == 1 &&
                digits[nextTime[2]] == 1 &&
                digits[nextTime[3]] == 1)
                isValid = true;
        }

        // **** return ****          
        return String.format("%02d:%02d", minutes / 60, minutes % 60);
    }


    /**
     * Given a time represented in the format "HH:MM", 
     * form the next closest time by reusing the current digits. 
     * There is no limit on how many times a digit can be reused.
     * 
     * Runtime: O(K) - Space: O(1)
     * 
     * Runtime: 10 ms, faster than 33.91% of Java online submissions.
     * Memory Usage: 39.1 MB, less than 34.11% of Java online submissions.
     * 
     * 66 / 66 test cases passed.
     * Status: Accepted
     * Runtime: 10 ms
     * Memory Usage: 39.1 MB
     */
    static public String nextClosestTime(String time) {
        
        // **** constant ****
        final int MINS_IN_DAY   = 1440;     // 24 * 60

        // **** initialization ****
        int[] digits            = new int[10];
        int hh                  = 0;
        int mm                  = 0;
        boolean inHours         = true;

        // **** initialize digits, hh and mm - O(n) ****
        for (char c : time.toCharArray()) {

            // **** skip ':' ****
            if (c == ':') {
                inHours = false;
                continue;
            }
            
            // **** add this digit to t ****
            if (inHours)
                hh = hh * 10 + (c - '0');
            else mm = mm * 10 + (c - '0');

            // **** flag c is in use ****
            digits[c - '0'] = 1;
        }

        // ???? ????
        System.out.println("<<< MINS_IN_DAY: " + MINS_IN_DAY);
        System.out.println("<<<      digits: " + Arrays.toString(digits));
        System.out.println("<<<       hh:mm: " + hh + ":" + mm);

        // **** loop incrementing time by 1 minute testing if done - O(K) ****
        for (int i = 0; i < MINS_IN_DAY; i++) {

            // **** increment time by 1 minute ****
            mm++;

            // **** update HH and MM (as needed) ****
            if (mm == 60 ) {

                // **** start of next hour ****
                mm = 0;
                hh++;

                // **** start of next day ****
                if (hh >= 24)
                    hh = 0;
            }

            // **** check if all digits in hh are in digits[]  ****
            if (hh < 10) {
                if (digits[hh] == 0) continue;
                if (digits[0] == 0) continue;
            } else {
                if (digits[hh / 10] == 0) continue;
                if (digits[hh % 10] == 0) continue;
            }

            // **** check if digits in mm are in digits[] ****
            if (mm < 10) {
                if (digits[mm] == 0) continue;
                if (digits[0] == 0) continue;
            } else {
                if (digits[mm / 10] == 0) continue;
                if (digits[mm % 10] == 0) continue;
            }

            // **** this is the closest time ****
            break;
        }

        // **** return next closest time ****
        return String.format("%02d:%02d", hh, mm);
    }

    
    /**
     * Given a time represented in the format "HH:MM", 
     * form the next closest time by reusing the current digits. 
     * There is no limit on how many times a digit can be reused.
     */
    static public String nextClosestTime1(String time) {

        // **** initialization ****
        var arr = time.toCharArray();

        // ???? ????
        // System.out.println("<<<    arr: " + new String(arr));
        
        // **** ****
        char min = '9';
        int[] digits = new int[10];
        for (char c : arr) {
            if (c == ':') continue;
            if (c < min) min = c;
            digits[c - '0']++;
        }

        // ???? ????
        // System.out.println("<<<    min: " + min);
        System.out.println("<<< digits: " + Arrays.toString(digits));
        
        // **** increment this digit ****
        for (int i = arr[4] - '0' + 1; i <= 9; i++) {
            if (digits[i] > 0) {

                // **** ****
                arr[4] = (char)('0' + i);

                // ???? ?????
                // System.out.println("<<<    arr: " + new String(arr));

                // **** ****
                return new String(arr);
            }
        }

        // **** ****
        arr[4] = min;

        // ???? ????
        // System.out.println("<<< arr: " + new String(arr));

        // **** ****
        for (int i = arr[3] - '0' + 1; i <= 5; i++) {
            if (digits[i] > 0) {

                // **** ****
                arr[3] = (char)('0' + i);

                // ???? ?????
                // System.out.println("<<<    arr: " + new String(arr));

                // **** ****
                return new String(arr);
            }
        }

        // **** ****
        arr[3] = min;

        // ???? ????
        // System.out.println("<<<    arr: " + new String(arr));
        
        // **** ****
        int stop = arr[0] < '2' ? 9 : 3;

        // ???? ????
        // System.out.println("<<< stop: " + stop);

        // **** ****
        for (int i = arr[1] - '0' + 1; i <= stop; i++) {
            if (digits[i] > 0) {

                // **** ****
                arr[1] = (char)('0' + i);

                // ???? ?????
                // System.out.println("<<<    arr: " + new String(arr));

                // **** ****
                return new String(arr);
            }
        }

        // **** ****
        arr[1] = min;
        
        // ???? ????
        // System.out.println("<<<    arr: " + new String(arr));

        // **** ****
        for (int i = arr[0] - '0' + 1; i <= 2; i++) {
            if (digits[i] > 0) {

                // **** ****
                arr[0] = (char)('0' + i);

                // ???? ?????
                // System.out.println("<<<    arr: " + new String(arr));

                // **** ****
                return new String(arr);
            }
        }
        
        // **** ****
        arr[0] = min;
        
        // ???? ?????
        // System.out.println("<<<    arr: " + new String(arr));

        // **** return next closest time ****
        return new String(arr);
    }


    /**
     * Test scaffold.
     * !!! NOT PART OF THE SOLUTION !!!
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read time ****
        String time = br.readLine().trim();

        // **** close bufferered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<< time ==>" + time + "<==");

        // **** call function of interest and display output ****
        System.out.println("main <<< nextClosestTime0: " + nextClosestTime0(time));

        // **** call function of interest and display output ****
        System.out.println("main <<<  nextClosestTime: " + nextClosestTime(time));
    }
}