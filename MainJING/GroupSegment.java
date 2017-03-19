package MainJING;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by rliu on 3/18/17.
 */
public class GroupSegment {
    int id;
    SegmentNode root;

    GroupSegment() {
        id = 1;
        root = new SegmentNode();
    }

    public static void main(String[] args) {
        //        String segmentName=getSegmentNameFromSegment(jsonSegment); //eg FunStore > Clothing > Likes Clothes so much
        //        int segementId=getSegmentIdFromSegment(jsonSegment); //eg. 1001295329
        //        String price=getSegmentPriceFromSegment(jsonSegment); // eg 4

        GroupSegment groupSegment = new GroupSegment();
        //assuming decode ahead of time.
        groupSegment.insertSegment("FunStore > Clothing > Likes Clothes so much", 1001295329, 4);
        groupSegment.insertSegment("FunStore > Clothing > A Red Shirt > Y", 1001295349, 6);
        groupSegment.insertSegment("FunStore > Clothing > A Red Shirt > N", 1001295359, 7);
        groupSegment.insertSegment("Target > Clothing > A Red Shirt > N", 1001295987, 17);
        System.out.println(groupSegment.getCSVContent());
    }

    public void insertSegment(String segmentName, int segmentId, int price) {

        String[] segmentNames = segmentName.split(">"); //eg. [FunStore, Clothing, Likes Clothes so much]

        SegmentNode curr = root;

        for (int i = 0; i < segmentNames.length; i++) {
            LinkedHashMap<String, SegmentNode> childs = curr.childs;

            SegmentNode child = childs.get(segmentNames[i]);
            if (child == null) {
                if (i < segmentNames.length - 1)
                    child = new SegmentNode(id++, segmentNames[i]);
                else {
                    child = new SegmentNode(segmentId, segmentNames[i]);
                    child.price = price;
                }
                childs.put(segmentNames[i], child);
            }
            curr = child;
        }
    }


    String getCSVContent() {
        StringBuilder sb = new StringBuilder();
        for (SegmentNode node : root.childs.values()) {
            List<List<String>> res = new ArrayList<>();
            dfsPrint(node, new ArrayList<String>(), res);
            for (List<String> l : res) {
                sb.append(String.join(",", l));
                sb.append("\n");
            }

        }
        return sb.toString();
    }

    void dfsPrint(SegmentNode node, List<String> prev, List<List<String>> res) {
        prev.add(node.nodeName);
        List<String> curr = new ArrayList<>();
        curr.add(Integer.toString(node.id));
        curr.add(String.join(">", prev));
        if (node.price != null)
            curr.add(Integer.toString(node.price));

        res.add(curr);
        for (SegmentNode c : node.childs.values()) {
            dfsPrint(c, prev, res);
        }
        prev.remove(prev.size() - 1);
    }


    private class SegmentNode {
        int id;
        String nodeName;
        LinkedHashMap<String, SegmentNode> childs;
        Integer price;

        SegmentNode() {
            childs = new LinkedHashMap<>();
        }

        SegmentNode(int id, String nodeName) {
            this.id = id;
            this.nodeName = nodeName;
            childs = new LinkedHashMap<>();
            price = null;
        }
    }
}
