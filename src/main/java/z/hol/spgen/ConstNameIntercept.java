package z.hol.spgen;

/**
 * const name's transformation
 * Created by holmes on 16-8-1.
 */
public final class ConstNameIntercept implements TextIntercept {

    public static final ConstNameIntercept INSTANCE = new ConstNameIntercept();

    private ConstNameIntercept() {
    }

    public String intercept(String src) {
        String result;
        final String name = src;
        int length = name.length();
        StringBuilder sb = new StringBuilder(length + 8);
        for (int i = 0; i < length; i++) {
            char c = name.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                c = Character.toLowerCase(c);
                sb.append("_");
            }
            sb.append(c);
        }
        result = sb.toString();
        return result;
    }

}
