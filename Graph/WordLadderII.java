package Graph;

import java.util.*;

/**
 * Created by rliu on 2/16/17.
 * 126. Word Ladder II
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that [beginWord is not a transformed word].
 * For example,
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * Return
 * [
 * ["hit","hot","dot","dog","cog"],
 * ["hit","hot","lot","log","cog"]
 * ]
 * Note:
 * the difference with word ladder is word ladder is return the min length, but here need to return the string
 * <p>
 * pay attention, when copy as list of list List<List<String> a=new ArrayList<>(l); will work, but does not get deep copy content of l,
 * which means the list<String> in l is changes, the List<String>of a will be changes accroding to l</String>
 */
public class WordLadderII {
    public static void main(String[] args) {

//        [["magic","manic","mania","maria","marta","marty","party","parry","perry","peary","pearl"],
//        ["magic","manic","mania","maria","maris","paris*","parks*","perks","peaks","pears","pearl"], parks has two path
//        ["magic","manic","mania","maria","marta","marty","marry","merry","perry","peary","pearl"],
//        ["magic","manic","mania","maria","marta","marty","marry","parry","perry","peary","pearl"],
//        ["magic","manic","mania","maria","maris","marks*","parks*","perks","peaks","pears","pearl"]],
//


        String[] arr = {"flail", "halon", "lexus", "joint", "pears", "slabs", "lorie", "lapse", "wroth", "yalow", "swear", "cavil", "piety", "yogis", "dhaka", "laxer", "tatum", "provo", "truss", "tends", "deana", "dried", "hutch", "basho", "flyby", "miler", "fries", "floes", "lingo", "wider", "scary", "marks", "perry", "igloo", "melts", "lanny", "satan", "foamy", "perks", "denim", "plugs", "cloak", "cyril", "women", "issue", "rocky", "marry", "trash", "merry", "topic", "hicks", "dicky", "prado", "casio", "lapel", "diane", "serer", "paige", "parry", "elope", "balds", "dated", "copra", "earth", "marty", "slake", "balms", "daryl", "loves", "civet", "sweat", "daley", "touch", "maria", "dacca", "muggy", "chore", "felix", "ogled", "acids", "terse", "cults", "darla", "snubs", "boats", "recta", "cohan", "purse", "joist", "grosz", "sheri", "steam", "manic", "luisa", "gluts", "spits", "boxer", "abner", "cooke", "scowl", "kenya", "hasps", "roger", "edwin", "black", "terns", "folks", "demur", "dingo", "party", "brian", "numbs", "forgo", "gunny", "waled", "bucks", "titan", "ruffs", "pizza", "ravel", "poole", "suits", "stoic", "segre", "white", "lemur", "belts", "scums", "parks", "gusts", "ozark", "umped", "heard", "lorna", "emile", "orbit", "onset", "cruet", "amiss", "fumed", "gelds", "italy", "rakes", "loxed", "kilts", "mania", "tombs", "gaped", "merge", "molar", "smith", "tangs", "misty", "wefts", "yawns", "smile", "scuff", "width", "paris", "coded", "sodom", "shits", "benny", "pudgy", "mayer", "peary", "curve", "tulsa", "ramos", "thick", "dogie", "gourd", "strop", "ahmad", "clove", "tract", "calyx", "maris", "wants", "lipid", "pearl", "maybe", "banjo", "south", "blend", "diana", "lanai", "waged", "shari", "magic", "duchy", "decca", "wried", "maine", "nutty", "turns", "satyr", "holds", "finks", "twits", "peaks", "teems", "peace", "melon", "czars", "robby", "tabby", "shove", "minty", "marta", "dregs", "lacks", "casts", "aruba", "stall", "nurse", "jewry", "knuth"};
        //String[] arr = {"hot", "dot", "dog", "lot", "log","cog"};
        List<String> list = new ArrayList<>(Arrays.asList(arr));
        System.out.println(findLadders("magic", "pearl", list));
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> dict = new HashSet<>();
        for (String s : wordList)
            dict.add(s);
        List<List<String>> rt = new ArrayList<>();
        if (!dict.contains(endWord))
            return rt;

        boolean found = false;
        HashSet<String> visited = new HashSet<>();

        HashMap<String, List<List<String>>> start = new HashMap<>(); //for the same string the path to these root might be different so we still need to save it as a List of list
        HashMap<String, List<List<String>>> end = new HashMap<>();
        visited.add(beginWord);
        visited.add(endWord);

        start.put(beginWord, new ArrayList<>(rt));
        end.put(endWord, new ArrayList<>(rt));
        start.get(beginWord).add(new ArrayList<>());
        end.get(endWord).add(new ArrayList<>());
        start.get(beginWord).get(0).add(beginWord);
        end.get(endWord).get(0).add(endWord);

        while (!start.isEmpty() && !end.isEmpty() && !found) {
            HashMap<String, List<List<String>>> nextLevel = new HashMap<>();

            for (String s : start.keySet()) {
                for (int i = 0; i < s.length(); i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        String newWord = replace(s, i, c);
                        if (end.containsKey(newWord)) {
                            found = true;
                            for (List<String> el : end.get(newWord)) {
                                ArrayList<String> endList = new ArrayList<>(el);
                                Collections.reverse(endList);
                                for (List<String> sl : start.get(s)) {
                                    ArrayList<String> startList = new ArrayList<>(sl);
                                    startList.addAll(endList);
                                    rt.add(startList);
                                }
                            }
                        } else if (dict.contains(newWord) && !visited.contains(newWord)) {
                            ArrayList<List<String>> newList = new ArrayList<>();
                            for (List<String> l : start.get(s))
                                newList.add(new ArrayList<>(l));
                            for (List<String> l : newList)
                                l.add(newWord);
                            List<List<String>> nl = nextLevel.get(newWord);
                            if (nl == null)
                                nextLevel.put(newWord, newList);
                            else
                                nl.addAll(newList);
                        }
                    }
                }
            }

            for (String s : nextLevel.keySet())
                visited.add(s);

            if (nextLevel.size() <= end.size())
                start = nextLevel;
            else {
                start = end;
                end = nextLevel;
            }

        }

        for (List<String> l : rt) {
            if (!l.get(0).equals(beginWord)) {
                Collections.reverse(l);
            }
        }

        return rt;
    }

    public static String replace(String word, int index, char k) {
        char[] charWord = word.toCharArray();
        charWord[index] = k;
        return new String(charWord);
    }
}
