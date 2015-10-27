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

import com.axiastudio.mapformat.MessageMapFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author AXIA Studio (http://www.axiastudio.com)
 */
public class MessageMapFormatTest {
    
    public MessageMapFormatTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of format method, of class MessageMapFormat.
     */
    @Test
    public void testFormat() {
        String pattern = "${mydate,date,dd}/${mystring}/${mynumber,number,00}/";
        MessageMapFormat mmp = new MessageMapFormat(pattern);
        Map<String, Object> map = new HashMap();
        map.put("mydate", (new GregorianCalendar(2012, 9-1, 20)).getTime());
        map.put("mystring", "That's all folks!");
        map.put("mynumber", 7);
        String expResult = "20/That's all folks!/07/";
        String result = mmp.format(map);
        assertEquals(expResult, result);
    }

   
    @Test
    public void testApplyPattern() {
        String pattern = "${mydate,date,dd}/${mystring}/${mynumber,number,00}/";
        MessageMapFormat mmp = new MessageMapFormat(pattern);
        assertEquals("{0,date,dd}/{1}/{2,number,00}/", mmp.getPattern());
        List<String> expResult = new ArrayList();
        expResult.add("mydate");
        expResult.add("mystring");
        expResult.add("mynumber");
        assertEquals(expResult, mmp.getKeys());
    }

}