package Leetcode.String;

/**
 * https://leetcode.com/problems/defanging-an-ip-address/
 */
public class DefangingIPAddress {
    public String defangIPaddr(String address) {
        StringBuilder sb= new StringBuilder();
        for(int i=0; i<address.length(); i++){
            if(address.charAt(i)=='.'){
                sb.append("[.]");
            }else{
                sb.append(address.charAt(i));
            }
        }
        return sb.toString();
    }
}
