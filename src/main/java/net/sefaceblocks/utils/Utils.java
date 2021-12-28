package net.sefaceblocks.utils;

import java.util.ArrayList;

public class Utils {

  public static String replaceColor(String content) {
        return content.replace('&', '§');
    }

  public static ArrayList<String> replaceColor(ArrayList<String> contents) {
    ArrayList<String> replacedContents = new ArrayList<>();

    for (String content : contents) {
      replacedContents.add(
        replaceColor(content)
      );
    }

    return replacedContents;
  }
}
