package MainJING;

/**
 * Created by rliu on 2/9/17.
 * String mapping
 * GroundTruthBad
 * groundTruthBad
 * ground_truth_bad
 * string conversion
 */
public class FileNameConversion {
    public static void main(String[] args) {
        System.out.println(toAllCapital("GroundTruthBad"));
        System.out.println(toAllCapital("ground_truth_bad"));
        System.out.println(toAllCapital("groundTruthBad"));
        System.out.println(toPython("GroundTruthBad"));
        System.out.println(toPython("groundTruthBad"));
        System.out.println(toPython("ground_truth_bad"));
        System.out.println(toCarmel("GroundTruthBad"));
        System.out.println(toCarmel("groundTruthBad"));
        System.out.println(toCarmel("ground_truth_bad"));
    }

    public static String toCarmel(String input) {
        StringBuilder sb = new StringBuilder();

        if (input.charAt(0) - 'A' >= 0 && input.charAt(0) - 'A' <= 26)
            return sb.append(Character.toLowerCase(input.charAt(0))).append(input.substring(1, input.length())).toString();

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '_' && i + 1 < input.length()) {
                sb.append(Character.toUpperCase(input.charAt(i + 1)));
                i++;
            } else
                sb.append(input.charAt(i));
        }
        return sb.toString();
    }

    public static String toPython(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append(Character.toLowerCase(input.charAt(0)));

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) - 'A' >= 0 && input.charAt(i) - 'A' <= 26) {
                sb.append("_");
                sb.append(Character.toLowerCase(input.charAt(i)));
            } else
                sb.append(input.charAt(i));
        }
        return sb.toString();
    }

    public static String toAllCapital(String input) {
        if (input.charAt(0) - 'A' >= 0 && input.charAt(0) - 'A' <= 26) //already all capital
            return input;
        StringBuilder sb = new StringBuilder();
        boolean toUpperCase = true;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '_') {
                toUpperCase = true;
                continue;
            }
            if (toUpperCase) {
                sb.append(Character.toUpperCase(input.charAt(i)));
                toUpperCase = false;
            } else {
                sb.append(input.charAt(i));
            }
        }
        return sb.toString();
    }
}
