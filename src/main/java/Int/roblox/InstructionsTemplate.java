package Int.roblox;

import java.util.*;
import java.util.stream.Collectors;

//We need to create a template string that contains instructions for each category. The categories are determined by the keywords.
//
//        Given the inputs instruction_strings_list and keyword_map_strings_list, we can create two dictionaries:
//
//        keyword_to_category: maps a keyword to its content type.
//        category_to_instruction: maps a content type to its instruction.
//        Once we have these dictionaries, we can loop through the keywords, find their corresponding categories, and then find the corresponding instructions.

public class InstructionsTemplate {

    private static Map<String, String> parseInstructionStrings(List<String> instructionStrings) {
        Map<String, String> categoryToInstruction = new HashMap<>();
        for (String instructionString : instructionStrings.subList(1, instructionStrings.size())) {
            String[] parts = instructionString.split(",", 2);
            categoryToInstruction.put(parts[0].trim(), parts[1].trim());
        }
        return categoryToInstruction;
    }

    private static Map<String, String> parseKeywordMapStrings(List<String> keywordMapStrings) {
        Map<String, String> keywordToCategory = new HashMap<>();
        for (String keywordMapString : keywordMapStrings.subList(1, keywordMapStrings.size())) {
            String[] parts = keywordMapString.split(",");
            keywordToCategory.put(parts[0].trim(), parts[1].trim());
        }
        return keywordToCategory;
    }

    public static String generateTemplateString(List<String> instructionStringsList, List<String> keywordMapStringsList) {
        Map<String, String> categoryToInstruction = parseInstructionStrings(instructionStringsList);
        Map<String, String> keywordToCategory = parseKeywordMapStrings(keywordMapStringsList);

        Set<String> categoriesSet = new HashSet<>(keywordToCategory.values());
        List<String> sortedCategories = categoriesSet.stream().sorted().toList();

        StringBuilder templateString = new StringBuilder();
        for (String category : sortedCategories) {
            templateString.append("For ").append(category).append(", ").append(categoryToInstruction.get(category)).append("\n");
        }
        return templateString.toString().trim();
    }

    public static void main(String[] args) {
        List<String> instructionStringsList = Arrays.asList(
                "Content Type, Instruction",
                "Scam, Check if they ask you for money",
                "Exploiting, Check if the game developer has enabled functionality that allows people to cheat in the game",
                "Drugs, if the game shows alcohol, tobacco, or illegal drug use, remove it from the site"
        );
        List<String> keywordMapStringsList = Arrays.asList(
                "keyword, content_type",
                "hack, exploiting",
                "obfuscator, exploiting",
                "1337, exploiting",
                "password, scam",
                "fbi, scam",
                "cigarette,drugs",
                "alcohol,drugs"
        );

        System.out.println(generateTemplateString(instructionStringsList, keywordMapStringsList));
    }
}
