/*
 * Copyright (c) 2014, AXIA Studio (Tiziano Lattisi) - http://www.axiastudio.com
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the AXIA Studio nor the
 *    names of its contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY AXIA STUDIO ''AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL AXIA STUDIO BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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

