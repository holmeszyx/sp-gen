package z.hol.spgen;

/**
 * parameter name's transformation
 * Created by holmes on 16-8-1.
 */
public final class ParamNameIntercept implements TextIntercept {

    public static final ParamNameIntercept INSTANCE = new ParamNameIntercept();

    private ParamNameIntercept() {
    }

    public String intercept(String src) {
        String result;
        // remove '_' and '-', and make next letter to upper case if it is the lower case.
        final String name = src;
        int length = name.length();
        StringBuilder sb = new StringBuilder(length);
        boolean nextCap = false;
        for (int i = 0; i < length; i++) {
            char c = name.charAt(i);
            if (c == '_' || c == '-') {
                nextCap = true;
                continue;
            }
            if (sb.length() > 0 && nextCap && c >= 'a' && c <= 'z') {
                c = Character.toUpperCase(c);
                nextCap = false;
            }
            sb.append(c);
        }
        result = sb.toString();
        return result;
    }

}
