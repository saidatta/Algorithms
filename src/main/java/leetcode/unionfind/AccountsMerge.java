package leetcode.unionfind;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/accounts-merge/description/
class AccountsMerge {
    // Main function to merge accounts
    public List<List<String>> accountsMerge(List<List<String>> accountList) {
        int accountListSize = accountList.size();
        DSU dsu = new DSU(accountListSize);
        Map<String, Integer> emailGroup = mapEmailsToGroups(accountList, dsu);
        Map<Integer, List<String>> components = buildComponents(emailGroup, dsu);

        return constructMergedAccounts(accountList, components);
    }

    /**
     * Maps each email to a unique group identifier and merges accounts that have a shared email.
     *
     * @param accountList List of accounts where each account is represented by a list of strings.
     *                    The first string in the list is the account name, followed by its associated emails.
     * @param dsu The Disjoint Set Union (DSU) instance used for merging accounts.
     * @return A mapping of each email to its associated group identifier.
     */
    private Map<String, Integer> mapEmailsToGroups(List<List<String>> accountList, DSU dsu) {
        Map<String, Integer> emailToGroupMapping = new HashMap<>();

        for (int accountId = 0; accountId < accountList.size(); accountId++) {
            List<String> accountDetails = accountList.get(accountId);

            // Starting from index 1 since index 0 contains account name.
            for (int emailIndex = 1; emailIndex < accountDetails.size(); emailIndex++) {
                String email = accountDetails.get(emailIndex);

                if (!emailToGroupMapping.containsKey(email)) {
                    emailToGroupMapping.put(email, accountId);
                } else {
                    int previouslyMappedAccountId = emailToGroupMapping.get(email);
                    dsu.unionBySize(accountId, previouslyMappedAccountId);
                }
            }
        }

        return emailToGroupMapping;
    }


    /**
     * Build components based on email groupings, with each component representing a unique set of emails.
     *
     * @param emailGroup Mapping of email to its group identifier.
     * @param dsu The Disjoint Set Union (DSU) instance used for finding representatives.
     * @return A mapping of group identifiers to their list of associated emails.
     */
    private Map<Integer, List<String>> buildComponents(Map<String, Integer> emailGroup, DSU dsu) {
        Map<Integer, List<String>> components = new HashMap<>();

        for (String email : emailGroup.keySet()) {
            int accountId = dsu.findRepresentative(emailGroup.get(email));

            // If the group representative is not present in components, create a new list for it.
            // Then, add the email to the associated list.
            components
                    .computeIfAbsent(accountId, unusedKey -> new ArrayList<>())
                    .add(email);
        }

        return components;
    }

    /**
     * Construct the final merged accounts by combining associated emails and account names.
     *
     * @param accountList Original list of accounts.
     * @param components Mapping of group identifiers to their list of associated emails.
     * @return A list of merged accounts, where each account is represented by its name followed by its associated emails.
     */
    private List<List<String>> constructMergedAccounts(List<List<String>> accountList, Map<Integer, List<String>> components) {
        List<List<String>> mergedAccounts = new ArrayList<>();

        for (int group : components.keySet()) {
            List<String> emailsInGroup = new ArrayList<>(components.get(group));
            Collections.sort(emailsInGroup);

            // Add account name at the beginning of the list.
            String accountName = accountList.get(group).get(0);
            emailsInGroup.add(0, accountName);

            mergedAccounts.add(emailsInGroup);
        }

        return mergedAccounts;
    }


    public static void main(String[] args) {
        // Sample accounts for testing
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));

        // Create an object of the AccountsMerge class
        AccountsMerge solution = new AccountsMerge();

        // Get the merged accounts
        List<List<String>> mergedAccounts = solution.accountsMerge(accounts);

        // Print the merged accounts
        for (List<String> account : mergedAccounts) {
            System.out.println(account);
        }
    }

}

/**
 * DSU (Disjoint Set Union) is a data structure used to manage
 * a collection of disjoint sets. It supports two primary operations:
 * - finding the representative of a set
 * - merging two sets together
 */
final class DSU {

    private int[] representative;
    private int[] size;

    /**
     * Constructs a new DSU with a given size.
     * Initially, every element is its own representative and the size of each set is 1.
     *
     * @param sz The number of elements in the DSU.
     */
    DSU(int sz) {
        representative = new int[sz];
        size = new int[sz];

        for (int i = 0; i < sz; i++) {
            representative[i] = i;
            size[i] = 1;
        }
    }

    /**
     * Finds the representative of the set that an element belongs to.
     *
     * @param x The element whose representative is to be found.
     * @return The representative of the set that x belongs to.
     */
    public int findRepresentative(int x) {
        // If x is its own representative, return it
        if (x == representative[x]) {
            return x;
        }

        // Otherwise, find the representative of x's representative
        // This is also path compression: it makes x point directly to its representative
        return representative[x] = findRepresentative(representative[x]);
    }

    /**
     * Merges the sets that contain elements a and b.
     * The smaller set (in terms of size) will be merged into the larger set.
     *
     * @param a An element from the first set.
     * @param b An element from the second set.
     */
    public void unionBySize(int a, int b) {
        int repA = findRepresentative(a);
        int repB = findRepresentative(b);

        // If a and b are already in the same set, do nothing
        if (repA == repB) {
            return;
        }

        // Merge smaller set into the larger set
        if (size[repA] >= size[repB]) {
            size[repA] += size[repB];
            representative[repB] = repA;
        } else {
            size[repB] += size[repA];
            representative[repA] = repB;
        }
    }
}

//    Explanation Refactor:
//        Problem Summary: Given a list of email accounts, where each account is a list of emails and a name. An account and all of its emails represent a single person. We need to merge the accounts that have any common email address.
//
//        Approach:
//
//        Assign a unique ID to each account and map each email to this ID.
//        If an email has already been mapped to an ID, merge these two accounts using the Disjoint Set Union (DSU) data structure.
//        After mapping, for each email, determine its final group or representative.
//        Combine emails with the same representative and sort them.
//        Complexity Analysis:
//
//        Time Complexity:
//        O
//        (
//        N
//        K
//        log
//        ⁡
//        N
//        K
//        )
//        O(NKlogNK)
//        Merging accounts takes
//        O
//        (
//        N
//        K
//        α
//        (
//        N
//        )
//        )
//        O(NKα(N)).
//        Sorting emails takes
//        O
//        (
//        N
//        K
//        log
//        ⁡
//        N
//        K
//        )
//        O(NKlogNK).
//        Here,
//        α
//        (
//        N
//        )
//        α(N) is the inverse Ackermann function.
//        Space Complexity:
//        O
//        (
//        N
//        K
//        )
//        O(NK)
//        Storage for emails and DSU data structure.
//        Note: The provided space complexity for sorting might differ based on the language's built-in sort implementation. In Java, Collections.sort() internally uses Arrays.sort(), which has a worst-case space complexity of
//        O
//        (
//        log
//        ⁡
//        N
//        K
//        )
//        O(logNK).