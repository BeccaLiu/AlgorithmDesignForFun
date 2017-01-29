package Linear.String;

/**
 * Created by rliu on 1/28/17.
 * Write one function mapping "2w3d5h8m9s" to"2 weeks 3 days 5 hours 8 minutes 9 seconds"
 */
public class StringMapping {
    public static void main(String[] args) {
        String input = "2w3d5h8m9s";
        System.out.println(mappingTime(input));
        input = "2w3d1h8m9s";
        System.out.println(mappingTime(input));
        input = "2w3d21h8m9s";
        System.out.println(mappingTime(input));

    }

    public static String mappingTime(String time) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < time.length(); i++) {
            char curr = time.charAt(i);
            if (Character.isDigit(curr))
                out.append(curr);
            else if (!Character.isDigit(time.charAt(i - 1))) //case 2ww2dd5h8m9s
                return "invalid input";
            else
                switch (curr) {
                    case 'w':
                        if (time.charAt(i - 1) == '1' && !Character.isDigit(time.charAt(i - 2)))
                            out.append(" week ");
                        else
                            out.append(" weeks ");
                        break;
                    case 'd':
                        if (time.charAt(i - 1) == '1' && !Character.isDigit(time.charAt(i - 2)))
                            out.append(" day ");
                        else
                            out.append(" days ");
                        break;
                    case 'h':
                        if (time.charAt(i - 1) == '1' && !Character.isDigit(time.charAt(i - 2)))
                            out.append(" hour ");
                        else
                            out.append(" hours ");
                        break;
                    case 'm':
                        if (time.charAt(i - 1) == '1' && !Character.isDigit(time.charAt(i - 2)))
                            out.append(" minute ");
                        else
                            out.append(" minutes ");
                        break;
                    case 's':
                        if (time.charAt(i - 1) == '1' && !Character.isDigit(time.charAt(i - 2)))
                            out.append(" second");
                        else
                            out.append(" seconds");
                        break;
                }
        }
        return out.toString();
    }
}
