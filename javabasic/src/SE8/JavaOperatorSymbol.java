package SE8;

/**********************************
 * @author zhang zhao lin
 * @date 2023年10月24日 17:17
 * @Description: java 位运算符

 **********************************/
public class JavaOperatorSymbol {


    public static void main(String[] args) {

    }


    /**
     * 位运算
     * ＆	如果相对应位都是1，则结果为1，否则为0   （A＆B），得到12，即0000 1100
     * |	如果相对应位都是 0，则结果为 0，否则为 1  	（A | B）得到61，即 0011 1101
     * ^	如果相对应位值相同，则结果为0，否则为1     （A ^ B）得到49，即 0011 0001
     * 〜	按位取反运算符翻转操作数的每一位，即0变成1，1变成0。  	（〜A）得到-61，即1100 0011
     * << 	按位左移运算符。左操作数按位左移右操作数指定的位数。  	A << 2得到240，即 1111 0000
     * >> 	按位右移运算符。左操作数按位右移右操作数指定的位数。     	A >> 2得到15即 1111
     * >>> 	按位右移补零操作符。左操作数的值按右操作数指定的位数右移，移动得到的空位以零填充。  A>>>2得到15即0000 1111
     */
    public void bitOperation() {
        int a = 60; /* 60 = 0011 1100 */
        int b = 13; /* 13 = 0000 1101 */
        int c = 0;
        c = a & b;       /* 12 = 0000 1100 */
        System.out.println("a & b = " + c );

        c = a | b;       /* 61 = 0011 1101 */
        System.out.println("a | b = " + c );

        c = a ^ b;       /* 49 = 0011 0001 */
        System.out.println("a ^ b = " + c );

        c = ~a;          /*-61 = 1100 0011 */
        System.out.println("~a = " + c );

        c = a << 2;     /* 240 = 1111 0000 */
        System.out.println("a << 2 = " + c );

        c = a >> 2;     /* 15 = 1111 */
        System.out.println("a >> 2  = " + c );

        c = a >>> 2;     /* 15 = 0000 1111 */
        System.out.println("a >>> 2 = " + c );
    }


    /**
     * 赋值运算符
     * =	简单的赋值运算符，将右操作数的值赋给左侧操作数    	C = A + B将把A + B得到的值赋给C
     * + =	加和赋值操作符，它把左操作数和右操作数相加赋值给左操作数    	C + = A等价于C = C + A
     * - =	减和赋值操作符，它把左操作数和右操作数相减赋值给左操作数  	C - = A等价于C = C - A
     * * =	乘和赋值操作符，它把左操作数和右操作数相乘赋值给左操作数    	C * = A等价于C = C * A
     * / =	除和赋值操作符，它把左操作数和右操作数相除赋值给左操作数    	C / = A，C 与 A 同类型时等价于 C = C / A
     * （％）=	取模和赋值操作符，它把左操作数和右操作数取模后赋值给左操作数   C％= A等价于C = C％A
     * << =	左移位赋值运算符   C << = 2等价于C = C << 2
     * >> =	右移位赋值运算符   C >> = 2等价于C = C >> 2
     * ＆=	按位与赋值运算符   C＆= 2等价于C = C＆2
     * ^ =	按位异或赋值操作符  C ^ = 2等价于C = C ^ 2
     * | =	按位或赋值操作符   C | = 2等价于C = C | 2
     */
    public void valueOperation() {
        int a = 10;
        int b = 20;
        int c = 0;
        c = a + b;
        System.out.println("c = a + b = " + c );
        c += a ;
        System.out.println("c += a  = " + c );
        c -= a ;
        System.out.println("c -= a = " + c );
        c *= a ;
        System.out.println("c *= a = " + c );
        a = 10;
        c = 15;
        c /= a ;
        System.out.println("c /= a = " + c );
        a = 10;
        c = 15;
        c %= a ;
        System.out.println("c %= a  = " + c );
        c <<= 2 ;
        System.out.println("c <<= 2 = " + c );
        c >>= 2 ;
        System.out.println("c >>= 2 = " + c );
        c >>= 2 ;
        System.out.println("c >>= 2 = " + c );
        c &= a ;
        System.out.println("c &= a  = " + c );
        c ^= a ;
        System.out.println("c ^= a   = " + c );
        c |= a ;
        System.out.println("c |= a   = " + c );
    }


}
