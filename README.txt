A set of java.text.Format subclasses that instead positional notation uses tag names.

Example:

String pattern = "${mydate,date,dd}/${mystring}/${mynumber,number,00}/";

Map<String, Object> map = new HashMap();
map.put("mydate", (new GregorianCalendar(2012, 9-1, 20)).getTime());
map.put("mystring", "That's all folks!");
map.put("mynumber", 7);

MessageMapFormat mmp = new MessageMapFormat(pattern);

String result = mmp.format(map);          // "20/That's all folks!/07/