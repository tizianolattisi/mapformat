/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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