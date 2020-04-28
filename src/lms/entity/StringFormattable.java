package lms.entity;

import java.util.stream.Stream;

public interface StringFormattable {

	public default String stringify(String... values) {
		return Stream.of(values).reduce("", (str1, str2) -> {
			if (str1.isEmpty()) {
				return str2;
			}
			return str1 + " - " + str2;
		});
	}

	public default String accumulateString(String str1, String str2) {
		if (str1.isEmpty()) {
			return str2;
		}
		return str1 + "," + str2;
	}
}
