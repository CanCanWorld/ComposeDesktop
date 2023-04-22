package utils

import java.net.URLEncoder
import java.nio.charset.Charset
import java.util.regex.Pattern

object ClutterUtils {
    fun encodeOnlyChinese(url: String, charset: Charset): String {
        val sb = StringBuffer()
        try {
            val matcher = Pattern.compile("[\u4e00-\u9fa5]+").matcher(url)
            while (matcher.find()) {
                matcher.appendReplacement(sb, URLEncoder.encode(matcher.group(0), charset))
            }
            matcher.appendTail(sb)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return sb.toString()
    }
}

