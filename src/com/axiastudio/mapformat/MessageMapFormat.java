/*
 * Copyright (C) 2013 AXIA Studio (http://www.axiastudio.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.axiastudio.mapformat;

import java.text.FieldPosition;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author AXIA Studio (http://www.axiastudio.com)
 */
public class MessageMapFormat extends MapFormat {
    //private final Locale locale;
    private String pattern;
    private List<String> keys = new ArrayList();

    public MessageMapFormat(String pattern) {
        //this.locale = Locale.getDefault(Locale.Category.FORMAT);
        this.applyPattern(pattern);
    }

    @Override
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String format(Map map){
        List<Object> arguments = new ArrayList();
        for( String key: this.keys ){
            Object value = map.get(key);
            arguments.add(value);
        }
        MessageFormat mf = new MessageFormat(this.pattern);
        return mf.format(arguments.toArray());
    }
   
    @Override
    public Object parseObject(String source, ParsePosition pos) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void applyPattern(String mapPattern) {
        Pattern c = Pattern.compile("\\$\\{([a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*(\\,(number|date|time|choice)*(\\,[#\\.0-9\\|<>YyMmDdHh]*)?)?)\\}");
        Matcher matcher = c.matcher(mapPattern);
        matcher.reset();
        StringBuffer stringBuffer = new StringBuffer();
        int i=0;
        while( matcher.find() ){
            String propertyName;
            String formatTypeAndPattern;
            String group0 = matcher.group();
            String group1 = matcher.group(1);
            int n = group1.indexOf(",");
            if( n>0 ){
                propertyName = group1.substring(0, n);
                formatTypeAndPattern = group1.substring(n);
            } else {
                propertyName = group1;
                formatTypeAndPattern = "";
            }
            this.keys.add(propertyName);
            String s = "{" + i + formatTypeAndPattern + "}";
            matcher.appendReplacement(stringBuffer, Matcher.quoteReplacement(s));
            i++;
        }
        matcher.appendTail(stringBuffer);
        this.pattern = stringBuffer.toString();
    }

    public String getPattern() {
        return pattern;
    }

    public List<String> getKeys() {
        return keys;
    }
   
}

