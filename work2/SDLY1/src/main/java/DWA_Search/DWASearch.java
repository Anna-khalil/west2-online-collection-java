package DWA_Search;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class DWASearch {
    public static final Pattern REGEX_PLAYERS = Pattern.compile("^players$");

    public static final Pattern REGEX_RESULT = Pattern.compile("^result.*");
    public static final Pattern REGEX_CONTEST = Pattern.compile("^result (women 1m springboard|women 3m springboard|women 10m platform|women 3m synchronised|women 10m synchronised|men 1m springboard|men 3m springboard|men 10m platform|men 3m synchronised|men 10m synchronised)$");
    public static final Pattern REGEX_CONTEST_DETAILED = Pattern.compile("^result (women 1m springboard|women 3m springboard|women 10m platform|women 3m synchronised|women 10m synchronised|men 1m springboard|men 3m springboard|men 10m platform|men 3m synchronised|men 10m synchronised) detail$");

    public static void main(String[] args) throws IOException {
        CoreModule coreModule = new CoreModule();
        String input = args[0];
        String output = args[1];
        IOstream.setInput(input);
        IOstream.setOutput(output);
        String line;
        while ((line = IOstream.readLine()) != null) {
            Matcher playersMatcher = REGEX_PLAYERS.matcher(line);
            Matcher resultMathcher = REGEX_RESULT.matcher(line);
            Matcher contestMathcher = REGEX_CONTEST.matcher(line);
            Matcher contestDetailedMathcher = REGEX_CONTEST_DETAILED.matcher(line);
            // 如果查询 players
            if (playersMatcher.find()) {
                coreModule.displayAllPlayersInfo();
                continue;
            }
            // 如果匹配到 result 开头
            if (resultMathcher.find()) {
                if (contestMathcher.find()) { // 查询单场比赛(决赛)
                    coreModule.displayResults(contestMathcher.group(1));
                } else if (contestDetailedMathcher.find()) { // 查询 多场比赛
                    coreModule.displayDetailedResults(contestDetailedMathcher.group(1));
                } else {
                    // 输入不正确
                    IOstream.write("N/A\n" + "-----------");
                }
            } else {
                // 无法识别的指令
                IOstream.write("Error\n" + "-----------");
            }
        }
        IOstream.close();
    }
}