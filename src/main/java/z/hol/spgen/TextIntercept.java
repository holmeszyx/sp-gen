package z.hol.spgen;

/**
 * To handle the transformation between text;
 * Created by holmes on 16-8-1.
 */
public interface TextIntercept {

    /**
     * transforming the text
     * @param src origin text
     * @return
     */
    String intercept(String src);

}
