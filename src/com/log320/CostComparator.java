package com.log320;

import java.util.Comparator;

public class CostComparator implements Comparator<PriorityNode> {

    @Override
    public int compare(PriorityNode firstNode, PriorityNode secondNode) {
        // TODO Auto-generated method stub
        int c1 = firstNode.getWeight();
        int c2 = secondNode.getWeight();
        if (c1 == c2)
            return secondNode.getDistance() - firstNode.getDistance();
        return c1 - c2;
    }

}
