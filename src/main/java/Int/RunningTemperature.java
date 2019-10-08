package Int;

/**
 * 	// Given a Timeseries that keeps information about Temperature readings for a city,
 // return a Timeseries that tells you, for a given day,
 // how long has its value been the largest running value.

 // eg: For temperature readings        [30,50,60,20,10,40,60,90],

 //[30,20,60,20,10,40,60,90],

 //[1,1,3,1,1,3,7,8]

 // the transformed timeseries would be [ 1, 2, 3, 1, 1, 3, 7, 8]
 *
 * Created by venkatamunnangi on 10/3/19.
 */
public class RunningTemperature {

    int[] longestRunning(int[] input){
        if(input.length==0)
            return null;
        int[] cache = new int[input.length];
        cache[0]=1;
        //[30,20,60,20,10,40,60,90]
        //[1,1,3,1,1,3,7,8]
        for(int i = 1;i<input.length;i++){
            cache[i] =1;
            int j=i-1;
            while(j>=0 && input[j]<=input[i]){
                cache[i]+=cache[j];
                j=j-cache[j];
            }
        }

        return cache;

    }

    public static void main (String[] args) {
        RunningTemperature m = new RunningTemperature();
        int[] input = {30,50,60,20,10,40,60,90};
        for(int c: m.longestRunning(input))
            System.out.print(c+" ");
        System.out.println("");
    }
}
