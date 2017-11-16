package project.euler;

import project.euler.util.ArrayUtils;
import project.euler.util.NumberUtils;
import project.euler.util.ResourcesUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OneToTwentyFive {

    public static void main(String... args) {
//        one();
//        two();
//        three();
//        four();
//        five();
//        six();
//        seven();
//        eight();
//        nine();
//        ten();
//        eleven();
//        twelve();
//        thirteen();
//        fourteen();
//        fifteen();
//        sixteen();
//        seventeen();
//        eighteen();
//        nineteen();
//        twenty();
//        twentyOne();
//        twentyTwo();
//        twentyThree();
//        twentyFour();
//        twentyFive();
    }

    private static void one() {
        int sum = IntStream.range(1, 1000)
                .filter(n -> n % 3 == 0 || n % 5 == 0)
                .sum();
        System.out.println(sum);
    }

    private static void two() {
//        starting from #3
        long s = 2;
        long n1 = 1;
        long n2 = 2;
        long n3;
        while (n2 < 4_000_000) {
            n3 = n1 + n2;
            n1 = n2;
            n2 = n3;
            if (n2 % 2 == 0) {
                s += n2;
            }
        }
        System.out.println(s);
    }

    private static void three() {
        System.out.println(threeLargestPrimeFactor(600851475143l));
    }

    private static long threeLargestPrimeFactor(long n) {
        long f = 0;
        long d = 2;
        while (d <= n) {
            if (n % d == 0) {
                f = d;
                n = n / f;
            } else {
                d += (d == 2 ? 1 : 2);
            }
        }

        return f;
    }

    private static void four() {
        long max = 999 * 999;
        while (max > 99 * 99) {
            if (NumberUtils.isPalindrome(max) && isProductOfTwo3dNum(max)) {
                System.out.println(max);
                break;
            }
            max--;
        }
    }

    private static boolean isProductOfTwo3dNum(long n) {
        long d1 = (long) Math.sqrt(n);
        while (d1 > 99) {
            if (n % d1 == 0) {
                long d2 = n / d1;
                if (d2 <= 999 && d2 > 99) {
                    return true;
                }
            }
            d1--;
        }
        return false;
    }

    private static void five() {
        int[] items = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        int mul = 1;
        int d = 2;
        while (d <= 20) {
            if (anyOneDivisible(items, d)) {
                divideIfDividable(items, d);
                mul *= d;
            } else {
                d++;
            }
        }
        for (int item : items) {
            mul *= item;
        }
        System.out.println(mul);
    }

    private static boolean anyOneDivisible(int[] items, int divider) {
        for (int item : items) {
            if (item % divider == 0) {
                return true;
            }
        }
        return false;
    }

    private static void divideIfDividable(int[] items, int divider) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] % divider == 0) {
                items[i] = items[i] / divider;
            }
        }
    }

    private static void six() {
        int sum = IntStream.rangeClosed(1, 100).sum();
        int squareOfSum = square(sum);
        int sumOfSquare = IntStream.rangeClosed(1, 100).map(OneToTwentyFive::square).sum();
        System.out.println("squareOfSum: " + squareOfSum + " sumOfSquare: " + sumOfSquare + " different: " + (squareOfSum - sumOfSquare));
    }

    private static int square(int x) {
        return (int) Math.pow(x, 2);
    }

    private static void seven() {
        System.out.println(nthPrime(10001));
    }

    private static long nthPrime(int n) {
        long[] primes = new long[n];
        int index = 0;
        primes[index] = 2;

        long trying = primes[index] + 1;
        while (index < (n - 1)) {
            if (isPrime(trying, Arrays.copyOf(primes, index + 1))) {
                index++;
                primes[index] = trying;
            }
            trying += 2;
        }
        return primes[n - 1];
    }

    private static boolean isPrime(long n, long[] primesBefore) {
        for (long p : primesBefore) {
            if (n % p == 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPrime(long n, long[] primes, int count) {
        for (int i = 0; i < count; i++) {
            long p = primes[i];
            if (n % p == 0) {
                return false;
            }
        }
        return true;
    }

    private static void eight() {
        String input = "73167176531330624919225119674426574742355349194934" +
                "96983520312774506326239578318016984801869478851843" +
                "85861560789112949495459501737958331952853208805511" +
                "12540698747158523863050715693290963295227443043557" +
                "66896648950445244523161731856403098711121722383113" +
                "62229893423380308135336276614282806444486645238749" +
                "30358907296290491560440772390713810515859307960866" +
                "70172427121883998797908792274921901699720888093776" +
                "65727333001053367881220235421809751254540594752243" +
                "52584907711670556013604839586446706324415722155397" +
                "53697817977846174064955149290862569321978468622482" +
                "83972241375657056057490261407972968652414535100474" +
                "82166370484403199890008895243450658541227588666881" +
                "16427171479924442928230863465674813919123162824586" +
                "17866458359124566529476545682848912883142607690042" +
                "24219022671055626321111109370544217506941658960408" +
                "07198403850962455444362981230987879927244284909188" +
                "84580156166097919133875499200524063689912560717606" +
                "05886116467109405077541002256983155200055935729725" +
                "71636269561882670428252483600823257530420752963450";
        int digits = 13;
        long maxProduct = 0;
        String seq = null;
        for (int i = 0; i < 1000 - digits; i++) {
            long product = product(input.substring(i, i + digits));
            if (product > maxProduct) {
                maxProduct = product;
                seq = input.substring(i, i + digits);
            }
        }
        System.out.println(seq + " " + maxProduct);
    }

    private static long product(String s) {
        long mul = 1;
        for (int i = 0; i < s.length(); i++) {
            int val = Character.getNumericValue(s.charAt(i));
            mul *= val;
        }
        return mul;
    }

    private static void nine() {
        int s = 1000;
        for (int a = 1; a < s / 3; a++) {
            for (int b = a + 1; b < s; b++) {
                int c = s - a - b;
                if (isPythagoreanTriplet(a, b, c)) {
                    System.out.println("a: " + a + " b: " + b + " c: " + c);
                    System.out.println("mul: " + a * b * c);
                }

            }
        }
    }

    private static boolean isPythagoreanTriplet(int a, int b, int c) {
        return a * a + b * b == c * c;
    }

    private static void ten() {
        int threshold = 2_000_000;
        long[] primes = new long[200_000];
        int index = 0;
        primes[index] = 2;

        long trying = primes[index] + 1;
        while (trying < threshold) {
            if (isPrime(trying, primes, index + 1)) {
                index++;
                primes[index] = trying;
            }

            trying += 2;
        }

        System.out.println(Arrays.stream(primes, 0, index + 1).sum());
    }

    private static void eleven() {
        String input =
                "08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08\n" +
                        "49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00\n" +
                        "81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65\n" +
                        "52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91\n" +
                        "22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80\n" +
                        "24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50\n" +
                        "32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70\n" +
                        "67 26 20 68 02 62 12 20 95 63 94 39 63 08 40 91 66 49 94 21\n" +
                        "24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72\n" +
                        "21 36 23 09 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95\n" +
                        "78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92\n" +
                        "16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57\n" +
                        "86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58\n" +
                        "19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40\n" +
                        "04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66\n" +
                        "88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69\n" +
                        "04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36\n" +
                        "20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16\n" +
                        "20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54\n" +
                        "01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48";
        int[][] grid = makeIntGrid(input, 20, 20);
        int max = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                int hp = horizontalProductOfFour(grid, i, j);
                int vp = verticalProductOfFour(grid, i, j);
                int dpf = forwardDiagonalProductOfFour(grid, i, j);
                int dpb = backwardDiagonalProductOfFour(grid, i, j);
                max = NumberUtils.max(max, hp, vp, dpf, dpb);
            }
        }
        System.out.println(max);

    }

    private static int horizontalProductOfFour(int[][] grid, int x, int y) {
        try {
            return grid[x][y] * grid[x][y + 1] * grid[x][y + 2] * grid[x][y + 3];
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }

    private static int verticalProductOfFour(int[][] grid, int x, int y) {
        try {
            return grid[x][y] * grid[x + 1][y] * grid[x + 2][y] * grid[x + 3][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }

    private static int forwardDiagonalProductOfFour(int[][] grid, int x, int y) {
        try {
            return grid[x][y] * grid[x + 1][y + 1] * grid[x + 2][y + 2] * grid[x + 3][y + 3];
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }

    private static int backwardDiagonalProductOfFour(int[][] grid, int x, int y) {
        try {
            return grid[x][y] * grid[x + 1][y - 1] * grid[x + 2][y - 2] * grid[x + 3][y - 3];
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }

    private static int[][] makeIntGrid(String str, int x, int y) {
        int[][] grid = new int[x][y];
        String[] lines = str.split("\n");
        for (int i = 0; i < x; i++) {
            String[] nums = lines[i].split(" ");
            for (int j = 0; j < y; j++) {
                grid[i][j] = Integer.parseInt(nums[j]);
            }
        }
        return grid;
    }

    private static void twelve() {
        long tn = 0;
        int s = 1;
        do {
            tn += s++;
        } while (factorCount(tn) < 500);
        System.out.println(tn);
    }

    private static int factorCount(long n) {
        return getDivisors(n).size();
    }

    private static Set<Long> getDivisors(long n) {
        Set<Long> factors = new HashSet<>();
        long s = (long) Math.sqrt(n);
        for (long i = 1; i <= s; i++) {
            if (n % i == 0) {
                factors.add(i);
                factors.add(n / i);
            }
        }
        return factors;
    }

    private static void thirteen() {
        String input = "37107287533902102798797998220837590246510135740250\n" +
                "46376937677490009712648124896970078050417018260538\n" +
                "74324986199524741059474233309513058123726617309629\n" +
                "91942213363574161572522430563301811072406154908250\n" +
                "23067588207539346171171980310421047513778063246676\n" +
                "89261670696623633820136378418383684178734361726757\n" +
                "28112879812849979408065481931592621691275889832738\n" +
                "44274228917432520321923589422876796487670272189318\n" +
                "47451445736001306439091167216856844588711603153276\n" +
                "70386486105843025439939619828917593665686757934951\n" +
                "62176457141856560629502157223196586755079324193331\n" +
                "64906352462741904929101432445813822663347944758178\n" +
                "92575867718337217661963751590579239728245598838407\n" +
                "58203565325359399008402633568948830189458628227828\n" +
                "80181199384826282014278194139940567587151170094390\n" +
                "35398664372827112653829987240784473053190104293586\n" +
                "86515506006295864861532075273371959191420517255829\n" +
                "71693888707715466499115593487603532921714970056938\n" +
                "54370070576826684624621495650076471787294438377604\n" +
                "53282654108756828443191190634694037855217779295145\n" +
                "36123272525000296071075082563815656710885258350721\n" +
                "45876576172410976447339110607218265236877223636045\n" +
                "17423706905851860660448207621209813287860733969412\n" +
                "81142660418086830619328460811191061556940512689692\n" +
                "51934325451728388641918047049293215058642563049483\n" +
                "62467221648435076201727918039944693004732956340691\n" +
                "15732444386908125794514089057706229429197107928209\n" +
                "55037687525678773091862540744969844508330393682126\n" +
                "18336384825330154686196124348767681297534375946515\n" +
                "80386287592878490201521685554828717201219257766954\n" +
                "78182833757993103614740356856449095527097864797581\n" +
                "16726320100436897842553539920931837441497806860984\n" +
                "48403098129077791799088218795327364475675590848030\n" +
                "87086987551392711854517078544161852424320693150332\n" +
                "59959406895756536782107074926966537676326235447210\n" +
                "69793950679652694742597709739166693763042633987085\n" +
                "41052684708299085211399427365734116182760315001271\n" +
                "65378607361501080857009149939512557028198746004375\n" +
                "35829035317434717326932123578154982629742552737307\n" +
                "94953759765105305946966067683156574377167401875275\n" +
                "88902802571733229619176668713819931811048770190271\n" +
                "25267680276078003013678680992525463401061632866526\n" +
                "36270218540497705585629946580636237993140746255962\n" +
                "24074486908231174977792365466257246923322810917141\n" +
                "91430288197103288597806669760892938638285025333403\n" +
                "34413065578016127815921815005561868836468420090470\n" +
                "23053081172816430487623791969842487255036638784583\n" +
                "11487696932154902810424020138335124462181441773470\n" +
                "63783299490636259666498587618221225225512486764533\n" +
                "67720186971698544312419572409913959008952310058822\n" +
                "95548255300263520781532296796249481641953868218774\n" +
                "76085327132285723110424803456124867697064507995236\n" +
                "37774242535411291684276865538926205024910326572967\n" +
                "23701913275725675285653248258265463092207058596522\n" +
                "29798860272258331913126375147341994889534765745501\n" +
                "18495701454879288984856827726077713721403798879715\n" +
                "38298203783031473527721580348144513491373226651381\n" +
                "34829543829199918180278916522431027392251122869539\n" +
                "40957953066405232632538044100059654939159879593635\n" +
                "29746152185502371307642255121183693803580388584903\n" +
                "41698116222072977186158236678424689157993532961922\n" +
                "62467957194401269043877107275048102390895523597457\n" +
                "23189706772547915061505504953922979530901129967519\n" +
                "86188088225875314529584099251203829009407770775672\n" +
                "11306739708304724483816533873502340845647058077308\n" +
                "82959174767140363198008187129011875491310547126581\n" +
                "97623331044818386269515456334926366572897563400500\n" +
                "42846280183517070527831839425882145521227251250327\n" +
                "55121603546981200581762165212827652751691296897789\n" +
                "32238195734329339946437501907836945765883352399886\n" +
                "75506164965184775180738168837861091527357929701337\n" +
                "62177842752192623401942399639168044983993173312731\n" +
                "32924185707147349566916674687634660915035914677504\n" +
                "99518671430235219628894890102423325116913619626622\n" +
                "73267460800591547471830798392868535206946944540724\n" +
                "76841822524674417161514036427982273348055556214818\n" +
                "97142617910342598647204516893989422179826088076852\n" +
                "87783646182799346313767754307809363333018982642090\n" +
                "10848802521674670883215120185883543223812876952786\n" +
                "71329612474782464538636993009049310363619763878039\n" +
                "62184073572399794223406235393808339651327408011116\n" +
                "66627891981488087797941876876144230030984490851411\n" +
                "60661826293682836764744779239180335110989069790714\n" +
                "85786944089552990653640447425576083659976645795096\n" +
                "66024396409905389607120198219976047599490197230297\n" +
                "64913982680032973156037120041377903785566085089252\n" +
                "16730939319872750275468906903707539413042652315011\n" +
                "94809377245048795150954100921645863754710598436791\n" +
                "78639167021187492431995700641917969777599028300699\n" +
                "15368713711936614952811305876380278410754449733078\n" +
                "40789923115535562561142322423255033685442488917353\n" +
                "44889911501440648020369068063960672322193204149535\n" +
                "41503128880339536053299340368006977710650566631954\n" +
                "81234880673210146739058568557934581403627822703280\n" +
                "82616570773948327592232845941706525094512325230608\n" +
                "22918802058777319719839450180888072429661980811197\n" +
                "77158542502016545090413245809786882778948721859617\n" +
                "72107838435069186155435662884062257473692284509516\n" +
                "20849603980134001723930671666823555245252804609722\n" +
                "53503534226472524250874054075591789781264330331690";
        BigDecimal[] numbers = splitNumbers(input);
        BigDecimal sum = new BigDecimal(0);
        for (BigDecimal n : numbers) {
            sum = sum.add(n);
        }
        System.out.println(sum);
    }

    private static BigDecimal[] splitNumbers(String str) {
        return Arrays.stream(str.split("\n")).map(BigDecimal::new).toArray(BigDecimal[]::new);
    }

    private static void fourteen() {
        int th = 1_000_000;
        Map<Long, Long> lengths = new HashMap<>(th);
        long maxLength = 0;
        long numberOfMaxLenght = 1;

        for (long i = 1; i <= th; i++) {
            long l = collatzLength(i, lengths);
            lengths.put(i, l);
            if (l > maxLength) {
                maxLength = l;
                numberOfMaxLenght = i;
            }
        }

        System.out.println(numberOfMaxLenght + " " + maxLength);
    }

    private static long collatzLength(long x, Map<Long, Long> refLength) {
        long cLength = 1;
        while (x > 1) {
            x = nextCollatzNumber(x);
            if (refLength.containsKey(x)) {
                cLength = cLength + refLength.get(x);
                x = 1;
            } else {
                cLength++;
            }
        }
        return cLength;
    }

    private static long nextCollatzNumber(long x) {
        return x % 2 == 0 ? x / 2 : 3 * x + 1;
    }

    private static void fifteen() {
        int size = 20;
        long[][] grid = new long[size + 1][size + 1];
        for (int i = 1; i <= size; i++) {
            grid[0][i] = 1;
            grid[i][0] = 1;
        }

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
            }
        }

        System.out.println(size + " : " + grid[size][size]);
    }

    private static void sixteen() {
        BigDecimal n = new BigDecimal(2);
        char[] chars = n.pow(1000).toString().toCharArray();
        int sum = Arrays.stream(ArrayUtils.toObject(chars)).mapToInt(Character::getNumericValue).sum();
        System.out.println(sum);

    }

    private static void seventeen() {
        int lettersCount = IntStream.rangeClosed(1, 1000).mapToObj(NumberUtils::numberInWord).mapToInt(String::length).sum();
        System.out.println(lettersCount);
    }

    private static void eighteen() {
        String input =
                "75\n" +
                        "95 64\n" +
                        "17 47 82\n" +
                        "18 35 87 10\n" +
                        "20 04 82 47 65\n" +
                        "19 01 23 75 03 34\n" +
                        "88 02 77 73 07 63 67\n" +
                        "99 65 04 28 06 16 70 92\n" +
                        "41 41 26 56 83 40 80 70 33\n" +
                        "41 48 72 33 47 32 37 16 94 29\n" +
                        "53 71 44 65 25 43 91 52 97 51 14\n" +
                        "70 11 33 28 77 73 17 78 39 68 17 57\n" +
                        "91 71 52 38 17 14 91 43 58 50 27 29 48\n" +
                        "63 66 04 68 89 53 67 30 73 16 69 87 40 31\n" +
                        "04 62 98 27 23 09 70 98 73 93 38 53 60 04 23";

        Integer[][] triangle = NumberUtils.parseTo2dIntegerArray(input);
        for (int i = 1; i < triangle.length; i++) {
            triangle[i][0] += triangle[i - 1][0];
            triangle[i][i] += triangle[i - 1][i - 1];
            for (int j = 1; j < i; j++) {
                triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
            }
        }
        System.out.println(NumberUtils.max(triangle[triangle.length - 1]));
    }

    private static void nineteen() {
        //Say days from Sunday to Saturday is numbered from 0 to 6.
        //01 Jan 1900 is Monday. So 01 Jan 1901 is Wednesday(3).
        int day = 3;
        int count = 0;
        boolean startedWithSunday = false;
        for (int y = 1901; y <= 2000; y++) {
            for (int m = 1; m <= 12; m++) {
                day = nextMonthStartDay(day, daysInMonth(y, m));
                if (day == 0) {
                    startedWithSunday = true;
                    count++;
                } else {
                    startedWithSunday = false;
                }
            }
        }
        if (startedWithSunday) {
            count--;
        }
        System.out.println(count);
    }

    private static int nextMonthStartDay(int day, int daysInMonth) {
        return (day + daysInMonth) % 7;
    }

    private static int daysInMonth(int y, int m) {
        switch (m) {
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return y % 4 == 0 ? 29 : 28;
            default:
                return 31;
        }
    }

    private static void twenty() {
        BigDecimal factorial = factorial(new BigDecimal(100));
        System.out.println(NumberUtils.sumOfDigits(factorial));
    }

    private static BigDecimal factorial(BigDecimal x) {
        if (x.intValue() == 1) {
            return x;
        } else {
            return x.multiply(factorial(x.subtract(new BigDecimal(1))));
        }
    }

    private static void twentyOne() {
        int sum = 0;
        for (int i = 2; i < 10_000; i++) {
            if (isAmicableNumber(i)) {
                sum += i;
            }
        }
        System.out.println("sum: " + sum);
    }

    private static boolean isAmicableNumber(int x) {
        long sumOfProperDivisorsOfX = sumOf(getProperDivisors(x));
        return x != sumOfProperDivisorsOfX && x == sumOf(getProperDivisors(sumOfProperDivisorsOfX));
    }

    private static Set<Long> getProperDivisors(long x) {
        Set<Long> divisors = getDivisors(x);
        divisors.remove(x);
        return divisors;
    }

    private static long sumOf(Collection<Long> numbers) {
        long sum = 0;
        for (long num : numbers) {
            sum += num;
        }
        return sum;
    }

    private static void twentyTwo() {
        String input = ResourcesUtils.readAsString("p022_names.txt");
        List<String> names = Arrays.stream(input.split(",")).map(s -> s.substring(1, s.length() - 1)).sorted().collect(Collectors.toList());
        int totalScore = 0;
        for (int i = 0; i < names.size(); i++) {
            totalScore += alphabeticValue(names.get(i)) * (i + 1);
        }
        System.out.println(totalScore);

    }

    private static int alphabeticValue(String s) {
        int sum = 0;
        for (byte b : s.getBytes()) {
            sum += (b - 'A' + 1);
        }
        return sum;
    }

    private static void twentyThree() {
        Set<Integer> ans = abundantNumbersInRange(12, 28123);
        int sum = 0;
        for (int i = 1; i <= 28123; i++) {
            if (!isSumOfTwo(i, ans)) {
                sum += i;
            }
        }
        System.out.println(sum);
    }

    private static Set<Integer> abundantNumbersInRange(int r1, int r2) {
        Set<Integer> numbers = new HashSet<>();
        for (int i = r1; i <= r2; i++) {
            if (isAbundantNumber(i)) {
                numbers.add(i);
            }
        }
        return numbers;
    }

    private static boolean isAbundantNumber(int x) {
        return sumOf(getProperDivisors(x)) > x;
    }

    private static boolean isSumOfTwo(int x, Set<Integer> items) {
        for (int item : items) {
            if (item < x && items.contains(x - item)) {
                return true;
            }
        }
        return false;
    }


    private static void twentyFour() {
        int[] data = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int c = 1;
        while (c++ < 1_000_000) {
            ArrayUtils.updateToNextLexPermutation(data);
        }
        System.out.println(Arrays.toString(data));

    }

    private static boolean isDescSorted(int[] arr) {
        if (arr.length == 1) return true;

        for (int i = 0; i <= arr.length - 2; i++) {
            if (arr[i] < arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private static void twentyFive() {
        int order = 2;
        BigDecimal last = new BigDecimal(1), current = new BigDecimal(1), tmp;
        int digitCount = current.toPlainString().length();

        while (digitCount < 1_000) {
            tmp = current;
            current = current.add(last);
            last = tmp;
            digitCount = current.toPlainString().length();
            order++;
        }
        System.out.println(order);
    }

}
