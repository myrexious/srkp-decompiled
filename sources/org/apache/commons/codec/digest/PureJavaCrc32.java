package org.apache.commons.codec.digest;

import java.util.zip.Checksum;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes2.dex */
public class PureJavaCrc32 implements Checksum {
    private static final int[] T = {0, 1996959894, -301047508, -1727442502, 124634137, 1886057615, -379345611, -1637575261, 249268274, 2044508324, -522852066, -1747789432, 162941995, 2125561021, -407360249, -1866523247, 498536548, 1789927666, -205950648, -2067906082, 450548861, 1843258603, -187386543, -2083289657, 325883990, 1684777152, -43845254, -1973040660, 335633487, 1661365465, -99664541, -1928851979, 997073096, 1281953886, -715111964, -1570279054, 1006888145, 1258607687, -770865667, -1526024853, 901097722, 1119000684, -608450090, -1396901568, 853044451, 1172266101, -589951537, -1412350631, 651767980, 1373503546, -925412992, -1076862698, 565507253, 1454621731, -809855591, -1195530993, 671266974, 1594198024, -972236366, -1324619484, 795835527, 1483230225, -1050600021, -1234817731, 1994146192, 31158534, -1731059524, -271249366, 1907459465, 112637215, -1614814043, -390540237, 2013776290, 251722036, -1777751922, -519137256, 2137656763, 141376813, -1855689577, -429695999, 1802195444, 476864866, -2056965928, -228458418, 1812370925, 453092731, -2113342271, -183516073, 1706088902, 314042704, -1950435094, -54949764, 1658658271, 366619977, -1932296973, -69972891, 1303535960, 984961486, -1547960204, -725929758, 1256170817, 1037604311, -1529756563, -740887301, 1131014506, 879679996, -1385723834, -631195440, 1141124467, 855842277, -1442165665, -586318647, 1342533948, 654459306, -1106571248, -921952122, 1466479909, 544179635, -1184443383, -832445281, 1591671054, 702138776, -1328506846, -942167884, 1504918807, 783551873, -1212326853, -1061524307, -306674912, -1698712650, 62317068, 1957810842, -355121351, -1647151185, 81470997, 1943803523, -480048366, -1805370492, 225274430, 2053790376, -468791541, -1828061283, 167816743, 2097651377, -267414716, -2029476910, 503444072, 1762050814, -144550051, -2140837941, 426522225, 1852507879, -19653770, -1982649376, 282753626, 1742555852, -105259153, -1900089351, 397917763, 1622183637, -690576408, -1580100738, 953729732, 1340076626, -776247311, -1497606297, 1068828381, 1219638859, -670225446, -1358292148, 906185462, 1090812512, -547295293, -1469587627, 829329135, 1181335161, -882789492, -1134132454, 628085408, 1382605366, -871598187, -1156888829, 570562233, 1426400815, -977650754, -1296233688, 733239954, 1555261956, -1026031705, -1244606671, 752459403, 1541320221, -1687895376, -328994266, 1969922972, 40735498, -1677130071, -351390145, 1913087877, 83908371, -1782625662, -491226604, 2075208622, 213261112, -1831694693, -438977011, 2094854071, 198958881, -2032938284, -237706686, 1759359992, 534414190, -2118248755, -155638181, 1873836001, 414664567, -2012718362, -15766928, 1711684554, 285281116, -1889165569, -127750551, 1634467795, 376229701, -1609899400, -686959890, 1308918612, 956543938, -1486412191, -799009033, 1231636301, 1047427035, -1362007478, -640263460, 1088359270, 936918000, -1447252397, -558129467, 1202900863, 817233897, -1111625188, -893730166, 1404277552, 615818150, -1160759803, -841546093, 1423857449, 601450431, -1285129682, -1000256840, 1567103746, 711928724, -1274298825, -1022587231, 1510334235, 755167117, 0, 421212481, 842424962, 724390851, 1684849924, 2105013317, 1448781702, 1329698503, -925267448, -775767223, -84940662, -470492725, -1397403892, -1246855603, -1635570290, -2020074289, 1254232657, 1406739216, 2029285587, 1643069842, 783210325, 934667796, 479770071, 92505238, -2112120743, -1694455528, -1339163941, -1456026726, -428384931, -9671652, -733921313, -849736034, -1786501982, -1935731229, -1481488864, -1096190111, -236396122, -386674457, -1008827612, -624577947, 1566420650, 1145479147, 1869335592, 1987116393, 959540142, 539646703, 185010476, 303839341, -549046541, -966981710, -311405455, -194288336, -1154812937, -1573797194, -1994616459, -1878548428, 396344571, 243568058, 631889529, 1018359608, 1945336319, 1793607870, 1103436669, 1490954812, -260485371, -379421116, -1034998393, -615244602, -1810527743, -1928414400, -1507596157, -1086793278, 950060301, 565965900, 177645455, 328046286, 1556873225, 1171730760, 1861902987, 2011255754, -1162125996, -1549767659, -2004009002, -1852436841, -556296112, -942888687, -320734510, -168113261, 1919080284, 1803150877, 1079293406, 1498383519, 370020952, 253043481, 607678682, 1025720731, 1711106983, 2095471334, 1472923941, 1322268772, 26324643, 411738082, 866634785, 717028704, -1390091857, -1270886162, -1626176723, -2046184852, -918018901, -799861270, -75610583, -496666776, 792689142, 908347575, 487136116, 68299317, 1263779058, 1380486579, 2036719216, 1618931505, -404294658, -16923969, -707751556, -859070403, -2088093958, -1701771333, -1313057672, -1465424583, 998479947, 580430090, 162921161, 279890824, 1609522511, 1190423566, 1842954189, 1958874764, -212200893, -364829950, -1049857855, -663273088, -1758013625, -1909594618, -1526680123, -1139047292, 1900120602, 1750776667, 1131931800, 1517083097, 355290910, 204897887, 656092572, 1040194781, -1181220846, -1602014893, -1951505776, -1833610287, -571161322, -990907305, -272455788, -153512235, -1375224599, -1222865496, -1674453397, -2060783830, -898926099, -747616084, -128115857, -515495378, 1725839073, 2143618976, 1424512099, 1307796770, 45282277, 464110244, 813994343, 698327078, -456806728, -35741703, -688665542, -806814341, -2136380484, -1716364547, -1298200258, -1417398145, 740041904, 889656817, 506086962, 120682355, 1215357364, 1366020341, 2051441462, 1667084919, -872753330, -756947441, -104024628, -522746739, -1349119414, -1232264437, -1650429752, -2068102775, 52649286, 439905287, 823476164, 672009861, 1733269570, 2119477507, 1434057408, 1281543041, -2126985953, -1742474146, -1290885219, -1441425700, -447479781, -61918886, -681418087, -830909480, 1239502615, 1358593622, 2077699477, 1657543892, 764250643, 882293586, 532408465, 111204816, 1585378284, 1197851309, 1816695150, 1968414767, 974272232, 587794345, 136598634, 289367339, -1767409180, -1883486043, -1533994138, -1115018713, -221528864, -338653791, -1057104286, -639176925, 347922877, 229101820, 646611775, 1066513022, 1892689081, 1774917112, 1122387515, 1543337850, -597333067, -981574924, -296548041, -146261898, -1207325007, -1592614928, -1975530445, -1826292366, 0, 29518391, 59036782, 38190681, 118073564, 114017003, 76381362, 89069189, 236147128, 265370511, 228034006, 206958561, 152762724, 148411219, 178138378, 190596925, 472294256, 501532999, 530741022, 509615401, 456068012, 451764635, 413917122, 426358261, 305525448, 334993663, 296822438, 275991697, 356276756, 352202787, 381193850, 393929805, 944588512, 965684439, 1003065998, 973863097, 1061482044, 1049003019, 1019230802, 1023561829, 912136024, 933002607, 903529270, 874031361, 827834244, 815125939, 852716522, 856752605, 611050896, 631869351, 669987326, 640506825, 593644876, 580921211, 551983394, 556069653, 712553512, 733666847, 704405574, 675154545, 762387700, 749958851, 787859610, 792175277, 1889177024, 1901651959, 1931368878, 1927033753, 2006131996, 1985040171, 1947726194, 1976933189, 2122964088, 2135668303, 2098006038, 2093965857, 2038461604, 2017599123, 2047123658, 2076625661, 1824272048, 1836991623, 1866005214, 1861914857, 1807058540, 1786244187, 1748062722, 1777547317, 1655668488, 1668093247, 1630251878, 1625932113, 1705433044, 1684323811, 1713505210, 1742760333, 1222101792, 1226154263, 1263738702, 1251046777, 1339974652, 1310460363, 1281013650, 1301863845, 1187289752, 1191637167, 1161842422, 1149379777, 1103966788, 1074747507, 1112139306, 1133218845, 1425107024, 1429406311, 1467333694, 1454888457, 1408811148, 1379576507, 1350309090, 1371438805, 1524775400, 1528845279, 1499917702, 1487177649, 1575719220, 1546255107, 1584350554, 1605185389, -516613248, -520654409, -491663378, -478960167, -432229540, -402728597, -440899790, -461763323, -282703304, -287039473, -324886954, -312413087, -399514908, -370308909, -341100918, -362193731, -49039120, -53357881, -23630690, -11204951, -98955220, -69699045, -107035582, -128143755, -218044088, -222133377, -259769050, -247048431, -200719980, -171234397, -141715974, -162529331, -646423200, -658884777, -620984050, -616635591, -562956868, -541876341, -571137582, -600355867, -680850216, -693541137, -722478922, -718425471, -798841852, -777990605, -739872662, -769385891, -983630320, -996371417, -958780802, -954711991, -1034463540, -1013629701, -1043103070, -1072568171, -884101208, -896547425, -926319674, -922021391, -867956876, -846828221, -809446630, -838682323, -1850763712, -1871840137, -1842658770, -1813436391, -1767489892, -1755032405, -1792873742, -1797226299, -1615017992, -1635865137, -1674046570, -1644529247, -1732939996, -1720253165, -1691239606, -1695297155, -1920387792, -1941217529, -1911692962, -1882223767, -1971282452, -1958545445, -1996207742, -2000280651, -2087033720, -2108158273, -2145472282, -2116232495, -2070688684, -2058246557, -2028529606, -2032831987, -1444753248, -1474250089, -1436154674, -1415287047, -1360299908, -1356262837, -1385190382, -1397897691, -1477345000, -1506546897, -1535814282, -1514717375, -1594349116, -1590017037, -1552089686, -1564567651, -1245416496, -1274668569, -1237276738, -1216164471, -1295131892, -1290817221, -1320611998, -1333041835, -1143528856, -1173010337, -1202457082, -1181639631, -1126266188, -1122180989, -1084596518, -1097321235, 0, -1195612315, -1442199413, 313896942, -1889364137, 937357362, 627793884, -1646839623, -978048785, 2097696650, 1874714724, -687765759, 1255587768, -227878691, -522225869, 1482887254, 1343838111, -391827206, -99573996, 1118632049, -545537848, 1741137837, 1970407491, -842109146, -1783791760, 756094997, 1067759611, -2028416866, 449832999, -1569484990, -1329192788, 142231497, -1607291074, 412010587, 171665333, -1299775280, 793786473, -1746116852, -2057703198, 1038456711, 1703315409, -583343948, -812691622, 1999841343, -354152314, 1381529571, 1089329165, -128860312, -265553759, 1217896388, 1512189994, -492939441, 2135519222, -940242797, -717183107, 1845280792, 899665998, -1927039189, -1617553211, 657096608, -1157806311, 37822588, 284462994, -1471616777, -1693165507, 598228824, 824021174, -1985873965, 343330666, -1396004849, -1098971167, 113467524, 1587572946, -434366537, -190203815, 1276501820, -775755899, 1769898208, 2076913422, -1015592853, -888336478, 1941006535, 1627703081, -642211764, 1148164341, -53215344, -295284610, 1457141531, 247015245, -1241169880, -1531908154, 470583459, -2116308966, 963106687, 735213713, -1821499404, 992409347, -2087022490, -1859174520, 697522413, -1270587308, 217581361, 508405983, -1494102086, -23928852, 1177467017, 1419450215, -332959742, 1911572667, -917753890, -604405712, 1665525589, 1799331996, -746338311, -1053399017, 2039091058, -463652917, 1558270126, 1314193216, -152528859, -1366587277, 372764438, 75645176, -1136777315, 568925988, -1722451903, -1948198993, 861712586, -312887749, 1441124702, 1196457648, -1304107, 1648042348, -628668919, -936187417, 1888390786, 686661332, -1873675855, -2098964897, 978858298, -1483798141, 523464422, 226935048, -1254447507, -1119821404, 100435649, 390670639, -1342878134, 841119475, -1969352298, -1741963656, 546822429, 2029308235, -1068978642, -755170880, 1782671013, -141140452, 1328167289, 1570739863, -450629134, 1298864389, -170426784, -412954226, 1608431339, -1039561134, 2058742071, 1744848601, -792976964, -1998638614, 811816591, 584513889, -1704288764, 129869501, -1090403880, -1380684234, 352848211, 494030490, -1513215489, -1216641519, 264757620, -1844389427, 715964072, 941166918, -2136639965, -658086283, 1618608400, 1926213374, -898381413, 1470427426, -283601337, -38979159, 1158766284, 1984818694, -823031453, -599513459, 1693991400, -114329263, 1100160564, 1395044826, -342174017, -1275476247, 189112716, 435162722, -1588827897, 1016811966, -2077804837, -1768777419, 774831696, 643086745, -1628905732, -1940033262, 887166583, -1456066866, 294275499, 54519365, -1149009632, -471821962, 1532818963, 1240029693, -246071656, 1820460577, -734109372, -963916118, 2117577167, -696303304, 1858283101, 2088143283, -993333546, 1495127663, -509497078, -216785180, 1269332353, 
    332098007, -1418260814, -1178427044, 25085497, -1666580864, 605395429, 916469259, -1910746770, -2040129881, 1054503362, 745528876, -1798063799, 151290352, -1313282411, -1559410309, 464596510, 1137851976, -76654291, -371460413, 1365741990, -860837601, 1946996346, 1723425172, -570095887, 0, 1029712304, 2059424608, 1201699536, -176118080, -924807312, -1891568224, -1306469360, 812665793, 219177585, 1253054625, 2010132753, -974066431, -124730191, -1087324575, -2108647471, 1625331586, 1568718386, 438355170, 658566482, -1788858046, -1476388622, -274701790, -759149678, 1351670851, 1844508147, 709922595, 389064339, -1525646717, -1737469133, -540005917, -491782061, -1044304124, -56555852, -1157530524, -2040441388, 876710340, 153198708, 1317132964, 1944187668, -240032571, -858698379, -1955514459, -1240392171, 70369797, 961670069, 2129760613, 1133623509, -1591625594, -1673424586, -605951002, -427703722, 1419845190, 1774270454, 778128678, 318858390, -1856900281, -1406018825, -342777817, -688813673, 1691440519, 1504803895, 504432359, 594620247, 1492342857, 1704161785, 573770537, 525542041, -1384907127, -1877747911, -676090391, -355236775, 1753420680, 1440954936, 306397416, 790849880, -1660701368, -1604084488, -406591960, -626798696, 940822475, 91481723, 1121164459, 2142483739, -845977333, -252493637, -1219282325, -1976364069, 140739594, 889433530, 1923340138, 1338244826, -35446070, -1065153670, -2027720278, -1169991654, -1724745907, -1538105603, -470670291, -560853603, 1823658381, 1372780605, 376603373, 722643805, -1455276916, -1809705668, -746426388, -287160740, 1556257356, 1638052860, 637716780, 459464860, -103620401, -994915969, -2095926353, -1099785697, 206718479, 825388991, 1989285231, 1274166495, -912086258, -188579138, -1285359506, -1912417826, 1008864718, 21111934, 1189240494, 2072147742, -1310281582, -1937336030, -886643726, -163132862, 1147541074, 2030452706, 1051084082, 63335554, -2120811693, -1124674845, -78206925, -969506429, 1947622803, 1232499747, 248909555, 867575619, -788125936, -328855904, -1413057424, -1767481920, 612794832, 434546784, 1581699760, 1663499008, -512332591, -602520223, -1682554959, -1495919103, 351717905, 697754529, 1849071985, 1398190273, 1881644950, 1296545318, 182963446, 931652934, -2052638378, -1194913562, -9999818, -1039711354, 1079497815, 2100821479, 983009079, 133672583, -1244171625, -2001249497, -820567561, -227080121, 281479188, 765927844, 1778867060, 1466397380, -448287020, -668498076, -1618477644, -1561865212, 548881365, 500656741, 1517752501, 1729575173, -717757163, -396899163, -1342720395, -1835556923, -384440101, -730480277, -1814709317, -1363832309, 479546907, 569730987, 1716854139, 1530213579, -647650534, -469398870, -1549406086, -1631200822, 753206746, 293940330, 1445287610, 1799716618, -1980399783, -1265281303, -214619079, -833288823, 2088098201, 1091956777, 112560889, 1003856713, -1182452584, -2065359576, -1018861576, -31109560, 1275433560, 1902492648, 918929720, 195422344, 685033439, 364179055, 1377080511, 1869921551, -581672673, -533444433, -1483459969, -1695278129, 413436958, 633644462, 1650777982, 1594160846, -316396834, -800849042, -1746634306, -1434169330, 1211387997, 1968470509, 854852413, 261368461, -1112213859, -2133532883, -948656643, -99316659, 2017729436, 1160000044, 42223868, 1071931724, -1916486308, -1331391252, -150671812, -899364980, 0, -883108955, 1304994059, -2037091666, -1684979178, 1355649459, -698752227, 486879416, -330071443, 655315400, -1583668378, 1791488195, 2009251963, -1130490914, 973758832, -245976363, 64357019, -930426562, 1310630800, -2059243467, -1740160883, 1394316072, -711990906, 517157411, -276463370, 618222419, -1572003331, 1762783832, 1947517664, -1085796027, 970744811, -226447282, 128714038, -856631661, 1248109629, -2127005800, -1673705696, 1466012805, -772413909, 447296910, -335575205, 547575038, -1506335152, 1835791861, 1886307661, -1154345240, 1034314822, -151341085, 75106221, -819538936, 1236444838, -2098301693, -1611971141, 1421317662, -769399632, 427767573, -399931968, 594892389, -1511971637, 1857943406, 1941489622, -1193012109, 1047553757, -181619336, 257428076, -1006315063, 1116777319, -1983088446, -1798748038, 1603640287, -654186127, 308099796, -485783551, 676813732, -1362941686, 1704983215, 2023410199, -1278862926, 894593820, -32589639, 210634999, -942482606, 1095150076, -1977976231, -1759556895, 1547934020, -623383574, 294336591, -522351974, 729897279, -1391121519, 1716123700, 2068629644, -1341121751, 914647431, -36128222, 150212442, -1012343553, 1161604689, -1906278924, -1822077620, 1480171241, -559027129, 368132066, -458781385, 805002898, -1452331972, 1647574937, 2134298401, -1268114300, 855535146, -106775153, 186781121, -1065427356, 1189784778, -1917419665, -1867296809, 1542429810, -579080484, 371670393, -411988052, 741170185, -1430704473, 1642462466, 2095107514, -1212408289, 824732849, -93012204, 514856152, -705902723, 1400419795, -1742444938, -2061412658, 1316849003, -924190779, 62202976, -219965771, 968836368, -1087686722, 1954014235, 1769133219, -1574041850, 616199592, -270096883, 493229635, -700791322, 1353627464, -1678613267, -2030611371, 1303087088, -885000866, 6498043, -248146898, 979978123, -1124256475, 2007099008, 1789187640, -1577581155, 661419827, -332356458, 421269998, -767507893, 1423225061, -1618451648, -2104667144, 1238466653, -817499405, 68755798, -179334269, 1041448998, -1199099256, 1943789869, 1860096405, -1518206416, 588673182, -397761733, 449450869, -778649392, 1459794558, -1671536165, -2124721821, 1242006214, -862719896, 131015629, -157708008, 1036337853, -1152307181, 1879958454, 1829294862, -1504444245, 549483013, -342056544, 300424884, -625685231, 1545650111, -1753453542, -1971757918, 1092980487, -944636503, 216870412, -38036263, 921128828, -1334624814, 2066738807, 1714085583, -1384772246, 736264132, -524374943, 306060335, -647835766, 1610005796, -1800769919, -1984995783, 1123257756, -999817422, 255536279, -26370494, 892423655, -1281015991, 2029645036, 1711070292, -1365241871, 674528607, -479678726, 373562242, -585578457, 1535949449, -1865389780, -1915397740, 1183418929, -1071777633, 188820282, -99116561, 827017802, -1210107676, 2089020225, 1636228089, -1428551588, 743340786, -418207401, 361896217, -556873028, 1482340370, -1828295753, -1912382705, 1163888810, -1010042364, 144124321, -104752268, 849168593, -1274463617, 2136336858, 1649465698, -1458828601, 798521449, -456873012, 0, -1502147660, -1751183063, 837294749, -196140013, 1379413927, 1674589498, -978895218, 871321191, -1785182765, -1536139442, 34034938, -945788300, 1641505216, 1346337629, -163024663, 1742642382, -1045850246, -264139289, 1446413907, -1819166499, 904311657, 68069876, -1569086912, 1412551337, -230237923, -1011956864, 1708771380, -1602292038, 101317902, 937551763, -1852380121, -809682532, 1774858792, 1478633653, -27974911, 1005723023, -1652222405, -1402139482, 169477906, -61704197, 1512406095, 1808623314, -843420314, 136139752, -1368762276, -1618853183, 972376437, -1469864622, 236236518, 1073525883, -1718894641, 1546420545, -94663947, -877424536, 1841601500, -1685263563, 1039917185, 202635804, -1436225112, 1875103526, -910900078, -128131569, 1579931067, 1141601657, -495157555, -745249712, 1977839588, -1337699990, 372464350, 668680259, -2119414793, 2011446046, -778882902, -528799177, 1175200131, -2085937395, 635180217, 338955812, -1304230512, 601221559, -2052922877, -1270155106, 306049834, -677720668, 1911408144, 1074125965, -428681415, 272279504, -1236423580, -2019182855, 567459149, -462060605, 1107462263, 1944752874, -711091874, -1950987035, 767641425, 472473036, -1168222600, 2147051766, -644979902, -395937313, 1309766251, -1202126206, 506333494, 801510315, -1984882657, 1276520081, -362730203, -611764296, 2113813516, -328675285, 1243601823, 2079834370, -578762058, 405271608, -1101987956, -1883708143, 701492901, -544760244, 2045810168, 1209569125, -294681391, 734575199, -1916816917, -1135105162, 438345922, -2011763982, 778166598, 529136603, -1174474641, 2086260449, -634469035, -339288120, 1303499900, -1141267307, 495890209, 744928700, -1978548728, 1337360518, -373191886, -668364369, 2120129051, -272075204, 1237286280, 2018993941, -568300383, 461853231, -1108321893, -1944567034, 711936178, -601409445, 2052076527, 1270360434, -305192250, 677911624, -1910564868, -1074328223, 427820757, 1202443118, -505620262, -801848761, 1984154099, -1276840067, 362020041, 612099668, -2113081888, 1950653705, -768371011, -472151008, 1168934804, -2146715366, 645706414, 395618355, -1310481529, 544559008, -2046671852, -1209377143, 295523645, -734368845, 1917673479, 1134918298, -439193298, 328860103, -1242756493, -2080042770, 577903450, -405461548, 1101147744, 1883911421, -700629175, -870473845, 1785369663, 1535282850, -34241258, 944946072, -1641697236, -1345475919, 163225861, -863764, 1501944408, 1752023237, -837104783, 196998655, -1379205557, -1675434794, 978710370, -1413283003, 229902577, 1012666988, -1708451368, 1603020630, -100979486, -938264961, 1852063179, -1741927134, 1046169238, 263412747, -1446750273, 1818454321, -904633723, -67340264, 1569420204, 60859927, -1512591965, -1807763650, 843627658, -135298556, 1368951216, 1617990445, -972580711, 810543216, -1774656572, -1479476903, 27783917, -1006580637, 1652017111, 1402985802, -169289986, 1685994201, -1039584915, -203346960, 1435902020, -1875829046, 910562686, 128847843, -1579613097, 1469150398, -236552438, -1072798313, 1719234083, -1545711443, 94984985, 876691844, -1841935824, 0, -861273954, 1109723005, -1903228957, -2075521286, 1222643300, -965801593, 180685081, -739959883, 525277995, -1849680696, 1567235158, 1471092047, -1694165551, 361370162, -652209492, 2092642603, -1341050443, 1050555990, -231459128, -118407215, 878395215, -1160496980, 1987983410, -1352783202, 1676945920, -310694429, 567356797, 722740324, -406969094, 1764827929, -1516559481, -109682090, 903635656, -1152162517, 2012833205, 2101111980, -1315541966, 1058630609, -206345393, 714308067, -432440963, 1756790430, -1541636608, -1361479911, 1651734407, -319000476, 542535930, -2050141315, 1231508451, -941075456, 188896414, 25648519, -852665063, 1134713594, -1895277980, 1445480648, -1702737834, 336416693, -660123861, -765311438, 516441772, -1874378417, 1559052753, 698204909, -449330573, 1807271312, -1491942130, -1378366441, 1635634313, -269300886, 593021940, -92743336, 919787974, -1201807835, 1962401467, 2117261218, -1298606276, 1008193759, -255995839, 1428616134, -1718815912, 386135227, -609618907, -781386436, 499580322, -1823868351, 1608776415, -2033981325, 1248454893, -991498482, 139259792, 42591881, -836508137, 1085071860, -1945706134, -789864261, 474062885, -1831950394, 1583654744, 1419882049, -1744064801, 377792828, -634476126, 51297038, -811287664, 1093385331, -1920877331, -2025540108, 1273935210, -983453047, 164344343, -1404006000, 1627033870, -294283539, 585078387, 672833386, -458186764, 1782552599, -1500145527, 2142603813, -1289778501, 1032883544, -247820858, -67140385, 928351297, -1176861790, 1970307900, 1396409818, -1617853116, 287212199, -575372743, -680424672, 467372990, -1789621155, 1509854403, -2132894097, 1282711281, -1023698670, 240228748, 76845205, -935423989, 1186043880, -1977903242, 796964081, -483740561, 1839575948, -1592806638, -1412777461, 1734392469, -370164362, 625327592, -60444860, 818917338, -1103058887, 1927981223, 2016387518, -1266310880, 973776579, -157243811, -1437735028, 1726474002, -395779855, 616751215, 772270454, -491918872, 1814228491, -1601638763, 2041117753, -1258095449, 999160644, -148374566, -35458365, 826864221, -1077414466, 1936586016, -688466265, 442291769, -1798057510, 1484378436, 1388107869, -1642669885, 278519584, -600580162, 85183762, -910570100, 1194773103, -1952658703, -2124823576, 1307820918, -1015233387, 265733131, 2057717559, -1240709207, 948125770, -198623020, -18069043, 843467091, -1127657808, 1885556270, -1455203198, 1709792284, -345613313, 667704161, 755585656, -509390106, 1865176325, -1551477349, 
    102594076, -893946238, 1144549729, -2003668481, -2108196634, 1325234296, -1066238053, 215514885, -705139287, 424832311, -1747096876, 1534552650, 1370645331, -1659345971, 328688686, -549624656, -2083510943, 1333405183, -1040899556, 224338562, 127544219, -886035707, 1170156774, -1995101064, 1345666772, -1667285430, 303053225, -558221001, -729862098, 416624816, -1772472493, 1525692365, -9759670, 868291796, -1118956745, 1910772649, 2065767088, -1215620562, 956571085, -173138605, 747507711, -534507679, 1856702594, -1576990692, -1463549691, 1684930971, -354351496, 642451174};
    private int crc;

