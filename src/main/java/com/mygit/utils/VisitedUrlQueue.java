package com.mygit.utils;

import java.util.HashSet;

/**
 * Created by yxf on 2017/11/30.
 */
public class VisitedUrlQueue {
    public static HashSet<String> visitedUrlQueue = new HashSet<String>();

    public synchronized static void addElem(String url)
    {
        visitedUrlQueue.add(url);
    }

    public synchronized static boolean isContains(String url)
    {
        return visitedUrlQueue.contains(url);
    }

    public synchronized static int size()
    {
        return visitedUrlQueue.size();
    }
}
