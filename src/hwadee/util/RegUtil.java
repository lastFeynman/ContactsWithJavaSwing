package hwadee.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegUtil {
	public static boolean isFind(String reg,String origin) {
		if(reg == null || origin == null)
			return false;
		
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(origin);
		
		if(matcher.find())
			return true;
		else
			return false;
	}
	
	public static String getGroup(String reg,String origin,int groupId) {
		String getStr = null;
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(origin);
		
		if(matcher.find())
			getStr = matcher.group(groupId);
		
		return getStr;
	}
}
