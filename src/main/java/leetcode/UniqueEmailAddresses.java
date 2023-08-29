package leetcode;

import java.util.HashSet;
import java.util.Set;

public class UniqueEmailAddresses {
    public int numUniqueEmails(String... emails) {
        if(emails == null || emails.length == 0) {
            return 0;
        }

        Set<String> uniqueEmails = new HashSet<>();

        for (String email : emails) {
            String [] elements = email.split("@");
            String localName = elements[0];
            localName = localName.substring(0, localName.indexOf("+")).replaceAll(".","");
            uniqueEmails.add(localName+"@"+elements[1]);
        }

        return uniqueEmails.size();
    }

    public int numUniqueEmails2(String[] emails) {
        Set<String> emailsSet = new HashSet<>();

        for (String email : emails) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < email.length(); i++) {
                if (email.charAt(i) == '.') {
                    continue;
                } else if (email.charAt(i) == '+') {
                    int idx = email.length() - 1;

                    while (email.charAt(idx) != '@') {
                        idx--;
                    }

                    sb.append(email.substring(idx));
                    break;
                } else {
                    sb.append(email.charAt(i));
                }
            }
            emailsSet.add(sb.toString());
        }

        return emailsSet.size();
    }

    public static void main(String... args) {
        UniqueEmailAddresses uniqueEmails = new UniqueEmailAddresses();

        System.out.println(uniqueEmails.numUniqueEmails("test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"));

    }
}
