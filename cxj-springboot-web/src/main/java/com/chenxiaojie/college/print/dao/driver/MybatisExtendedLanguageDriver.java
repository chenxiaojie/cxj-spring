package com.chenxiaojie.college.print.dao.driver;

import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenxiaojie on 15/11/14.
 * 当mybatis的入参是数组或者集合的时候打上此注解
 */
public class MybatisExtendedLanguageDriver extends XMLLanguageDriver implements LanguageDriver {

    private static final Pattern foreachPattern = Pattern.compile("(?s)\\(\\s*(?:#\\{(.*)\\})+.*\\)", Pattern.MULTILINE);

    public SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType) {
        if (parameterType.isArray() || Collection.class.isAssignableFrom(parameterType)) {
            Matcher matcher = foreachPattern.matcher(script);
            if (matcher.find()) {
                String matchString = matcher.group(1);
                if (matchString.contains(".")) {
                    String listName = matchString.split("\\.", 2)[0];
                    script = matcher.replaceAll("<foreach collection=\"" + listName + "\" item=\"" + listName + "\" separator=\",\" >$0</foreach>");

                } else {
                    script = matcher.replaceAll("(<foreach collection=\"$1\" item=\"__item\" separator=\",\" >#{__item}</foreach>)");
                }
            }
            if (!script.trim().startsWith("<script>")) {
                script = new StringBuilder(500).append("<script>").append(script).append("</script>").toString();
            }
        }
        return super.createSqlSource(configuration, script, parameterType);
    }
}