    public PureJavaCrc32() {
        _reset();
    }

    @Override // java.util.zip.Checksum
    public long getValue() {
        return (~this.crc) & BodyPartID.bodyIdMax;
    }

    @Override // java.util.zip.Checksum
    public void reset() {
        _reset();
    }

    private void _reset() {
        this.crc = -1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // java.util.zip.Checksum
    public void update(byte[] bArr, int i, int i2) {
        int i3 = this.crc;
        int i4 = i2 & 7;
        int i5 = (i2 + i) - i4;
        while (i < i5) {
            int i6 = i3 ^ ((((bArr[i] << BuiltinOptions.BatchToSpaceNDOptions) >>> 24) + ((bArr[i + 1] << BuiltinOptions.BatchToSpaceNDOptions) >>> 16)) + (((bArr[i + 2] << BuiltinOptions.BatchToSpaceNDOptions) >>> 8) + (bArr[i + 3] << BuiltinOptions.BatchToSpaceNDOptions)));
            int[] iArr = T;
            i3 = ((iArr[(i6 >>> 24) + 1024] ^ iArr[((i6 << 8) >>> 24) + 1280]) ^ (iArr[((i6 << 24) >>> 24) + 1792] ^ iArr[((i6 << 16) >>> 24) + 1536])) ^ ((iArr[(bArr[i + 7] << BuiltinOptions.BatchToSpaceNDOptions) >>> 24] ^ iArr[((bArr[i + 6] << BuiltinOptions.BatchToSpaceNDOptions) >>> 24) + 256]) ^ (iArr[((bArr[i + 4] << BuiltinOptions.BatchToSpaceNDOptions) >>> 24) + 768] ^ iArr[((bArr[i + 5] << BuiltinOptions.BatchToSpaceNDOptions) >>> 24) + 512]));
            i += 8;
        }
        switch (i4) {
            case 1:
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                break;
            case 2:
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                i++;
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                break;
            case 3:
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                i++;
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                i++;
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                break;
            case 4:
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                i++;
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                i++;
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                i++;
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                break;
            case 5:
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                i++;
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                i++;
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                i++;
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                i++;
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                break;
            case 6:
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                i++;
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                i++;
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                i++;
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                i++;
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                i++;
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                break;
            case 7:
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                i++;
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                i++;
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                i++;
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                i++;
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                i++;
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                i++;
                i3 = (i3 >>> 8) ^ T[((bArr[i] ^ i3) << 24) >>> 24];
                break;
        }
        this.crc = i3;
    }

    @Override // java.util.zip.Checksum
    public final void update(int i) {
        int i2 = this.crc;
        this.crc = T[((i ^ i2) << 24) >>> 24] ^ (i2 >>> 8);
    }
}
