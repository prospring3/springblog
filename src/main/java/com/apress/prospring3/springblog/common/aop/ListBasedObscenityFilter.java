/**
 * Created on Oct 3, 2011
 */
package com.apress.prospring3.springblog.common.aop;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * @author Clarence
 *
 */
@Service("obscenityFilter")
public class ListBasedObscenityFilter implements ObscenityFilter {

	private List<String> obscenities = null;

	private Pattern obscenityPattern = null;

	@Resource(name="obscenities")
	public void setObscenities(List<String> obscenities) {
		this.obscenities = obscenities;

		buildRegex();
	}

	private void buildRegex() {
		StringBuffer sb = new StringBuffer();

		int size = obscenities.size();

		for (int x = 0; x < size; x++) {
			if (x != 0) {
				sb.append("|");
			}
			sb.append("(");
			sb.append(obscenities.get(x));
			sb.append(")");
		}

		obscenityPattern = Pattern.compile(sb.toString(), Pattern.CASE_INSENSITIVE);
	}

	/**
	 * Returns true if the data contain an obscenity otherwise returns false
	 */
	public boolean containsObscenities(String data) {
		Matcher m = obscenityPattern.matcher(data);
		return m.find();
	}

	/**
	 * Obfuscate all obscenities in a String
	 */
	public String obfuscateObscenities(String data) {
		Matcher m = obscenityPattern.matcher(data);
		StringBuffer out = new StringBuffer(data.length());

		while (m.find()) {
			if (m.group(0) != null) {
				m.appendReplacement(out, rot13(m.group(0)));
			}
		}
        
        m.appendTail(out);

		return out.toString();
	}

	/**
	 * Rot13 encode a String value.
	 * @param in
	 * @return Encoded string
	 */
	private String rot13(String in) {
		char[] chars = in.toCharArray();

		for (int x = 0; x < chars.length; x++) {
			char c = chars[x];
			if (c >= 'a' && c <= 'm')
				c += 13;
			else if (c >= 'n' && c <= 'z')
				c -= 13;
			else if (c >= 'A' && c <= 'M')
				c += 13;
			else if (c >= 'A' && c <= 'Z') c -= 13;

			chars[x] = c;
		}

		return new String(chars);
	}
	
}
