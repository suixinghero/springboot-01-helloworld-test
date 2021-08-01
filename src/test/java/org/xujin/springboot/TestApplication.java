package org.xujin.springboot;

import com.alibaba.fastjson.JSON;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;
import org.xujin.springboot.config.BeanConfig;
import org.xujin.springboot.service.BaseService;
import org.xujin.springboot.service.Hello1Service;


import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author suixing
 * @date 2020-08-25-4:15 PM
 */
@SpringBootTest(classes = {BeanConfig.class, HelloWorldMainApplication.class})
@RunWith(SpringRunner.class)
public class TestApplication {
    @Resource
    private ApplicationContext applicationContext;
    @Resource
    private Hello1Service hello1Service;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    boolean a;

    @Test
    public void testRightPushAll() {
        stringRedisTemplate.delete("xujin_demo");
        List<String> strings = new ArrayList<String>();
        System.out.println(stringRedisTemplate.opsForList().rightPushAll("xujin_demo", strings));
        System.out.println(stringRedisTemplate.opsForList().size("xujin_demo"));
        stringRedisTemplate.expire("xujin_demo", 60 * 60L, TimeUnit.SECONDS);
    }

    @Test
    public void test() {
        Set<String> strings = stringRedisTemplate.keys("sui:xing:*");
        System.out.println(strings);
    }

    @Test
    public void test01() {
        Set<ZSetOperations.TypedTuple<String>> suixing_zset = stringRedisTemplate.opsForZSet().reverseRangeWithScores("suixing_zset", 0L, -1L);
        for (ZSetOperations.TypedTuple<String> tuple : suixing_zset) {
            System.out.println(tuple.getScore());
            System.out.println(tuple.getValue());
        }
    }

    @Test
    public void test2() {
        List<String> strings = stringRedisTemplate.opsForList().range("fsafsfsfd",0L,-1L);
        System.out.println(strings);
    }

    @Test
    public void test3() {
        List<Object> objects = stringRedisTemplate.executePipelined(new RedisCallback<Set<RedisZSetCommands.Tuple>>() {
            @Override
            public Set<RedisZSetCommands.Tuple> doInRedis(RedisConnection connection) throws DataAccessException {
                connection.zRevRangeWithScores("sui:xing:zset".getBytes(), 0, -1);
                return null;
            }
        });
    }

