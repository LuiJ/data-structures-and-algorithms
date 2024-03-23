package root.tasks.unnamed;

/*
    You are working in a team responsible for maintaining a chips transaction service. The transactions are tracked in a log file.

    The content of the file is provided in your system as a string list where each entry represents a chips transaction between two players.
    The structure of a transaction consists of:
    - sender_player_id: Unique identifier for the player that initiated the transaction. It consists of only digits with at most 9 digits.
    - receiver_player_id: Unique identifier for the player that is receiving the chips amount. It consists only digits with at most 9 digits.
    - chips_amount: The amount of chips that are sending from a player to another one. It consists only digits with at most 9 digits.

    The values are separated by a ';'. For example, "sender_player_id;receiver_player_id;chips_amount";

    Players that perform an excessive amount of transactions might be abusing the service so you have been tasked to identify those the players that have a number of       transactions over or equal to a threshold.
    The list of player ids should be ordered in ascending numeric value.

    Constraints:
    - the length of sender_player_id, receiver_player_id and chips_amount should be in [0,9]
    - sender_player_id, receiver_player_id and chips_amount should not starts with 0
    - the result must return at least one element

    Example:
    logs = ['12;13;10000', '12;13;15000', '11;11;3000', '13;28;1500'], threshold = 2

    The transactions count for each player:
    12 - 2
    13 - 3
    11 - 1
    28 - 1

    Expected result: [12, 13]

    Note: In the previous example, player 11 was on both sides of the transaction. This counts as only 1 transaction for player 11.
*/

import lombok.Value;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Task_1 {

    private static final String TRANSACTION_ATTRIBUTES_SEPARATOR = ";";

    public static void main(String[] args) {
        // given
        var log = List.of("12;13;10000", "11;013;15000", "12;13;15000", "11;11;3000", "13;28;1500");
        var threshold = 2;
        var expectedResult = List.of(12, 13);

        // when
        var result = findAbusers(log, threshold);

        // then
        if (result.equals(expectedResult)) {
            System.out.println("The task is done successfully");
        } else {
            System.out.println("Results do not match: expected " + expectedResult + ", actual " + result);
        }
    }

    private static List<Integer> findAbusers(List<String> log, int threshold) {
        return log.stream()
                .map(Task_1::buildTransactionRecords)
                .filter(Objects::nonNull)
                .map(TransactionRecord::getUniqueIds)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() >= threshold)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private static TransactionRecord buildTransactionRecords(String transaction) {
        try {
            var transactionAttributes = transaction.split(TRANSACTION_ATTRIBUTES_SEPARATOR);
            verifyTransactionAttributes(transactionAttributes);
            return new TransactionRecord(
                    Integer.parseInt(transactionAttributes[0]),
                    Integer.parseInt(transactionAttributes[1]),
                    Integer.parseInt(transactionAttributes[2])
            );
        } catch (Exception e) {
            System.err.println("WARN: Transaction is invalid: " + transaction);
            return null;
        }
    }

    private static void verifyTransactionAttributes(String[] transactionAttributes) {
        if (Arrays.stream(transactionAttributes).anyMatch(attribute -> attribute.startsWith("0"))) {
            throw new IllegalArgumentException("Wrong number string");
        }
    }

    @Value
    private static class TransactionRecord {
        Integer senderId;
        Integer receiverId;
        Integer amount;

        public Set<Integer> getUniqueIds() {
            return new HashSet<>() {{
                add(senderId);
                add(receiverId);
            }};
        }
    }
}
