import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class FileLogger
{
  private static final String COMMA_DELIMITER = ",";
  private static final String NEW_LINE_SEPARATOR = "\n";
  private static final String LOG_FILE_HEADER = "Time,RPM,TPS,COOLANT,Speed,ADK,Gear,accX,accY,accZ,gyroX,gyroY,gyroZ,magX,magY,magZ,susp1,susp2,susp3,susp4,steering";
  static final String LogFile = "LogData.csv";
  static Date date;
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
  
  public FileLogger()
  {
    initializeFile();
    
    logData();
  }
  
  /* Error */
  private void initializeFile()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: new 46	java/io/FileWriter
    //   5: dup
    //   6: ldc 17
    //   8: iconst_1
    //   9: invokespecial 48	java/io/FileWriter:<init>	(Ljava/lang/String;Z)V
    //   12: astore_1
    //   13: aload_1
    //   14: ldc 14
    //   16: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   19: pop
    //   20: aload_1
    //   21: ldc 11
    //   23: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   26: pop
    //   27: goto +74 -> 101
    //   30: astore_2
    //   31: getstatic 55	java/lang/System:out	Ljava/io/PrintStream;
    //   34: ldc 61
    //   36: invokevirtual 63	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   39: aload_2
    //   40: invokevirtual 69	java/lang/Exception:printStackTrace	()V
    //   43: aload_1
    //   44: invokevirtual 74	java/io/FileWriter:flush	()V
    //   47: aload_1
    //   48: invokevirtual 77	java/io/FileWriter:close	()V
    //   51: goto +76 -> 127
    //   54: astore 4
    //   56: getstatic 55	java/lang/System:out	Ljava/io/PrintStream;
    //   59: ldc 80
    //   61: invokevirtual 63	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   64: aload 4
    //   66: invokevirtual 82	java/io/IOException:printStackTrace	()V
    //   69: goto +58 -> 127
    //   72: astore_3
    //   73: aload_1
    //   74: invokevirtual 74	java/io/FileWriter:flush	()V
    //   77: aload_1
    //   78: invokevirtual 77	java/io/FileWriter:close	()V
    //   81: goto +18 -> 99
    //   84: astore 4
    //   86: getstatic 55	java/lang/System:out	Ljava/io/PrintStream;
    //   89: ldc 80
    //   91: invokevirtual 63	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   94: aload 4
    //   96: invokevirtual 82	java/io/IOException:printStackTrace	()V
    //   99: aload_3
    //   100: athrow
    //   101: aload_1
    //   102: invokevirtual 74	java/io/FileWriter:flush	()V
    //   105: aload_1
    //   106: invokevirtual 77	java/io/FileWriter:close	()V
    //   109: goto +18 -> 127
    //   112: astore 4
    //   114: getstatic 55	java/lang/System:out	Ljava/io/PrintStream;
    //   117: ldc 80
    //   119: invokevirtual 63	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   122: aload 4
    //   124: invokevirtual 82	java/io/IOException:printStackTrace	()V
    //   127: return
    // Line number table:
    //   Java source line #35	-> byte code offset #0
    //   Java source line #37	-> byte code offset #2
    //   Java source line #40	-> byte code offset #13
    //   Java source line #43	-> byte code offset #20
    //   Java source line #45	-> byte code offset #27
    //   Java source line #46	-> byte code offset #31
    //   Java source line #47	-> byte code offset #39
    //   Java source line #50	-> byte code offset #43
    //   Java source line #51	-> byte code offset #47
    //   Java source line #52	-> byte code offset #51
    //   Java source line #53	-> byte code offset #56
    //   Java source line #54	-> byte code offset #64
    //   Java source line #48	-> byte code offset #72
    //   Java source line #50	-> byte code offset #73
    //   Java source line #51	-> byte code offset #77
    //   Java source line #52	-> byte code offset #81
    //   Java source line #53	-> byte code offset #86
    //   Java source line #54	-> byte code offset #94
    //   Java source line #57	-> byte code offset #99
    //   Java source line #50	-> byte code offset #101
    //   Java source line #51	-> byte code offset #105
    //   Java source line #52	-> byte code offset #109
    //   Java source line #53	-> byte code offset #114
    //   Java source line #54	-> byte code offset #122
    //   Java source line #59	-> byte code offset #127
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	128	0	this	FileLogger
    //   1	105	1	fileWriter	java.io.FileWriter
    //   30	10	2	e	Exception
    //   72	28	3	localObject	Object
    //   54	11	4	e	java.io.IOException
    //   84	11	4	e	java.io.IOException
    //   112	11	4	e	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   2	27	30	java/lang/Exception
    //   43	51	54	java/io/IOException
    //   2	43	72	finally
    //   73	81	84	java/io/IOException
    //   101	109	112	java/io/IOException
  }
  
  /* Error */
  private void writeValues()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: new 46	java/io/FileWriter
    //   5: dup
    //   6: ldc 17
    //   8: iconst_1
    //   9: invokespecial 48	java/io/FileWriter:<init>	(Ljava/lang/String;Z)V
    //   12: astore_1
    //   13: new 94	java/util/Date
    //   16: dup
    //   17: invokespecial 96	java/util/Date:<init>	()V
    //   20: putstatic 97	FileLogger:date	Ljava/util/Date;
    //   23: aload_1
    //   24: new 99	java/sql/Timestamp
    //   27: dup
    //   28: getstatic 97	FileLogger:date	Ljava/util/Date;
    //   31: invokevirtual 101	java/util/Date:getTime	()J
    //   34: invokespecial 105	java/sql/Timestamp:<init>	(J)V
    //   37: invokestatic 108	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   40: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   43: pop
    //   44: aload_1
    //   45: ldc 8
    //   47: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   50: pop
    //   51: aload_1
    //   52: getstatic 114	Integration:RPM	D
    //   55: invokestatic 120	java/lang/String:valueOf	(D)Ljava/lang/String;
    //   58: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   61: pop
    //   62: aload_1
    //   63: ldc 8
    //   65: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   68: pop
    //   69: aload_1
    //   70: getstatic 123	Integration:tps	I
    //   73: invokestatic 127	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   76: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   79: pop
    //   80: aload_1
    //   81: ldc 8
    //   83: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   86: pop
    //   87: aload_1
    //   88: getstatic 130	Integration:clt	D
    //   91: invokestatic 120	java/lang/String:valueOf	(D)Ljava/lang/String;
    //   94: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   97: pop
    //   98: aload_1
    //   99: ldc 8
    //   101: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   104: pop
    //   105: aload_1
    //   106: getstatic 133	Integration:speed	D
    //   109: invokestatic 120	java/lang/String:valueOf	(D)Ljava/lang/String;
    //   112: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   115: pop
    //   116: aload_1
    //   117: ldc 8
    //   119: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   122: pop
    //   123: aload_1
    //   124: getstatic 136	Integration:adk	I
    //   127: invokestatic 127	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   130: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   133: pop
    //   134: aload_1
    //   135: ldc 8
    //   137: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   140: pop
    //   141: aload_1
    //   142: getstatic 139	Integration:gear	I
    //   145: invokestatic 127	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   148: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   151: pop
    //   152: aload_1
    //   153: ldc 8
    //   155: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   158: pop
    //   159: aload_1
    //   160: getstatic 142	Integration:accX	D
    //   163: invokestatic 120	java/lang/String:valueOf	(D)Ljava/lang/String;
    //   166: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   169: pop
    //   170: aload_1
    //   171: ldc 8
    //   173: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   176: pop
    //   177: aload_1
    //   178: getstatic 145	Integration:accY	D
    //   181: invokestatic 120	java/lang/String:valueOf	(D)Ljava/lang/String;
    //   184: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   187: pop
    //   188: aload_1
    //   189: ldc 8
    //   191: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   194: pop
    //   195: aload_1
    //   196: getstatic 148	Integration:accZ	D
    //   199: invokestatic 120	java/lang/String:valueOf	(D)Ljava/lang/String;
    //   202: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   205: pop
    //   206: aload_1
    //   207: ldc 8
    //   209: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   212: pop
    //   213: aload_1
    //   214: getstatic 151	Integration:gyroX	D
    //   217: invokestatic 120	java/lang/String:valueOf	(D)Ljava/lang/String;
    //   220: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   223: pop
    //   224: aload_1
    //   225: ldc 8
    //   227: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   230: pop
    //   231: aload_1
    //   232: getstatic 154	Integration:gyroY	D
    //   235: invokestatic 120	java/lang/String:valueOf	(D)Ljava/lang/String;
    //   238: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   241: pop
    //   242: aload_1
    //   243: ldc 8
    //   245: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   248: pop
    //   249: aload_1
    //   250: getstatic 157	Integration:gyroZ	D
    //   253: invokestatic 120	java/lang/String:valueOf	(D)Ljava/lang/String;
    //   256: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   259: pop
    //   260: aload_1
    //   261: ldc 8
    //   263: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   266: pop
    //   267: aload_1
    //   268: getstatic 160	Integration:magX	D
    //   271: invokestatic 120	java/lang/String:valueOf	(D)Ljava/lang/String;
    //   274: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   277: pop
    //   278: aload_1
    //   279: ldc 8
    //   281: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   284: pop
    //   285: aload_1
    //   286: getstatic 163	Integration:magY	D
    //   289: invokestatic 120	java/lang/String:valueOf	(D)Ljava/lang/String;
    //   292: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   295: pop
    //   296: aload_1
    //   297: ldc 8
    //   299: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   302: pop
    //   303: aload_1
    //   304: getstatic 166	Integration:magZ	D
    //   307: invokestatic 120	java/lang/String:valueOf	(D)Ljava/lang/String;
    //   310: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   313: pop
    //   314: aload_1
    //   315: ldc 8
    //   317: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   320: pop
    //   321: aload_1
    //   322: getstatic 169	Integration:susp1	D
    //   325: invokestatic 120	java/lang/String:valueOf	(D)Ljava/lang/String;
    //   328: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   331: pop
    //   332: aload_1
    //   333: ldc 8
    //   335: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   338: pop
    //   339: aload_1
    //   340: getstatic 172	Integration:susp2	D
    //   343: invokestatic 120	java/lang/String:valueOf	(D)Ljava/lang/String;
    //   346: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   349: pop
    //   350: aload_1
    //   351: ldc 8
    //   353: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   356: pop
    //   357: aload_1
    //   358: getstatic 175	Integration:susp3	D
    //   361: invokestatic 120	java/lang/String:valueOf	(D)Ljava/lang/String;
    //   364: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   367: pop
    //   368: aload_1
    //   369: ldc 8
    //   371: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   374: pop
    //   375: aload_1
    //   376: getstatic 178	Integration:susp4	D
    //   379: invokestatic 120	java/lang/String:valueOf	(D)Ljava/lang/String;
    //   382: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   385: pop
    //   386: aload_1
    //   387: ldc 8
    //   389: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   392: pop
    //   393: aload_1
    //   394: getstatic 181	Integration:steering	D
    //   397: invokestatic 120	java/lang/String:valueOf	(D)Ljava/lang/String;
    //   400: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   403: pop
    //   404: aload_1
    //   405: ldc 11
    //   407: invokevirtual 51	java/io/FileWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   410: pop
    //   411: goto +74 -> 485
    //   414: astore_2
    //   415: getstatic 55	java/lang/System:out	Ljava/io/PrintStream;
    //   418: ldc 61
    //   420: invokevirtual 63	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   423: aload_2
    //   424: invokevirtual 69	java/lang/Exception:printStackTrace	()V
    //   427: aload_1
    //   428: invokevirtual 74	java/io/FileWriter:flush	()V
    //   431: aload_1
    //   432: invokevirtual 77	java/io/FileWriter:close	()V
    //   435: goto +76 -> 511
    //   438: astore 4
    //   440: getstatic 55	java/lang/System:out	Ljava/io/PrintStream;
    //   443: ldc 80
    //   445: invokevirtual 63	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   448: aload 4
    //   450: invokevirtual 82	java/io/IOException:printStackTrace	()V
    //   453: goto +58 -> 511
    //   456: astore_3
    //   457: aload_1
    //   458: invokevirtual 74	java/io/FileWriter:flush	()V
    //   461: aload_1
    //   462: invokevirtual 77	java/io/FileWriter:close	()V
    //   465: goto +18 -> 483
    //   468: astore 4
    //   470: getstatic 55	java/lang/System:out	Ljava/io/PrintStream;
    //   473: ldc 80
    //   475: invokevirtual 63	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   478: aload 4
    //   480: invokevirtual 82	java/io/IOException:printStackTrace	()V
    //   483: aload_3
    //   484: athrow
    //   485: aload_1
    //   486: invokevirtual 74	java/io/FileWriter:flush	()V
    //   489: aload_1
    //   490: invokevirtual 77	java/io/FileWriter:close	()V
    //   493: goto +18 -> 511
    //   496: astore 4
    //   498: getstatic 55	java/lang/System:out	Ljava/io/PrintStream;
    //   501: ldc 80
    //   503: invokevirtual 63	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   506: aload 4
    //   508: invokevirtual 82	java/io/IOException:printStackTrace	()V
    //   511: return
    // Line number table:
    //   Java source line #63	-> byte code offset #0
    //   Java source line #65	-> byte code offset #2
    //   Java source line #70	-> byte code offset #13
    //   Java source line #71	-> byte code offset #23
    //   Java source line #72	-> byte code offset #44
    //   Java source line #73	-> byte code offset #51
    //   Java source line #74	-> byte code offset #62
    //   Java source line #75	-> byte code offset #69
    //   Java source line #76	-> byte code offset #80
    //   Java source line #77	-> byte code offset #87
    //   Java source line #78	-> byte code offset #98
    //   Java source line #79	-> byte code offset #105
    //   Java source line #80	-> byte code offset #116
    //   Java source line #81	-> byte code offset #123
    //   Java source line #82	-> byte code offset #134
    //   Java source line #83	-> byte code offset #141
    //   Java source line #84	-> byte code offset #152
    //   Java source line #85	-> byte code offset #159
    //   Java source line #86	-> byte code offset #170
    //   Java source line #87	-> byte code offset #177
    //   Java source line #88	-> byte code offset #188
    //   Java source line #89	-> byte code offset #195
    //   Java source line #90	-> byte code offset #206
    //   Java source line #91	-> byte code offset #213
    //   Java source line #92	-> byte code offset #224
    //   Java source line #93	-> byte code offset #231
    //   Java source line #94	-> byte code offset #242
    //   Java source line #95	-> byte code offset #249
    //   Java source line #96	-> byte code offset #260
    //   Java source line #97	-> byte code offset #267
    //   Java source line #98	-> byte code offset #278
    //   Java source line #99	-> byte code offset #285
    //   Java source line #100	-> byte code offset #296
    //   Java source line #101	-> byte code offset #303
    //   Java source line #102	-> byte code offset #314
    //   Java source line #103	-> byte code offset #321
    //   Java source line #104	-> byte code offset #332
    //   Java source line #105	-> byte code offset #339
    //   Java source line #106	-> byte code offset #350
    //   Java source line #107	-> byte code offset #357
    //   Java source line #108	-> byte code offset #368
    //   Java source line #109	-> byte code offset #375
    //   Java source line #110	-> byte code offset #386
    //   Java source line #111	-> byte code offset #393
    //   Java source line #118	-> byte code offset #404
    //   Java source line #121	-> byte code offset #411
    //   Java source line #122	-> byte code offset #415
    //   Java source line #123	-> byte code offset #423
    //   Java source line #126	-> byte code offset #427
    //   Java source line #127	-> byte code offset #431
    //   Java source line #128	-> byte code offset #435
    //   Java source line #129	-> byte code offset #440
    //   Java source line #130	-> byte code offset #448
    //   Java source line #124	-> byte code offset #456
    //   Java source line #126	-> byte code offset #457
    //   Java source line #127	-> byte code offset #461
    //   Java source line #128	-> byte code offset #465
    //   Java source line #129	-> byte code offset #470
    //   Java source line #130	-> byte code offset #478
    //   Java source line #133	-> byte code offset #483
    //   Java source line #126	-> byte code offset #485
    //   Java source line #127	-> byte code offset #489
    //   Java source line #128	-> byte code offset #493
    //   Java source line #129	-> byte code offset #498
    //   Java source line #130	-> byte code offset #506
    //   Java source line #135	-> byte code offset #511
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	512	0	this	FileLogger
    //   1	489	1	fileWriter	java.io.FileWriter
    //   414	10	2	e	Exception
    //   456	28	3	localObject	Object
    //   438	11	4	e	java.io.IOException
    //   468	11	4	e	java.io.IOException
    //   496	11	4	e	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   2	411	414	java/lang/Exception
    //   427	435	438	java/io/IOException
    //   2	427	456	finally
    //   457	465	468	java/io/IOException
    //   485	493	496	java/io/IOException
  }
  
  private void logData()
  {
    Runnable logger = new Runnable()
    {
      public void run()
      {
        FileLogger.this.writeValues();
      }
    };
    final ScheduledFuture<?> loggerHandle = this.scheduler.scheduleAtFixedRate(logger, 0L, 5L, TimeUnit.SECONDS);
    this.scheduler.schedule(new Runnable()
    {
      public void run()
      {
        loggerHandle.cancel(true);
      }
    }, 3600L, TimeUnit.SECONDS);
  }
}
