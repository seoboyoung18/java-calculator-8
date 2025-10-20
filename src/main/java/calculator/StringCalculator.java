package calculator;

public class StringCalculator {
    public static int add(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        input = input.replace("\\n", "\n");

        String[] numbers = splitInput(input);

        if (numbers.length == 1) {
            String value = numbers[0];
            if (!value.matches("\\d+")) {
                throw new IllegalArgumentException("잘못된 입력입니다: " + value);
            }

            throw new IllegalArgumentException("구분자가 포함된 형식이 아닙니다: " + value);
        }

        int sum = 0;
        for (String num : numbers) {
            if (!num.matches("\\d+")) {
                throw new IllegalArgumentException("잘못된 입력: " + num);
            }
            sum += Integer.parseInt(num);
        }

        return sum;
    }

    private static String[] splitInput(String input) {
        if (input.startsWith("//")) {
            // 커스텀 구분자 처리
            int delimiterIndex = input.indexOf("\\n");
            if (delimiterIndex == -1) {
                delimiterIndex = input.indexOf("\n");
            }

            if (delimiterIndex == -1) {
                throw new IllegalArgumentException("커스텀 구분자 형식이 잘못되었습니다.");
            }
            String delimiter = input.substring(2, delimiterIndex);
            String numbers = input.substring(delimiterIndex + 1);
            return numbers.split(delimiter);
        }
        // 기본 구분자 : 쉼표, 콜론
        return input.split("[,:]");
    }
}