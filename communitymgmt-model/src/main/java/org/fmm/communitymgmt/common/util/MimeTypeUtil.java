package org.fmm.communitymgmt.common.util;

import java.net.URLConnection;

public class MimeTypeUtil {
	public static String getMimeType(String filePath) {
		try {
			return URLConnection.guessContentTypeFromName(filePath);
		} catch (Exception e) {
			e.printStackTrace();
			return "application/octect-stream";
		}
	}
}