    @Test
    public void test4() {
        String s = "[10690085,11307434,11312056,10820124,11307435,11248895,11221653,11322155,11306312,11244439,1342714,306528,10695075,10839183,11248191,11221044,10976956,11100270,10976724,11236278,11244177,10765168,11307413,10842865,1384849,11244640,11306337,1220507,10833455,10091490,10841941,10609030,10827021,1264448,11246455,11235161,11311418,10775564,10834908,10600179,11100092,10829426,11231757,10980197,10456370,10555437,1223280,10991354,11236489,10980203,11225091,11246632,11105045,10750069,10990873,10839184,10505550,10975011,10540502,11243709,10130007,421140,10835588,1118335,10925087,10775077,10821278,10750085,10842837,11244430,10835707,227791,11321269,10971487,10755076,11321763,10830209,10473239,10835367,10849127,1364807,11226695,10827553,10822386,11115168,10602771,11035152,10996271,10975226,10467686,11230194,11248535,10456936,10690610,10755719,11244980,10995701,10820341,1585861,10832744,11120259,10350244,10900334,11236636,10841371,10421963,11248214,11100353,11220000,10775032,10905146,10971423,10606460,10843235,1282346,11220982,10842800,391843,10991403,10340118,10995458,10458080,11322039,10825606,10605278,10900347,10422915,311585,10986666,10760124,1439277,11245164,11230189,10456999,10421388,10848170,10841318,11307448,10511137,10848249,409074,10540061,10986840,10900336,10844625,10750109,11248217,1556751,10845234,10822831,10834063,10458762,10832616,10976705,1091479,10847858,11249792,10975716,10838851,10472919,10985159,10832991,308657,10185472,10466124,10820563,10829581,11243847,11220551,10830139,10622144,10991317,10461081,10535334,10847331,178754,292639,11220635,11050677,980283,11235082,11231607,1214093,11115234,828303,1173704,10473979,10613398,11221732,10420520,11220322,10417778,10837780,11040368,11307042,10623261,11228644,839252,10695593,11322448,10836598,11245558,11307455,10715066,11095371,10410755,10505467,10608028,1383817,10822223,1450373,10615026,806100,10467455,11120404,10840473,10530438,11225040,130991,1264456,10828830,10715691,10760577,10996285,1425116,10820196,10705050,10114057,11095048,10750052,10985860,10620240,11095302,10466961,10834498,11220165,11226697,10827343,10848383,11115783,10770003,10605606,10463699,11220984,10833371,10987320,10468069,10608377,10822975,10833363,10612170,10844672,10834844,10622129,11100820,10618449,11100529,10837923,10468693,10990262,977153,10461944,11220988,10085632,11225517,10715206,10540182,10458275,11035725,10775478,10840575,11120409,10828179,10825027,10839163,10715507,10622569,10089182,10831605,10462796,10460265,10461979,10832480,10827707,11105288,10829120,332845,10833997,1318115,10612740,10831656,10848567,10920203,11105283,1157694,10847599,10265259,11095165,1177448,10830693,10829175,10422327,10842843,726354,10755521,10920030,10603745,10610345,10849796,10690447,383551,364727,11227258,10468696,1224458,10822232,1187361,187883,331213,10603676,10606798,10468043,11055151,10605061,10592014,1238070,1158883,10770627,11228058,10821260,1205025,10976492,10613219,10715532,10845058,641,10991819,10613313,10104875,867928,10555432,10506206,10590050,428155,10840020,212481,858147,10456741,10590393,10843830,714867,10540575,10915079,10466963,10592415,10844463,10530442,10462109,10088310,10821180,10086812,320004,159862,10700050,11095304,10613873,10395653,155155,10610306,11240338,10715328,10607206,1472156,192737,10607586,10834857,10986858,10622152,1132600,1607693,10505521,1159845,11100078,10456658,100680,10545490,1424784,977802,307505,10842320,10972026,10511266,10849146,10976600,10602564,1051332,10466033,10462959,1457578,320556,314037,10690430,10715059,425548,923468,100880,10608327,242215,339840,131811,10462396,172858,10422597,160250,10511140,10511483,10981663,10610102,181493,653295,10839632,248215,10838372,253099,10900535,122333,1110890,986803,10824331,10103857,10837625,230247,10837569,10823439,131781,11040100,10471913,1244896,10820458,1110750,10845196,10468422,320643,10540206,410072,10617416,10472700,10395500,10820559,1465478,1586002,414687,10839748,409322,10991975,10849075,1388905,10837793,10976425,10472879,617008,10608896,10094037,125905,11248567,10848455,364799,10700490,10700253,507402,10466800,10991064,187936,423159,10971160,10416697,11220979,10715577,10422928,10462868,10463175,1250041,10471918,1191725,11306840,518063,11249145,881995,1300729,203079,780651,1006475,205491,1298468,10760639,10345081,1294565,1084396,955646,10829046,10545355,1393412,10422797,10991933,1389307,10107842,10400620,281502,944030,10622686,10603777,10690594,11095300,491764,11110093,10710576,254905,10846053,268670,1161107,911750,843704,1389304,10837801,10555409,10971957,105649,10612375,10823921,10460240,11310440,10980439,10827342,213350,10457834,10473738,125498,868858,10612243,1435621,999777,10462325,11100291,124048,908673,338306,10715547,1081656,10465009,221068,10700582,10422874,10705509,10467964,10990205,10990276,1183828,112649,852845,298590,10417179,213463,1084331,10770580,920442,11100045,1004964,1024335,10422373,10458373,1182221,10090533,101417,1191098,10613748,464095,10690580,138272,11246639,10822858,10695064,200594,10466893,105382,268664,1518002,342782,1175293,10750369,1419830,1077278,122865,124683,1376577,10827557,11235164,10690131,10420169,882942,10468628,294716,284317,201267,10097941,465978,10976077,10847704,1173601,10115423,1148129,1430663,10094036,420606,1179600,10456156,909203,170681,927496,435117,1495143,278440,1111439,1364828,104726,1153791,167374,1457311,1080537,1197754,271566,212374,1248227,330323,871421,318701,10835674,239248,473322,280065,1335021,10835140,979766,121262,10108872,159211,254871,230046,1173956,266067,652572,1584525,1038617,929339,954667,476062,10981889,10613314,957908,965861,783726,10760234,300716,10986023,10456916,1169560,773250,10842505,1418467,216661,10995745,217661,10845625,1165059,234344,10094444,1169919,1155800,920340,937978,319955,689809,223149,1031,10417218,170655,1341994,1178929,10981443,10466515,10465917,100473,1505151,437315,1338749,1110899,1345061,1180657,876475,1346249,1205024,10623977,10330356,909794,1462453,335831,1479354,100621,1215308,10270739,10910486,1083234,120768,1137539,230127,10822526,1119399,407852,1014149,10617525,1475044,1552385,461602,196644,10506225,1455805,1250840,1485514,1535281,10990857,302843,205426,10095917,852956,247168,105296,130632,527811,10175096,969478,461913,929845,11245444,344517,205910,1184114,1584239,725838,117442,1028293,128525,690960,724724,10835454,116197,783729,100078,107,10285520,141877,10603741,133571,106013,132425,116071,104435,105370,200912,887786,10111536,117441,10467035,10109720,10828764,123069,1052752,148277,10981521,1288760,206409,132059,190511,1084404,1093805,974891,522848,122336,10821635,407029,282252,10466240,10108927,1124916,856056,100522,132426,1512225,219905,1076910,240609,10506022,200410,1424543,419603,1268425,1052838,10821378,10848025,10755570,10510593,410037,1117120,911883,117875,1085008,219322,349293,100661,1418519,638056,284329,10111069,502006,10827957,10605516,148410,105647,213300,10468877,318082,108204,10455223,1301374,10505352,264251,126230,881919,957541,387204,634769,127972,1369356,130811,226844,420886,123301,937428,131791,529935,1404462,10505514,132450,230101,10395274,1540314,1522578,1309514,10510628,223381,243976,233763,10086089,1159801,456510,1264514,910272,332421,1488736,1025788,110661,1516161,148595,170806,201191,123917,963447,232647,1619138,1028152,222766,119967,11045271,115611,10991642,420335,10829571,108997,240220,10755545,10591036,1500070,473374,10622416,222808,10089158,10618979,10422878,1314224,175902,236073,137543,998819,10190355,100837,1527633,259292,10832373,897346,110925,202926,1306181,10462830,910076,394473,202548,127211,10975212,200869,1367085,545673,131652,10108615,1144204,139772,1137362,1198528,10457954,269012,246681,10458654,395927,107679,1232596,269624,101303,1003713,1284727,131934,132646,100314,1224099,247179,10840581,321716,1557379,1139685,224689,10087505,107809,10112513,126736,214725,108994,401238,318496,1398492,162374,234188,1481949,1378264,1512493,111288,100983,309894,108995,107829,225405,295358,108122,1335026,1087932,220982,313660,254061,629466,1526608,866216,129511,459757,215542,264534,120881,10995063,236590,135386,317430,1349386,1166125,1362450,103123,212804,1052981,226753,1492614,136177,10457264,149614,105601,1105633,225479,240816]";
        List<Long> longs = JSON.parseArray(s, Long.class);
        stringRedisTemplate.opsForValue().set("xu:jin:aaa", JSON.toJSONString(longs));
        System.out.println(JSON.toJSONString(longs));
    }

    public static void main(String[] args) {
        int i = 1;
        double a = i*100;
        System.out.println(a);

    }
    @Test
    public void test5() {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        boolean contains = integers.contains(null);
        Personal personal = new Personal();
        personal.setAge(22L);
        personal.setName("fsfds");
        hello1Service.say(personal);
    }
    @Test
    public void a() {
        String dddd = stringRedisTemplate.opsForValue().getAndSet("suixing:0924", "dddd");
        System.out.println(dddd);
    }

}
